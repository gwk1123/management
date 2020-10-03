package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.RouteConfigService;
import com.sibecommon.repository.entity.RouteConfig;
import com.sibecommon.repository.mapper.RouteConfigMapper;
import com.sibecommon.utils.constant.DirectConstants;
import com.sibecommon.utils.redis.impl.RouteConfigRepositoryImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RouteConfigRepositoryImpl routeConfigRepository;

    @Override
    public  IPage<RouteConfig> pageRouteConfig(Page<RouteConfig> page,RouteConfig routeConfig){
        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<RouteConfig> queryWrapper =this.buildQueryWrapper(routeConfig);
        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveRouteConfig(RouteConfig routeConfig){
        Assert.notNull(routeConfig, "航线路由信息为空");
        this.save(routeConfig);
        if(DirectConstants.NORMAL.equals(routeConfig.getStatus())){
            routeConfigRepository.saveOrUpdateCache(routeConfig);
        }else {
            routeConfigRepository.delete(routeConfig);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeRouteConfig(String id){
        Assert.hasText(id, "主键为空");
        RouteConfig routeConfig =this.getById(id);
        routeConfig.setStatus(DirectConstants.DELETE);
        this.updateById(routeConfig);
        routeConfigRepository.delete(routeConfig);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeRouteConfigByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        ids.stream().forEach(e ->{
            removeRouteConfig(e);
        });
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRouteConfig(RouteConfig routeConfig){
        Assert.notNull(routeConfig, "航线路由信息(ml_route_config) 为空");
        this.updateById(routeConfig);
        if(DirectConstants.NORMAL.equals(routeConfig.getStatus())){
            routeConfigRepository.saveOrUpdateCache(routeConfig);
        }else {
            routeConfigRepository.delete(routeConfig);
        }
        return true;
    }

    @Override
    public boolean changeRouteConfigStatus(RouteConfig routeConfig){
        RouteConfig status = this.getById(routeConfig.getId());
        status.setStatus(routeConfig.getStatus());
        this.updateById(status);
        if(DirectConstants.NORMAL.equals(status.getStatus())){
            routeConfigRepository.saveOrUpdateCache(status);
        }else {
            routeConfigRepository.delete(status);
        }
        return true;
    }

    @Override
    public RouteConfig getRouteConfigById(String id){
        return  this.getById(id);
    }

    public QueryWrapper<RouteConfig> buildQueryWrapper(RouteConfig routeConfig){
        QueryWrapper<RouteConfig> routeConfigQueryWrapper=new QueryWrapper<>();
        if(routeConfig != null){
            routeConfigQueryWrapper.lambda().like(StringUtils.isNotBlank(routeConfig.getOrigin()), RouteConfig::getOrigin,routeConfig.getOrigin())
                    .like(StringUtils.isNotBlank(routeConfig.getDestination()),RouteConfig::getDestination,routeConfig.getDestination());
        }
        if(routeConfig != null && StringUtils.isNotBlank(routeConfig.getStatus())){
            routeConfigQueryWrapper.lambda().eq(StringUtils.isNotBlank(routeConfig.getStatus()),RouteConfig::getStatus,routeConfig.getStatus());
        }else {
            routeConfigQueryWrapper.and(wrapper -> wrapper.lambda().eq(RouteConfig::getStatus, DirectConstants.NORMAL).or().eq(RouteConfig::getStatus,DirectConstants.FAILURE));
        }
        routeConfigQueryWrapper.lambda().orderByAsc(RouteConfig::getCreateTime);
        return routeConfigQueryWrapper;
    }

}
