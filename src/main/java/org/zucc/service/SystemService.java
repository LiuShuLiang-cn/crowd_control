package org.zucc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zucc.entity.Systems;
import org.zucc.entity.User;

public interface SystemService extends IService<Systems> {
    void initSystem(String systemName);
    void updateTime(String systemName);
}
