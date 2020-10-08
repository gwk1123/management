package com.ry.manage.direct.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class GdsSearchVo {

    private LocalGdsSearchVo localGdsSearchVo;

    /**
     * Key GDS航段信息拼接的key
     */
    private Map<String,LocalSiteSearchVo> localSiteSearchVoMap;

}
