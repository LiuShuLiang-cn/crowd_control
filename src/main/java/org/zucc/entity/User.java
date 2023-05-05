package org.zucc.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("user")
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "username")
    private String userName;

    @TableField(value = "password")
    private String password;

    @TableField(value = "role")
    private String role;

    @TableField(value = "salt")
    private String salt;

    @TableField(value = "status")
    private int status;
    @TableField(value = "systemname")
    private String systemname;
}
