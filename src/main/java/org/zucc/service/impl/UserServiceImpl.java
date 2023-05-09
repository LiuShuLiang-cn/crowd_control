package org.zucc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.zucc.dao.UserDao;
import org.zucc.entity.Systems;
import org.zucc.entity.User;
import org.zucc.service.*;
import org.zucc.utils.Constant;
import org.zucc.utils.SaltUtils;
import org.zucc.utils.TimeUtils;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    private final SystemService systemService;


    private final DeployService deployService;

    private final ScoreService scoreService;

    private final UserDao userDao;

    private final OperateService operateService;

    @Override
    public String registerInfo(User user) {
        if (user.getUserName().length() == 0 || user.getPassword().length() == 0) {
            return "redirect:/user/register";
        }
        //检查用户名是否存在
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUserName());
        List<User> users = baseMapper.selectByMap(map);
        if (null == users || users.size() == 0) {
            //不为空的情况
            //1.生成随机盐
            String salt = SaltUtils.getSalt(8);
            //2.将随机盐保存到数据
            user.setSalt(salt);
            //3.明文密码进行md5+salt+hash散列
            Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
            user.setPassword(md5Hash.toHex());
            user.setStatus(0);
            baseMapper.insert(user);
            return "redirect:/user/login";
        } else {
            //为空的情况
            return "redirect:/user/register";
        }
    }

    @Override
    public String loginInfo(User user, int speed, String system, HttpSession httpSession) {
        try {
            User user_mysql = userDao.getUserByName(user.getUserName());
            if (user_mysql.getStatus() == Constant.STATUS_ONLINE) {
                /* 还需要加一个判断角色是否登陆 */
                return "redirect:/user/login";
            }
            /* 如果是0，则是创建新系统 */
            if ("0".equals(system)) {
                creatSystem(speed);
            }
            user.setId(user_mysql.getId());
            user.setSystemname(system);
            scoreService.setLoginRole(system, user.getRole(), user.getUserName());
            baseMapper.updateById(user);
            httpSession.setAttribute("currentUser", user);
            Subject subject = SecurityUtils.getSubject();
            subject.login(new UsernamePasswordToken(user.getUserName(), user.getPassword()));
            return handlePage(user.getRole());
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误！");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误！");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        user.setStatus(Constant.STATUS_OFFLINE);
        baseMapper.updateById(user);
        return "redirect:/user/login";
    }

    @Override
    public User getUserByName(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return baseMapper.selectOne(queryWrapper);
    }

    private void creatSystem(int speed) throws ParseException {
        /*
           创建一个新系统系统
         */
        Systems systemOperate = new Systems();
        Random random = new Random();
        String system = "系统" + (random.nextInt(8999) + 1000);
        systemOperate.setSystemName(system);
        //放在websocket监听器中
        //systemOperate.setUsername(user.getUserName()+"-"+user.getRole() + ";");
        systemOperate.setSpeed(speed);
        systemOperate.setRunTime(TimeUtils.initTimes());
        systemService.save(systemOperate);
        //初始化系统人数
        systemService.initSystem(system);
        //初始化公交地铁和活动
        operateService.initOperate(system);
        //初始化部署人数
        deployService.initDeploy(system);
        //初始化人数
        scoreService.initScore(system);
    }

    private String handlePage(String role) {
        return switch (role) {
            case "公安", "交警", "城管", "志愿者" -> "police";
            case "市民" -> "role";
            case "主办单位" -> "sponsor";
            case "公交地铁" -> "subway";
            default -> "index";
        };
    }


}
