package org.zucc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("login_record")
public class Record {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "userName")
    private String userName;
    @TableField(value = "systemName")
    private String systemName;
    @TableField(value = "loginTime")
    private Timestamp loginTime;
    @TableField(value = "logoutTime")
    private Timestamp logoutTime;
}
