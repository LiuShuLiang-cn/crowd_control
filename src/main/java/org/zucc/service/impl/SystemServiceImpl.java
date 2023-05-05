package org.zucc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.zucc.dao.NumberOfPeopleDao;
import org.zucc.dao.SystemDao;
import org.zucc.dao.UserDao;
import org.zucc.entity.NumberOfPeople;
import org.zucc.entity.Systems;
import org.zucc.entity.User;
import org.zucc.service.SystemService;
import org.zucc.service.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Service
public class SystemServiceImpl extends ServiceImpl<SystemDao, Systems> implements SystemService {
    @Resource
    private NumberOfPeopleDao numberOfPeopleDao;
    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public void initSystem(String systemName) {
        for (NumberOfPeople numberOfPeople : numberOfPeopleDao.getInitList()) {
            numberOfPeople.setId(null);
            numberOfPeople.setSystemName(systemName);
            numberOfPeopleDao.insert(numberOfPeople);
        }
    }

    @Override
    public void updateTime(String systemName) {

    }
}
