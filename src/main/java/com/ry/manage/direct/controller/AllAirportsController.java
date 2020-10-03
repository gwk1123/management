package com.ry.manage.direct.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.common.CommonResult;
import com.ry.manage.direct.service.AllAirportsService;
import com.sibecommon.repository.entity.AllAirports;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 所有机场表 前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"所有机场表"})
@RestController
@RequestMapping("/direct")
public class AllAirportsController {

    private final AllAirportsService allAirportsService;

    public AllAirportsController(AllAirportsService allAirportsService) {
        this.allAirportsService = allAirportsService;
    }

    @ApiOperation(value = "新增所有机场表")
    @PostMapping("/all_airports")
    public CommonResult saveAllAirports(@RequestBody AllAirports allAirports) {
        return CommonResult.success(allAirportsService.saveAllAirports(allAirports));
    }

    @ApiOperation(value = "删除所有机场表")
    @DeleteMapping("/all_airports/{id}")
    public CommonResult removeAllAirports(@PathVariable("id") String id) {
        return CommonResult.success(allAirportsService.removeAllAirports(id));
    }

    @ApiOperation(value = "批量删除所有机场表")
    @DeleteMapping("/all_airportss")
    public CommonResult removeAllAirportsByIds(@RequestBody List<String> ids) {
        return CommonResult.success(allAirportsService.removeAllAirportsByIds(ids));
    }


    @ApiOperation(value = "更新机场表")
    @PutMapping("/all_airports")
    public CommonResult updateAllAirports(@RequestBody AllAirports allAirports) {
        return CommonResult.success(allAirportsService.updateAllAirports(allAirports));
    }

    @ApiOperation(value = "查询所有机场表分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页参数"),
            @ApiImplicitParam(name = "allAirports", value = "查询条件")
    })
    @GetMapping("/all_airports/page")
    public CommonResult pageAllAirports(Page<AllAirports> page, AllAirports allAirports) {
        IPage<AllAirports> iPage= allAirportsService.pageAllAirports(page, allAirports);
        return CommonResult.success(iPage);
    }

    @ApiOperation(value = "id查询所有机场表")
    @GetMapping("/all_airports/{id}")
    public CommonResult getAllAirportsById(@PathVariable String id) {
        return CommonResult.success(allAirportsService.getAllAirportsById(id));
    }

}
