package org.zucc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Service;
import org.zucc.entity.Issue;

import java.util.List;

@Mapper
public interface IssueDao extends BaseMapper<Issue> {
    @Select("select * from question_choice where classify = #{role}")
    @Results({
            @Result(column="answer_A", property="answer_A", jdbcType = JdbcType.VARCHAR),
            @Result(column="answer_B", property="answer_B", jdbcType=JdbcType.VARCHAR),
            @Result(column="answer_C", property="answer_C", jdbcType=JdbcType.VARCHAR),
            @Result(column="answer_D", property="answer_D", jdbcType=JdbcType.VARCHAR),
            @Result(column="right_answer", property="right_answer", jdbcType=JdbcType.VARCHAR)
    })
    List<Issue> getAllIssue(String role);
}
