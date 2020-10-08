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

    private BigDecimal adultPriceGds;
    private BigDecimal adultTaxGds;
    private BigDecimal childPriceGds;
    private BigDecimal childTaxGds;
    /**
     * GDS行李额信息
     */
    private String baggageInfo;

}
