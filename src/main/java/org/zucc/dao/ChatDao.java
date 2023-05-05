package org.zucc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.zucc.entity.Chat;

import java.util.List;

@Mapper
public interface ChatDao extends BaseMapper<Chat> {
    @Select("SELECT * FROM chat WHERE type='2' AND statue='0' and systemName=#{systemName} and toRole=#{roleTopic}")
    List<Chat> getNoTODO(String systemName, String roleTopic);
}
