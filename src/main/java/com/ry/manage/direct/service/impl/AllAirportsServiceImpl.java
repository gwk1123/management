package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.AllAirportsService;
import com.sibecommon.repository.entity.AllAirports;
import com.sibecommon.repository.mapper.AllAirportsMapper;
import com.sibecommon.utils.constant.DirectConstants;
import com.sibecommon.utils.redis.impl.AllAirportRepositoryImpl;
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
 * 所有机场表 服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class AllAirportsServiceImpl extends ServiceImpl<AllAirportsMapper, AllAirports> implements AllAirportsService {

    @Autowired
    private AllAirportRepositoryImpl allAirportRepository;

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
        this.save(allAirports);
        if(DirectConstants.NORMAL.equals(allAirports.getStatus())) {
            allAirportRepository.saveOrUpdateCache(allAirports);
        }else {
            allAirportRepository.delete(allAirports);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeAllAirports(String id){
        Assert.hasText(id, "主键为空");
        AllAirports allAirports=this.getById(id);
        allAirports.setStatus(DirectConstants.DELETE);
        this.updateById(allAirports);
        allAirportRepository.delete(allAirports);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeAllAirportsByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        ids.stream().forEach(e ->{
            removeAllAirports(e);
        });
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAllAirports(AllAirports allAirports){
        Assert.notNull(allAirports, "所有机场表为空");
        this.updateById(allAirports);
        if(DirectConstants.NORMAL.equals(allAirports.getStatus())) {
            allAirportRepository.saveOrUpdateCache(allAirports);
        }else {
            allAirportRepository.delete(allAirports);
        }
        return true;
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
            .eq(StringUtils.isNotBlank(allAirports.getCode()),AllAirports::getCode,allAirports.getCode())
                    .like(StringUtils.isNotBlank(allAirports.getCountry()),AllAirports::getCountry,allAirports.getCountry())
                    .eq(StringUtils.isNotBlank(allAirports.getGcode()),AllAirports::getGcode,allAirports.getGcode());
        }
        gdsQueryWrapper.lambda().orderByAsc(AllAirports::getCreateTime);
        return gdsQueryWrapper;
    }

}
