package org.zucc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zucc.dao.RecordDao;
import org.zucc.entity.Record;
import org.zucc.service.RecordService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class RecordServiceImpl extends ServiceImpl<RecordDao, Record> implements RecordService {
    @Override
    public int logCounts(String systemName, String userName) {
        /*
        查询登录次数
         */
        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("systemName", systemName);
        queryWrapper.eq("userName", userName);
        List<Record> records = baseMapper.selectList(queryWrapper);
        log.info(userName + "一共登录了" + records.size() + "次系统<" + systemName + ">");
        return records.size();
    }

    @Override
    public void loginRecord(String systemName, String userName) {
        /*
        记录登录时间
         */
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        Record record = new Record();
        record.setSystemName(systemName);
        record.setUserName(userName);
        record.setLoginTime(timeStamp);
        record.setLogoutTime(null);
        int i = baseMapper.insert(record);
        if (i == 1) {
            log.info(timeStamp+":"+userName+"登陆了"+systemName);
        }
    }

    @Override
    public void logoutRecord(String systemName, String userName) {
        /*
        记录退出时间
         */
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        Record record = baseMapper.queryLogoutTimeIsNull(systemName, userName);
        record.setLogoutTime(timeStamp);
        int i = baseMapper.updateById(record);
        if (i == 1) {
            log.info(timeStamp+":"+userName+"退出了"+systemName);
        }
    }
}
