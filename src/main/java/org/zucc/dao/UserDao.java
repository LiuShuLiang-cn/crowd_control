package org.zucc.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.zucc.entity.User;

@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("select * from user where username = #{name}")
    User getUserByName(String name);

    @Update("UPDATE `user` SET `status` = 0 , `systemname`= NULL")
    int update_init_stop();
}
