package org.zucc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.zucc.entity.NumberOfPeople;

import java.util.List;

@Mapper
public interface NumberOfPeopleDao extends BaseMapper<NumberOfPeople> {
    @Select("select * from number_of_people_init")
    List<NumberOfPeople> getInitList();

    @Select("SELECT * FROM number_of_people WHERE systemName=#{sysName}")
    List<NumberOfPeople> getNumBySys(String sysName);
    @Select("select distinct a.sysname from user as a , number_of_people as b\n" +
            "where a.sysname=b.systemName and a.sysname  != \"\" and a.sysname is not null")
    List<String> getAllSystemName();
}
