package org.zucc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zucc.entity.User;
import org.zucc.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
            return userService.getUserByName(
                    ((User) httpSession.getAttribute("currentUser"))
                            .getUserName()
            );
        } catch (NullPointerException e) {
            log.error("没有登录，无法获取当前用户信息");
            return new User();
        }
    }

    @GetMapping("/index")
    public String Index() {
        return "index";
    }

    @GetMapping("/question")
    public String getQuestion() {
        return "question";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/user/login";
    }
}
