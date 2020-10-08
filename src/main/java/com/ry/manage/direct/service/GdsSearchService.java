package com.ry.manage.direct.service;

import com.ry.manage.direct.model.GdsSearchVm;
import com.ry.manage.direct.model.GdsSearchVo;
import org.springframework.web.bind.annotation.RequestBody;

public interface GdsSearchService {
    GdsSearchVo findGdsSearch( GdsSearchVm gdsSearchVm) throws InterruptedException;
}
