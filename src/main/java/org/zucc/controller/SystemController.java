package org.zucc.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zucc.entity.Systems;
import org.zucc.service.SystemService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/system")
public class SystemController {
    @Resource
    private SystemService systemService;

    @Resource
    private RedisTemplate redisTemplate;
    @GetMapping("/list")
    @ResponseBody
    public List<Systems> systemsList() {
        return systemService.list();
    }


    @GetMapping("/time/{systemName}")
    @ResponseBody
    public String getCurrentTime(@PathVariable("systemName") String systemName) {
        return (String) redisTemplate.opsForValue().get(systemName+"_Time");
    }
}
