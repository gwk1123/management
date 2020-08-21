package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.entity.ExchangeRate;
import com.ry.manage.direct.mapper.ExchangeRateMapper;
import com.ry.manage.direct.service.ExchangeRateService;
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
public class ExchangeRateServiceImpl extends ServiceImpl<ExchangeRateMapper, ExchangeRate> implements ExchangeRateService {

    @Override
    public  IPage<ExchangeRate> pageExchangeRate(Page<ExchangeRate> page,ExchangeRate exchangeRate){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<ExchangeRate> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveExchangeRate(ExchangeRate exchangeRate){
        Assert.notNull(exchangeRate, "实时外汇数据为空");
        return this.save(exchangeRate);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeExchangeRate(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeExchangeRateByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateExchangeRate(ExchangeRate exchangeRate){
        Assert.notNull(exchangeRate, "实时外汇数据为空");
        return this.updateById(exchangeRate);
    }

    @Override
    public ExchangeRate getExchangeRateById(String id){
        return  this.getById(id);
    }
}
