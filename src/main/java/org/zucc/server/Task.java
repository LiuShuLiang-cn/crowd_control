//package org.zucc.server;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.zucc.dao.NumberOfPeopleDao;
//import org.zucc.dao.SystemDao;
//import org.zucc.entity.NumberOfPeople;
//import org.zucc.entity.Systems;
//import javax.annotation.Resource;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
//@Component
//@Slf4j
//public class Task {
//    @Resource
//    private NumberOfPeopleDao numberOfPeopleDao;
//    @Resource
//    private SystemDao systemDao;
//    @Resource
//    private RedisTemplate<String,Object> redisTemplate;
//
//    @Scheduled(cron = "*/5 * * * * ?")
//    public void dynamic() {
//        Set<String> keys = redisTemplate.keys("*" + "_NumberOfPeople");
//        for (String key : keys) {
//            List<NumberOfPeople> numberOfPeople = (List<NumberOfPeople>) redisTemplate.opsForValue().get(key);
//            for (int i = 0; i < 9; i++) {
//                int n = new Random().nextInt(200) - 100;
//                numberOfPeople.get(i).setNumber(numberOfPeople.get(i).getNumber() + n);
//                numberOfPeopleDao.updateById(numberOfPeople.get(i));
//            }
//            String[] parts = key.split("_");
//            redisTemplate.delete(key);
//            redisTemplate.opsForValue().set(key, numberOfPeople);
//        }
//    }
//
//    @Scheduled(cron = "*/1 * * * * ?")
//    public void updateTime() {
//        Set<String> keys = redisTemplate.keys("*" + "_Time");
//        if (keys == null) {
//            return;
//        }
//        for (String key : keys) {
//            String time = (String) redisTemplate.opsForValue().get(key);
//            String[] parts = key.split("_");
//
//            redisTemplate.delete(key);
//            redisTemplate.opsForValue().set(key, updateTime(time, parts[0]));
//        }
//    }
//
//    /**
//     * 更新时间
//     * @param time 当前时间
//     * @param systemName 系统名称
//     * @return 返回更新的时间
//     */
//    private String updateTime(String time,String systemName) {
//        Date parse = null;
//        try {
//            parse = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(time);
//        } catch (ParseException e) {
//            log.error("时间字符串格式不符合要求，{}",e.getMessage());
//        }
//        Systems systems = (Systems) redisTemplate.opsForValue().get(systemName);
//        if (systems == null) {
//            QueryWrapper<Systems> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("systemname", systemName);
//            systems = systemDao.selectOne(queryWrapper);
//            redisTemplate.opsForValue().set(systemName, systems);
//        }
//        parse.setTime(parse.getTime() + 1000 * systems.getSpeed());
//        Date parse2 = null;
//        try {
//            parse2 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse("Sat Jan 01 02:00:00 CST 2022");
//        } catch (ParseException e) {
//            log.error("时间字符串格式不符合要求，{}",e.getMessage());
//        }
//        if (parse.getTime()>parse2.getTime()){
//            return parse2.toString();
//        }else {
//            return parse.toString();
//        }
//    }
//}
