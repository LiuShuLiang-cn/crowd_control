package org.zucc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zucc.dao.UserDao;
import org.zucc.entity.User;
import org.zucc.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String register(User user) {
        return userService.registerInfo(user);
    }

    @PostMapping("/login")
    public String login(User user, int num, String system, HttpSession httpSession) {
        return userService.loginInfo(user, num, system, httpSession);
    }

    @GetMapping("/currentuser")
    @ResponseBody
    public User currentUser(HttpSession httpSession) {
        /*
            获取当前登录的用户
         */
        try {
            User currentUser = userService.getUserByName(
                    ((User) httpSession.getAttribute("currentUser"))
                            .getUserName()
            );
            return currentUser;
        }catch (NullPointerException e){
            log.error("Error! No login.");
            return new User();
        }
    }

    @GetMapping("/index")
    public String Index(){
        return "index";
    }
}
