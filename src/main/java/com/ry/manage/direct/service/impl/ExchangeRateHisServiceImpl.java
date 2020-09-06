package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.ExchangeRateHisService;
import comm.repository.entity.ExchangeRateHis;
import comm.repository.mapper.ExchangeRateHisMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 * 实时外汇数据 服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class ExchangeRateHisServiceImpl extends ServiceImpl<ExchangeRateHisMapper, ExchangeRateHis> implements ExchangeRateHisService {

    @Override
    public  IPage<ExchangeRateHis> pageExchangeRateHis(Page<ExchangeRateHis> page, ExchangeRateHis exchangeRateHis){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<ExchangeRateHis> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveExchangeRateHis(ExchangeRateHis exchangeRateHis){
        Assert.notNull(exchangeRateHis, "实时外汇数据为空");
        return this.save(exchangeRateHis);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeExchangeRateHis(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeExchangeRateHisByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateExchangeRateHis(ExchangeRateHis exchangeRateHis){
        Assert.notNull(exchangeRateHis, "实时外汇数据为空");
        return this.updateById(exchangeRateHis);
    }

    @Override
    public ExchangeRateHis getExchangeRateHisById(String id){
        return  this.getById(id);
    }
}
