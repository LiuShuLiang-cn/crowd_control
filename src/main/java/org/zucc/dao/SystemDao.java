package org.zucc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.zucc.entity.Systems;

import java.util.List;


@Mapper
public interface SystemDao extends BaseMapper<Systems> {
    @Select("select * from crowd_control.number_of_people_init")
    List<Systems> getInitSystems();

    @Select("select * from systems where systemname = #{systemName}")
    Systems getSystemBySystemName(String systemName);

}
