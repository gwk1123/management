package com.ry.manage.direct.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SiteInfoVo {

    private String adultPriceSite;
    private String adultTaxSite;
    private String childPriceSite;
    private String childTaxSite;

    private String gds;
    private String gdsPcc;

    /**
     * ota行李额信息
     */
    private String baggageInfo;
}
