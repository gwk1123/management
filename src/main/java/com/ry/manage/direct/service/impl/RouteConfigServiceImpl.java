package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.entity.RouteConfig;
import com.ry.manage.direct.mapper.RouteConfigMapper;
import com.ry.manage.direct.service.RouteConfigService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 * 航线路由信息(ml_route_config)  服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class RouteConfigServiceImpl extends ServiceImpl<RouteConfigMapper, RouteConfig> implements RouteConfigService {

    @Override
    public  IPage<RouteConfig> pageRouteConfig(Page<RouteConfig> page,RouteConfig routeConfig){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<RouteConfig> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRouteConfig(RouteConfig routeConfig){
        Assert.notNull(routeConfig, "航线路由信息(ml_route_config) 为空");
        return this.save(routeConfig);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeRouteConfig(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeRouteConfigByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRouteConfig(RouteConfig routeConfig){
        Assert.notNull(routeConfig, "航线路由信息(ml_route_config) 为空");
        return this.updateById(routeConfig);
    }

    @Override
    public RouteConfig getRouteConfigById(String id){
        return  this.getById(id);
    }
}
