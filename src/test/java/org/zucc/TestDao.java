package org.zucc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.zucc.dao.*;
import org.zucc.entity.Chat;
import org.zucc.entity.Issue;
import org.zucc.entity.Score;
import org.zucc.entity.User;
import org.zucc.service.IssueService;
import org.zucc.service.NumberOfPeopleService;
import org.zucc.service.OperateService;
import org.zucc.service.ScoreService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class TestDao {
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private NumberOfPeopleDao numberOfPeopleDao;

    @Test
    void Redis_1() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("zujj", numberOfPeopleDao.getNumBySys("系统3014"));
        Set<String> keys = redisTemplate.keys("zu" + "*");
        for (String key : keys) {
            System.out.println(key);
        }
        System.out.println();
        Boolean zucc = redisTemplate.delete("zujj");
        System.out.println(zucc);
    }

    @Resource
    NumberOfPeopleService numberOfPeopleService;

    @Test
    void PeopleTest() {
        numberOfPeopleService.transfer("系统6841", "钱塘里", "长生里", 500);
    }

    @Resource
    UserDao userDao;

    @Test
    void userDaoTest() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", "q");
        List<User> users = userDao.selectList(wrapper);
        for (User user : users) {
            System.out.printf("===>", user.toString());
        }
    }

    @Resource
    ChatDao chatDao;

    @Test
    void chatDaoTest() {
        Chat chat = chatDao.selectById(19);
        chat.setStatue(1);
        chatDao.updateById(chat);
    }

    @Resource
    DeployDao deployDao;

    @Test
    void DeployDaoTest() {
        //DeployVo deployVo=new DeployVo();
        //deployVo.setLat(30.248522);
        //deployVo.setLng(120.164273);
        //deployVo.setRole("ga");
        //deployVo.setNum(26);
        //deployVo.setSystemName("系统6841");
        //deployDao.updateGa(deployVo);
        String role = "cg";
        switch (role) {
            case "zyz":
                System.out.println(1);
                break;
            case "cg":
                System.out.println("123");
                break;
        }
    }

    @Resource
    IssueDao issueDao;
    @Resource
    IssueService issueService;

    @Test
    void issueTest() {
        List<Issue> issues = issueDao.getAllIssue("市民");
        for (Issue issue : issues) {
            System.out.println(issue.toString());
        }

    }

    @Resource
    ScoreService scoreService;

    @Test
    void ScoreTest() {
        //scoreService.initScore("系统6841");
        Score score = scoreService.setCurrentScore("系统6841", "指挥中心", 29);
        System.out.println(score);
    }

    @Resource
    private SystemDao systemDao;

    @Test
    void test1() {
        System.out.print("" + systemDao.getSystemBySystemName("系统6841").toString());
    }

    /**
     * shiro 授权方法测试
     */
    @Test
    public void Accredit() {
        Subject subject = SecurityUtils.getSubject();
        //模拟用户账号Token进行认证和授权
        UsernamePasswordToken token = new UsernamePasswordToken("q", "q");
        //登录
        subject.login(token);
        //登录认证成功校验
        System.out.println(subject.isAuthenticated());
        //权限认证校验
        subject.checkRoles("指挥中心");

    }

    @Autowired
    private ScoreDao scoreDao;

    @Test
    void test2() {
//        List<Issue> issues = issueDao.selectList(null);
//        for (int i = 0; i < 10; i++) {
//            issues.get(i).setQuestion(issues.get(i).getQuestion()+"====A");
//            issueDao.insert(issues.get(i));
//        }
        String[] s = {"指挥中心", "市民", "交警", "城管", "公安", "主办单位", "志愿者"};
        for (int i = 0; i < 7; i++) {
            for (Issue issue : issueDao.getAllIssue(s[i])) {
                issue.setQuestion(issue.getQuestion() + "====A");
                issueDao.insert(issue);
                System.out.print(issue);
            }
        }



    }

    @Autowired
    private OperateService operateService;

    @Test
    public void test_3() {
        Issue issue = issueService.getIssue("公安");
        System.out.println(issue);
    }

    @Test
    public void test_4() {
        int count = issueService.getCount("系统6841", "指挥中心");
        System.out.println(count);
    }

}
