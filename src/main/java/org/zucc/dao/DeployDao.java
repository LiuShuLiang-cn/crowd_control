package org.zucc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.zucc.entity.Deploy;
import org.zucc.entity.vo.DeployVo;

import java.util.List;


@Mapper
public interface DeployDao extends BaseMapper<Deploy> {
    @Select("SELECT * FROM deploy WHERE sysName=#{systemName}")
    List<Deploy> getNumBySys(String systemName);

    @Update("UPDATE deploy SET ga=#{num} WHERE cg_lat=#{lat} and cg_lng=#{lng} and sysName=#{systemName}")
    void updateGa(DeployVo deployVo);
    @Update("UPDATE deploy SET zyz=#{num} WHERE cg_lat=#{lat} and cg_lng=#{lng} and sysName=#{systemName}")
    void updateZyz(DeployVo deployVo);
    @Update("UPDATE deploy SET jj=#{num} WHERE cg_lat=#{lat} and cg_lng=#{lng} and sysName=#{systemName}")
    void updateJj(DeployVo deployVo);
    @Update("UPDATE deploy SET cg=#{num} WHERE cg_lat=#{lat} and cg_lng=#{lng} and sysName=#{systemName}")
    void updateCg(DeployVo deployVo);


}
