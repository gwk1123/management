package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.CurrencyPairsService;
import comm.repository.entity.CurrencyPairs;
import comm.repository.mapper.CurrencyPairsMapper;
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
public class CurrencyPairsServiceImpl extends ServiceImpl<CurrencyPairsMapper, CurrencyPairs> implements CurrencyPairsService {

    @Override
    public  IPage<CurrencyPairs> pageCurrencyPairs(Page<CurrencyPairs> page, CurrencyPairs currencyPairs){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<CurrencyPairs> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveCurrencyPairs(CurrencyPairs currencyPairs){
        Assert.notNull(currencyPairs, "实时外汇数据为空");
        return this.save(currencyPairs);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeCurrencyPairs(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeCurrencyPairsByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCurrencyPairs(CurrencyPairs currencyPairs){
        Assert.notNull(currencyPairs, "实时外汇数据为空");
        return this.updateById(currencyPairs);
    }

    @Override
    public CurrencyPairs getCurrencyPairsById(String id){
        return  this.getById(id);
    }
}
