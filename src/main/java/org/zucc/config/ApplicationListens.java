package org.zucc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class ApplicationListens implements CommandLineRunner, DisposableBean {
    @Resource
    private RedisTemplate redisTemplate;

    //应用启动成功后的回调
    @Override
    public void run(String... args) throws Exception {
//        Set<String> keys = redisTemplate.keys("*");
//        redisTemplate.delete(keys);
        log.info("应用启动成功，预相关加载数据");
    }

    //应用启动关闭前的回调
    @Override
    public void destroy() throws Exception {
//        Set<String> keys = redisTemplate.keys("*");
//        redisTemplate.delete(keys);
        log.info("应用正在关闭，清理相关数据");
    }
}