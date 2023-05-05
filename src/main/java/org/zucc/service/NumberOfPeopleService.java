package org.zucc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zucc.entity.NumberOfPeople;

import java.util.List;

public interface NumberOfPeopleService extends IService<NumberOfPeople> {
    void transfer(String systemName, String from, String to, int num);
}
