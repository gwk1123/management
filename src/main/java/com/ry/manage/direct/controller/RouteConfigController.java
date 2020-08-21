package com.ry.manage.direct.controller;

import com.ry.manage.direct.entity.RouteConfig;
import com.ry.manage.direct.service.RouteConfigService;
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
 * 航线路由信息(ml_route_config)  前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"航线路由信息(ml_route_config) "})
@RestController
@RequestMapping("/gwk/route-config")
public class RouteConfigController {

    private final RouteConfigService outeConfigService;

    public RouteConfigController (RouteConfigService outeConfigService){this.outeConfigService = outeConfigService;}

    @ApiOperation(value = "新增航线路由信息(ml_route_config) ")
    @PostMapping("/routeConfig")
    public boolean saveRouteConfig(@RequestBody RouteConfig routeConfig){
    return outeConfigService.saveRouteConfig(routeConfig);
    }

    @ApiOperation(value = "删除航线路由信息(ml_route_config) ")
    @DeleteMapping("/routeConfig/{id}")
    public boolean removeRouteConfig(@PathVariable("id") String id){
    return outeConfigService.removeRouteConfig(id);
    }

    @ApiOperation(value = "批量删除航线路由信息(ml_route_config) ")
    @DeleteMapping("/routeConfigs")
    public boolean removeRouteConfigByIds(@RequestBody List <String> ids){
        return outeConfigService.removeRouteConfigByIds(ids);
        }


        @ApiOperation(value = "更新航线路由信息(ml_route_config) ")
        @PutMapping("/routeConfig")
        public boolean updateRouteConfig(@RequestBody RouteConfig routeConfig){
        return outeConfigService.updateRouteConfig(routeConfig);
        }

        @ApiOperation(value = "查询航线路由信息(ml_route_config) 分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "routeConfig", value = "查询条件")
        })
        @GetMapping("/routeConfig/page")
        public IPage<RouteConfig> pageRouteConfig(Page<RouteConfig> page,RouteConfig routeConfig){
        return outeConfigService.pageRouteConfig(page, routeConfig);
        }

        @ApiOperation(value = "id查询航线路由信息(ml_route_config) ")
        @GetMapping("/routeConfig/{id}")
        public RouteConfig getRouteConfigById(@PathVariable String id){
        return outeConfigService.getRouteConfigById(id);
        }

        }
