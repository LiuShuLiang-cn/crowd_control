package org.zucc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("deploy")
public class Deploy implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField("sysName")
    private String sysName;
    @TableField("ga")
    private int ga;
    @TableField("ga_lng")
    private double gaLng;
    @TableField("ga_lat")
    private double gaLat;
    @TableField("jj")
    private int jj;
    @TableField("jj_lng")
    private double jjLng;
    @TableField("jj_lat")
    private double jjLat;
    @TableField("cg")
    private int cg;
    @TableField("cg_lng")
    private double cgLng;
    @TableField("cg_lat")
    private double cgLat;
    @TableField("zyz")
    private int zyz;
    @TableField("zyz_lng")
    private double zyzLng;
    @TableField("zyz_lat")
    private double zyzLat;

}
