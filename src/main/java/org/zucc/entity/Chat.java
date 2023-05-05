package org.zucc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("chat")
public class Chat implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "systemName")
    private String systemName;

    @TableField(value = "fromRole")
    private String fromRole;

    @TableField(value = "toRole")
    private String toRole;

    @TableField(value = "text")
    private String text;

    @TableField(value = "type")
    private String type;
    @TableField(value = "statue")
    private int statue;

    @TableField(value = "time")
    private String time;
}
