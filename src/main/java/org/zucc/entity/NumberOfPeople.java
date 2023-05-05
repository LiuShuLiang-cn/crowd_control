package org.zucc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("number_of_people")
public class NumberOfPeople implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "region")
    private String region;

    @TableField(value = "number")
    private Integer number;

    @TableField(value = "longitude")
    private String longitude;

    @TableField(value = "latitude")
    private String latitude;

    @TableField(value = "area")
    private Integer area;

    @TableField(value = "vicinity")
    private String vicinity;

    @TableField(value = "status")
    private String status;

    @TableField("systemName")
    private String systemName;
}
