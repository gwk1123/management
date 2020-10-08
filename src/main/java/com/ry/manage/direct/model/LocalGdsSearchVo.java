package com.ry.manage.direct.model;


import lombok.Data;

import java.util.Set;

@Data
public class LocalGdsSearchVo {

    //航司
    private Set<String> carriers;

    //GDS
    private Set<String> gdsSource;

    //航班号
    private Set<String> flightNumber;

    //飞行类型
    private Set<Integer> flightType;
}
