package com.ry.manage.direct.model;

import com.sibecommon.ota.gds.Routing;
import com.sibecommon.ota.site.SibeRouting;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class GdsInfoVo {

    /**
     * GDS方案
     */
    private SibeRouting sibeRouting;

    private String adultPriceGds;
    private String adultTaxGds;
    private String childPriceGds;
    private String childTaxGds;
    private String gds;
    private String pcc;
    /**
     * GDS行李额信息
     */
    private String baggageInfo;

}
