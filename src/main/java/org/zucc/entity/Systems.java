package org.zucc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("systems")
public class Systems implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @TableField(value = "systemname")
    private String systemName;
    @TableField(value = "username")
    private String username;
    @TableField(value = "speed")
    private int speed;
    @TableField(value = "runtime")
    private String runTime;
}
