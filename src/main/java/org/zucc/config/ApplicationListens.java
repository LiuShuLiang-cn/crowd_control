package org.zucc.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.zucc.dao.UserDao;

import javax.annotation.Resource;
import java.util.Set;

@Slf4j
@Component
public class ApplicationListens implements CommandLineRunner, DisposableBean {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private UserDao userDao;

    private void onClose_sql() {
        int rowsAffected = userDao.update_init_stop();
        log.info("Rows affected: " + rowsAffected);
    }

    /**
     * 删除所有缓存
     */
    private void delAll() {
        Set<String> keys = redisTemplate.keys("*");
        if (ObjectUtils.isNotEmpty(keys)) {
            redisTemplate.delete(keys);
        }
    }

    //应用启动成功后的回调
    @Override
    public void run(String... args) {
        delAll();
        onClose_sql();
        log.info("应用启动成功，预相关加载数据");
    }

    //应用启动关闭前的回调
    @Override
    public void destroy() {
        delAll();
        onClose_sql();
        log.info("应用正在关闭，清理相关数据");
    }
}