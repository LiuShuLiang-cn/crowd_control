package org.zucc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@TableName("user_score")
public class Score implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    @TableField(value = "systemName")
    private String systemName;
    @TableField(value = "role")
    private String role;
    @TableField(value = "username")
    private String username;
    @TableField(value = "score")
    private int score;
}
