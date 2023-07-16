package org.zucc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.zucc.dao.IssueDao;
import org.zucc.entity.Issue;
import org.zucc.service.IssueService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class IssueServiceImpl extends ServiceImpl<IssueDao, Issue> implements IssueService {
    @Resource
    private IssueDao issueDao;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    @Override
    public Issue getIssue(String role) {
        /*
        随机获取一个问题
         */
        List<Issue> allIssue = issueDao.getAllIssue(role);
        Issue issue = allIssue.get((int) (Math.random() * allIssue.size()));
        log.info("获取角色<" + role + ">的问题：" + issue.getQuestion());

        return issue;
    }

    @Override
    public int getCount(String systemName, String roleTopic) {
        int count;
        try {
            count = (int) redisTemplate.opsForValue().get(systemName + "-" + roleTopic);
        } catch (java.lang.NullPointerException e) {
            redisTemplate.opsForValue().set(systemName + "-" + roleTopic, 0);
            count=0;
        }
        return count;
    }
}
