package org.zucc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.zucc.dao.ScoreDao;
import org.zucc.entity.Score;
import org.zucc.service.ScoreService;

import javax.annotation.Resource;


@Service
@Slf4j
public class ScoreServerImpl extends ServiceImpl<ScoreDao, Score> implements ScoreService {
    @Resource
    private ScoreDao scoreDao;
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void initScore(String systemName) {
        /*
        系统初始化所有角色的得分,使用sql语句插入
         */
        scoreDao.initScore(systemName);
    }

    @Override
    public void setLoginRole(String systemName, String role, String userName) {
        /*
        每个角色登陆时设置用户名
         */
        scoreDao.update_score(systemName, role, userName);
    }

    @Override
    public Score Composition(String systemName, String roleTopic, int statue) {
        /*
        答对题目加5分
         */
        Score score = getScoreBySystemRole(systemName, roleTopic);
        if (statue == 20) {
            score.setScore(score.getScore() + 5);
            baseMapper.updateById(score);
        }
        return score;
    }

    @Override
    public Score getCurrentScore(String systemName, String roleTopic) {
        return getScoreBySystemRole(systemName, roleTopic);
    }

    @Override
    public Score setCurrentScore(String systemName, String roleTopic, int fraction) {
        /*
        设置当前用户的分数
         */
        Score score = getScoreBySystemRole(systemName, roleTopic);
        if (fraction > score.getScore()) {
            log.debug("得分：{}", (fraction - score.getScore()));
        } else if (fraction < score.getScore()) {
            log.debug("扣分：{}", (score.getScore() - fraction));
        }
        score.setScore(fraction);
        baseMapper.updateById(score);
        countIssue(systemName, roleTopic);
        return score;
    }

    private void countIssue(String systemName, String roleTopic) {
        /*
        记录答对题目数量
         */
        int count;
        try {
            count = (int) redisTemplate.opsForValue().get(systemName + "-" + roleTopic);
        } catch (java.lang.NullPointerException e) {
            count = 0;
        }
        count++;
        redisTemplate.opsForValue().set(systemName + "-" + roleTopic, count);
        log.info("已完成{}道题目", count);
    }

    private Score getScoreBySystemRole(String systemName, String roleTopic) {
        QueryWrapper<Score> scoreQueryWrapper = new QueryWrapper<>();
        scoreQueryWrapper.eq("systemName", systemName);
        scoreQueryWrapper.eq("role", roleTopic);
        Score score = baseMapper.selectOne(scoreQueryWrapper);
        if (score == null) {
            log.error("Error! Score is NULL;");
            return new Score();
        }
        return score;
    }
}
