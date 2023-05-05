package org.zucc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.zucc.dao.NumberOfPeopleDao;
import org.zucc.entity.NumberOfPeople;
import org.zucc.service.NumberOfPeopleService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NumberOfPeopleServiceImpl extends ServiceImpl<NumberOfPeopleDao, NumberOfPeople> implements NumberOfPeopleService {
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void transfer(String systemName, String from, String to, int num) {
        /*
        转移人数
         */
        List<NumberOfPeople> peoples = (List<NumberOfPeople>) redisTemplate.opsForValue().get(systemName + "_NumberOfPeople");
        for (int i = 0; i < peoples.size(); i++) {
            if (peoples.get(i).getRegion().equals(from)) {
                peoples.get(i).setNumber(peoples.get(i).getNumber() - num);
            } else if (peoples.get(i).getRegion().equals(to)) {
                peoples.get(i).setNumber(peoples.get(i).getNumber() + num);
            }
        }
        redisTemplate.opsForValue().set(systemName + "_NumberOfPeople", peoples);
    }
}
