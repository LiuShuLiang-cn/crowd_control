package org.zucc.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.zucc.entity.User;

@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("select * from user where username = #{name}")
    User getUserByName(String name);
}
