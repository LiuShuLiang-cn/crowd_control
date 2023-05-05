package org.zucc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zucc.entity.User;

import javax.servlet.http.HttpSession;

public interface UserService extends IService<User> {
    String registerInfo(User user);
    String loginInfo(User user, int speed, String system, HttpSession httpSession);
    User getUserByName(String username);
}
