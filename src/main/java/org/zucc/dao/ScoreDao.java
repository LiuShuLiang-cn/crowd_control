package org.zucc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.zucc.entity.Score;

@Mapper
public interface ScoreDao extends BaseMapper<Score> {
    @Insert("INSERT into user_score(systemName,role,score) values(#{systemName},#{role},#{score})")
    void insert_score(String systemName, String role, int score);

    @Update("update user_score set username=#{userName} where systemName=#{systemName} and role=#{role}")
    void update_score(String systemName, String role, String userName);

    @Insert("insert into user_score(systemName,role,score)\n" +
            "values(#{systemName},'指挥中心',0),(#{systemName},'交警',0),\n" +
            "(#{systemName},'公安',0),(#{systemName},'城管',0),(#{systemName},'公交地铁',0),\n" +
            "(#{systemName},'主办单位',0),(#{systemName},'志愿者',0),(#{systemName},'市民',0);")
    void initScore(String systemName);

    @Select("select * from user_score where user_score.role=#{roleTopic} and user_score.systemName=#{systemName}")
    Score queryByRole_System(String roleTopic,String systemName);
}
