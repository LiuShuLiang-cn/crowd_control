package org.zucc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zucc.entity.Record;

public interface RecordService extends IService<Record> {
    int logCounts(String systemName, String userName);

    void loginRecord(String systemName, String userName);

    void logoutRecord(String systemName, String userName);
}
