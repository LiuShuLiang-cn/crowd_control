package org.zucc.websocket;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.zucc.dao.SystemDao;
import org.zucc.dao.UserDao;
import org.zucc.entity.Systems;
import org.zucc.entity.User;
import org.zucc.service.RecordService;
import org.zucc.utils.Constant;

import java.util.Objects;


@Component
@Slf4j
public class WebSocketListener {

    private UserDao userDao;
    private SystemDao systemDao;

    private RecordService recordService;
    private RedisTemplate redisTemplate;

    @Autowired
    public void setSystemDao(SystemDao systemDao) {
        this.systemDao = systemDao;
    }

    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 建立连接时侯需要处理的事项
     */
    @EventListener
    public void handleConnectListener(SessionConnectedEvent sessionConnectedEvent) throws Exception {
        System.out.println("建立连接 -> {}" + sessionConnectedEvent);
        User user = userDao.getUserByName(Objects.requireNonNull(sessionConnectedEvent.getUser()).toString());
        /* 此处修改状态，防止登陆失败而修改数据库导致无法再次登录 */
        user.setStatus(Constant.STATUS_ONLINE);
        userDao.updateById(user);
        /* 在系统表中加入登录的用户 */
        Systems systems = systemDao.getSystemBySystemName(user.getSystemname());
        systems.setUsername(sessionConnectedEvent.getUser() + "-");
        systemDao.updateById(systems);
        recordService.loginRecord(user.getSystemname(), user.getUserName());/*记录一下登陆时间*/
    }


    /**
     * 断开连接监听
     */
    @EventListener
    public void handleDisconnectListener(SessionDisconnectEvent sessionDisconnectEvent) throws Exception {
        System.out.println("断开连接 -> {}" + sessionDisconnectEvent);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", String.valueOf(sessionDisconnectEvent.getUser()));
        User user = userDao.selectOne(wrapper);
        user.setStatus(Constant.STATUS_OFFLINE);
        String sysname = user.getSystemname();
        QueryWrapper<Systems> queryWrapper2;
        queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("systemname", user.getSystemname());
        Systems systems = systemDao.selectOne(queryWrapper2);
        String[] split = systems.getUsername().split("-");
        systems.setUsername("");
        for (String s : split) {
            if (!s.equals(user.getUserName())) {
                systems.setUsername(s + "-");
            }
        }
        systems.setRunTime((String) redisTemplate.opsForValue().get(user.getSystemname() + "_Time"));
        user.setSystemname("");

        systemDao.updateById(systems);
        userDao.updateById(user);
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        recordService.logoutRecord(sysname, user.getUserName());/*记录一下退出时间*/

        redisTemplate.delete(sysname + "_NumberOfPeople");
        redisTemplate.delete(sysname + "_Deploys");
        redisTemplate.delete(sysname + "_Time");

        log.info("系统退出！");
    }

    /**
     * 订阅监听
     */
    @EventListener
    public void handleSubscribeListener(SessionSubscribeEvent sessionSubscribeEvent) throws Exception {
        //用户第一次订阅评到，可以做一些相关事情，当然这个事情可能在login的时候就做掉了，那么这里就不用再写什么
        System.out.println("新的订阅 -> {}" + sessionSubscribeEvent);
        //template.convertAndSend("/topic/all", "上线了");
    }


}
