package com.ry.manage.direct.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.common.CommonResult;
import com.ry.manage.direct.service.RouteConfigService;
import com.sibecommon.repository.entity.RouteConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 航线路由信息前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"航线路由信息"})
@RestController
@RequestMapping("/direct")
public class RouteConfigController {

    private final RouteConfigService outeConfigService;

    public RouteConfigController(RouteConfigService outeConfigService) {
        this.outeConfigService = outeConfigService;
    }

    @ApiOperation(value = "新增航线路由信息")
    @PostMapping("/route_config")
    public CommonResult saveRouteConfig(@RequestBody RouteConfig routeConfig) {
        return CommonResult.success(outeConfigService.saveRouteConfig(routeConfig));
    }

    @ApiOperation(value = "删除航线路由信息")
    @DeleteMapping("/route_config/{id}")
    public CommonResult removeRouteConfig(@PathVariable("id") String id) {
        return CommonResult.success(outeConfigService.removeRouteConfig(id));
    }

    @ApiOperation(value = "批量删除航线路由信息")
    @DeleteMapping("/routeConfigs")
    public CommonResult removeRouteConfigByIds(@RequestBody List<String> ids) {
        return CommonResult.success(outeConfigService.removeRouteConfigByIds(ids));
    }


    @ApiOperation(value = "更新航线路由信息")
    @PutMapping("/route_config")
    public CommonResult updateRouteConfig(@RequestBody RouteConfig routeConfig) {
        return CommonResult.success(outeConfigService.updateRouteConfig(routeConfig));
    }

    @ApiOperation(value = "查询航线路由信息分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页参数"),
            @ApiImplicitParam(name = "routeConfig", value = "查询条件")
    })
    @GetMapping("/route_config/page")
    public CommonResult pageRouteConfig(Page<RouteConfig> page, RouteConfig routeConfig) {
        IPage<RouteConfig> iPage = outeConfigService.pageRouteConfig(page, routeConfig);
        return CommonResult.success(iPage);
    }

    @ApiOperation(value = "id查询航线路由信息")
    @GetMapping("/route_config/{id}")
    public CommonResult getRouteConfigById(@PathVariable String id) {
        return CommonResult.success(outeConfigService.getRouteConfigById(id));
    }

    @ApiOperation(value = "根据id更改路由状态")
    @PutMapping("/route_config/changeStatus")
    public CommonResult changeRouteConfigStatus(@RequestBody RouteConfig routeConfig) {
        return CommonResult.success(outeConfigService.changeRouteConfigStatus(routeConfig));
    }

}
