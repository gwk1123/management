package com.ry.manage.direct.model;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class GdsSearchVm {

    private String tripType;

    private String fromCity;

    private String toCity;

    private String fromDate;

    private String retDate;

    private Set<String> otaSites;
}
