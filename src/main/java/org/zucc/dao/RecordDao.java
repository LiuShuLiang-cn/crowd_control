package org.zucc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.zucc.entity.Record;

@Mapper
public interface RecordDao extends BaseMapper<Record> {
    @Select("SELECT * FROM login_record where systemName=#{systemName} AND userName=#{userName} AND logoutTime is null")
    Record queryLogoutTimeIsNull(String systemName, String userName);
}
