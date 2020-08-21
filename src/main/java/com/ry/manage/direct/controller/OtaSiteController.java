package com.ry.manage.direct.controller;

import com.ry.manage.common.CommonResult;
import com.ry.manage.direct.entity.Gds;
import com.ry.manage.direct.entity.GdsPcc;
import com.ry.manage.direct.entity.OtaSite;
import com.ry.manage.direct.service.OtaSiteService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

    import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * OTA平台站点表 前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"OTA平台站点表"})
@RestController
@RequestMapping("/direct")
public class OtaSiteController {

    private final OtaSiteService taSiteService;

    public OtaSiteController (OtaSiteService taSiteService){this.taSiteService = taSiteService;}

    @ApiOperation(value = "新增OTA平台站点表")
    @PostMapping("/ota_site")
    public CommonResult saveOtaSite(@RequestBody OtaSite otaSite){
    return CommonResult.success(taSiteService.saveOtaSite(otaSite));
    }

    @ApiOperation(value = "删除OTA平台站点表")
    @DeleteMapping("/ota_site/{id}")
    public CommonResult removeOtaSite(@PathVariable("id") String id){
    return CommonResult.success(taSiteService.removeOtaSite(id));
    }

    @ApiOperation(value = "批量删除OTA平台站点表")
    @DeleteMapping("/ota_site")
    public CommonResult removeOtaSiteByIds(@RequestBody List <String> ids){
        return CommonResult.success(taSiteService.removeOtaSiteByIds(ids));
        }


        @ApiOperation(value = "更新OTA平台站点表")
        @PutMapping("/ota_site")
        public CommonResult updateOtaSite(@RequestBody OtaSite otaSite){
        return CommonResult.success(taSiteService.updateOtaSite(otaSite));
        }

        @ApiOperation(value = "查询OTA平台站点表分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "otaSite", value = "查询条件")
        })
        @GetMapping("/ota_site/page")
        public CommonResult pageOtaSite(Page<OtaSite> page,OtaSite otaSite){
            IPage<OtaSite> iPage = taSiteService.pageOtaSite(page, otaSite);
            return CommonResult.success(iPage);
        }

        @ApiOperation(value = "id查询OTA平台站点表")
        @GetMapping("/ota_site/{id}")
        public CommonResult getOtaSiteById(@PathVariable String id){
        return CommonResult.success(taSiteService.getOtaSiteById(id));
        }

    @ApiOperation(value = "根据id更改OtaSite状态")
    @PutMapping("/ota_site/changeStatus")
    public CommonResult changeOtaOiteStatus(@RequestBody OtaSite otaSite) {
        return CommonResult.success(taSiteService.changeOtaSiteStatus(otaSite));
    }

        }
