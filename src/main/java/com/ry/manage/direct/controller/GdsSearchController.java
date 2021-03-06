package com.ry.manage.direct.controller;


import com.ry.manage.common.CommonResult;
import com.ry.manage.direct.model.GdsSearchVm;
import com.ry.manage.direct.service.GdsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * @author gwk
 */
@RestController
@RequestMapping("/direct")
public class GdsSearchController {

    @Autowired
    private GdsSearchService gdsSearchService;

    @PostMapping(value = "/gds_search")
    public CommonResult findGdsSearch(@RequestBody GdsSearchVm gdsSearchVm) throws InterruptedException {
        Set<String> otaSiteSet = new HashSet<>();
        otaSiteSet.add("CT001");
        otaSiteSet.add("CT002");
        gdsSearchVm.setOtaSites(otaSiteSet);
        return CommonResult.success(gdsSearchService.findGdsSearch(gdsSearchVm));
    }
}
