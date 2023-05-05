package org.zucc.entity.vo;

import lombok.Data;

@Data
public class DeployVo {
    private String systemName;
    private double lng;
    private double lat;
    private String role;
    private int num;
}
