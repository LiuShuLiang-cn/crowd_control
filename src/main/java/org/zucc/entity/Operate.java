package org.zucc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("operate")
public class Operate implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("systemName")
    private String systemName;
    @TableField("statusActivity")
    private String statusActivity;
    @TableField("subwayA")
    private String subwayA;
    @TableField("subwayB")
    private String subwayB;
    @TableField("subwayC")
    private String subwayC;
    @TableField("subwayD")
    private String subwayD;
    @TableField("busA")
    private String busA;
    @TableField("busB")
    private String busB;
    @TableField("busC")
    private String busC;
    @TableField("busD")
    private String busD;

}
