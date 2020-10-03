package com.ry.manage.direct.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.common.CommonResult;
import com.ry.manage.direct.service.GdsService;
import com.sibecommon.repository.entity.Gds;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * GDS信息 前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"GDS信息"})
@RestController
@RequestMapping("/direct")
public class GdsController {

    private final GdsService dsService;

    public GdsController(GdsService dsService) {
        this.dsService = dsService;
    }

    @ApiOperation(value = "新增GDS信息")
    @PostMapping("/gds")
    public CommonResult saveGds(@RequestBody Gds gds) {
        return CommonResult.success(dsService.saveGds(gds));
    }

    @ApiOperation(value = "删除GDS信息")
    @DeleteMapping("/gds/{id}")
    public CommonResult removeGds(@PathVariable("id") String id) {
        return CommonResult.success(dsService.removeGds(id));
    }


    @ApiOperation(value = "更新GDS信息")
    @PutMapping("/gds")
    public CommonResult updateGds(@RequestBody Gds gds) {
        return CommonResult.success(dsService.updateGds(gds));
    }

    @ApiOperation(value = "查询GDS信息分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页参数"),
            @ApiImplicitParam(name = "gds", value = "查询条件")
    })
    @GetMapping("/gds/page")
    public CommonResult pageGds(Page<Gds> page, Gds gds) {
        IPage<Gds> iPage = dsService.pageGds(page, gds);
        CommonResult commonResult = CommonResult.success();
        commonResult.put("total", iPage.getTotal());
        commonResult.put("rows", iPage.getRecords());
        return commonResult;
    }

    @ApiOperation(value = "id查询GDS信息")
    @GetMapping("/gds/{id}")
    public CommonResult getGdsById(@PathVariable String id) {
        return CommonResult.success(dsService.getGdsById(id));
    }

    @ApiOperation(value = "根据id更改GDS状态")
    @PutMapping("/gds/changeStatus")
    public CommonResult changeGdsStatus(@RequestBody Gds gds) {
        return CommonResult.success(dsService.changeGdsStatus(gds));
    }

}
