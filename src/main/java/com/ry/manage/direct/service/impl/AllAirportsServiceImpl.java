package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.common.constant.DirectConstants;
import com.ry.manage.direct.entity.AllAirports;
import com.ry.manage.direct.entity.GdsPcc;
import com.ry.manage.direct.mapper.AllAirportsMapper;
import com.ry.manage.direct.service.AllAirportsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 * 所有机场表 服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class AllAirportsServiceImpl extends ServiceImpl<AllAirportsMapper, AllAirports> implements AllAirportsService {

    @Override
    public  IPage<AllAirports> pageAllAirports(Page<AllAirports> page,AllAirports allAirports){
        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<AllAirports> queryWrapper = this.buildQueryWrapper(allAirports);
        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveAllAirports(AllAirports allAirports){
        Assert.notNull(allAirports, "所有机场表为空");
        return this.save(allAirports);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeAllAirports(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeAllAirportsByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAllAirports(AllAirports allAirports){
        Assert.notNull(allAirports, "所有机场表为空");
        return this.updateById(allAirports);
    }

    @Override
    public AllAirports getAllAirportsById(String id){
        return  this.getById(id);
    }


    public QueryWrapper<AllAirports> buildQueryWrapper(AllAirports allAirports){
        QueryWrapper<AllAirports> gdsQueryWrapper=new QueryWrapper<>();
        if(allAirports != null){
            gdsQueryWrapper.lambda().like(StringUtils.isNotBlank(allAirports.getCity()),AllAirports::getCity,allAirports.getCity())
                    .eq(StringUtils.isNotBlank(allAirports.getCcode()),AllAirports::getCcode,allAirports.getCcode())
            .like(StringUtils.isNotBlank(allAirports.getAirport()),AllAirports::getAirport,allAirports.getAirport())
            .eq(StringUtils.isNotBlank(allAirports.getCode()),AllAirports::getCode,allAirports.getCode());
        }
        gdsQueryWrapper.lambda().orderByAsc(AllAirports::getCreateTime);
        return gdsQueryWrapper;
    }

}
