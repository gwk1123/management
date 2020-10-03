package com.ry.manage.direct.controller;

import com.ry.manage.common.CommonResult;
import com.ry.manage.direct.service.OtaService;
import com.sibecommon.repository.entity.Ota;
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
 * OTA平台信息 前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"OTA平台信息"})
@RestController
@RequestMapping("/direct")
public class OtaController {

    private final OtaService otaService;

    public OtaController (OtaService otaService){this.otaService = otaService;}

    @ApiOperation(value = "新增OTA平台信息")
    @PostMapping("/ota")
    public CommonResult saveOta(@RequestBody Ota ota){
    return CommonResult.success(otaService.saveOta(ota));
    }

    @ApiOperation(value = "删除OTA平台信息")
    @DeleteMapping("/ota/{id}")
    public CommonResult removeOta(@PathVariable("id") String id){
    return CommonResult.success(otaService.removeOta(id));
    }

    @ApiOperation(value = "批量删除OTA平台信息")
    @DeleteMapping("/otas")
    public CommonResult removeOtaByIds(@RequestBody List <String> ids){
        return CommonResult.success(otaService.removeOtaByIds(ids));
        }


        @ApiOperation(value = "更新OTA平台信息")
        @PutMapping("/ota")
        public CommonResult updateOta(@RequestBody Ota ota){
        return CommonResult.success(otaService.updateOta(ota));
        }

        @ApiOperation(value = "查询OTA平台信息分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "ota", value = "查询条件")
        })
        @GetMapping("/ota/page")
        public CommonResult pageOta(Page<Ota> page,Ota ota){
            IPage<Ota> iPage =  otaService.pageOta(page, ota);
            return CommonResult.success(iPage);
        }

        @ApiOperation(value = "id查询OTA平台信息")
        @GetMapping("/ota/{id}")
        public CommonResult getOtaById(@PathVariable String id){
        return CommonResult.success(otaService.getOtaById(id));
        }

    @ApiOperation(value = "根据id更改GDS状态")
    @PutMapping("/ota/changeStatus")
    public CommonResult changeOtaStatus(@RequestBody Ota ota) {
        return CommonResult.success(otaService.changeOtaStatus(ota));
    }

        }
