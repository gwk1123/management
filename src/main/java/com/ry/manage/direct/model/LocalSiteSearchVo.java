package com.ry.manage.direct.model;

import lombok.Data;

import java.util.List;

/**
 * @author gwk
 */
@Data
public class LocalSiteSearchVo {

    /**
     * GDS航段信息拼接的key
     */
//    private String flightInfoKey;
    /**
     * GDS航段信息
     */
    private SegmentInfo segmentInfo;
    /**
     * GDS信息
     */
    List<GdsInfoVo> gdsInfoVos;
    /**
     * 站点信息
     */
    List<SiteInfoVo> siteInfoVos;
}
