package com.ry.manage.direct.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SiteInfoVo {

    private String adultPriceOta;
    private String adultTaxOta;
    private String childPriceOta;
    private String childTaxOta;

    private String gds;
    private String gdsPcc;

    /**
     * ota行李额信息
     */
    private String baggageInfo;
}
