package com.ry.manage.direct.controller;


import com.ry.manage.direct.model.GdsSearchVm;
import com.ry.manage.direct.model.GdsSearchVo;
import com.ry.manage.direct.service.GdsSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/direct")
public class GdsSearchController {

    @Autowired
    private GdsSearchService gdsSearchService;

    @RequestMapping(value = "/gdsSearch")
    public GdsSearchVo findGdsSearch(@RequestBody GdsSearchVm gdsSearchVm) throws InterruptedException {
        return gdsSearchService.findGdsSearch(gdsSearchVm);
    }
}
