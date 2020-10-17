package com.ry.manage.direct.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SiteInfoVo {

    private String adultPriceOta;
    private String adultTaxOta;
    private String childPriceOta;
    private String childTaxOta;
    private String site;

    private String policyInfoId;
    private String policyGlobalId;
    private String gds;
    private String pcc;

    /**
     * ota行李额信息
     */
    private String baggageInfo;
}
