package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.entity.RouteConfig;

import java.util.List;

/**
 * <p>
 * 航线路由信息(ml_route_config)  服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface RouteConfigService extends IService<RouteConfig> {

    /**
     * 查询航线路由信息(ml_route_config) 分页数据
     *
     * @param page      分页参数
     * @param routeConfig 查询条件
     * @return IPage<RouteConfig>
     */
     IPage<RouteConfig> pageRouteConfig(Page<RouteConfig> page,RouteConfig routeConfig);

    /**
     * 新增航线路由信息(ml_route_config)
     *
     * @param routeConfig 航线路由信息(ml_route_config)
     * @return boolean
     */
    boolean saveRouteConfig(RouteConfig routeConfig);

    /**
     * 删除航线路由信息(ml_route_config)
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeRouteConfig(String id);

    /**
     * 批量删除航线路由信息(ml_route_config)
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeRouteConfigByIds(List<String> ids);

    /**
     * 修改航线路由信息(ml_route_config)
     *
     * @param routeConfig 航线路由信息(ml_route_config)
     * @return boolean
     */
    boolean updateRouteConfig(RouteConfig routeConfig);

    /**
     * id查询数据
     *
     * @param id id
     * @return RouteConfig
     */
    RouteConfig getRouteConfigById(String id);

    /**
     * 更改状态
     * @param routeConfig
     * @return
     */
    boolean changeRouteConfigStatus(RouteConfig routeConfig);
}
