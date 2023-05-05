package org.zucc.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.zucc.dao.NumberOfPeopleDao;
import org.zucc.dao.SystemDao;
import org.zucc.entity.NumberOfPeople;
import org.zucc.entity.Systems;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//@Component
public class Scheduled {
    @Resource
    private NumberOfPeopleDao numberOfPeopleDao;
    @Resource
    private SystemDao systemDao;
    @Resource
    private RedisTemplate redisTemplate;

    @org.springframework.scheduling.annotation.Scheduled(cron = "*/5 * * * * ?")
    public void dynamic() {
        Set<String> keys = redisTemplate.keys("*" + "_NumberOfPeople");
//        Set<String> keys_deploy = redisTemplate.keys( "*"+"_Deploy");
        for (String key : keys) {
            List<NumberOfPeople> numberOfPeople = (List<NumberOfPeople>) redisTemplate.opsForValue().get(key);
            for (int i = 0; i < 9; i++) {
                int n = new Random().nextInt(200) - 100;
                numberOfPeople.get(i).setNumber(numberOfPeople.get(i).getNumber() + n);
                numberOfPeopleDao.updateById(numberOfPeople.get(i));
            }
            String[] parts = key.split("_");
            redisTemplate.delete(key);
            redisTemplate.opsForValue().set(key, numberOfPeople);
        }
    }

    @org.springframework.scheduling.annotation.Scheduled(cron = "*/1 * * * * ?")
    public void updateTime() throws ParseException {
        Set<String> keys = redisTemplate.keys("*" + "_Time");
        if (keys == null) {
            return;
        }
        for (String key : keys) {
            String time = (String) redisTemplate.opsForValue().get(key);
            String[] parts = key.split("_");

            redisTemplate.delete(key);
            redisTemplate.opsForValue().set(key, updateTime(time, parts[0]));
        }
    }

    private String updateTime(String time,String systemName) throws ParseException {
        Date parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(time);
        QueryWrapper<Systems> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("systemname", systemName);
        Systems systems = systemDao.selectOne(queryWrapper);
        parse.setTime(parse.getTime() + 1000 * systems.getSpeed());
        Date parse2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse("Sat Jan 01 02:00:00 CST 2022");
        if (parse.getTime()>parse2.getTime()){
            return parse2.toString();
        }else {
            return parse.toString();
        }
    }
}
