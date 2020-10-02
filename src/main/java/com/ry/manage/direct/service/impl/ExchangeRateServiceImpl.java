package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.ExchangeRateService;
import comm.repository.entity.ExchangeRate;
import comm.repository.mapper.ExchangeRateMapper;
import comm.utils.constant.DirectConstants;
import comm.utils.redis.impl.ExchangeRateRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;

import javax.xml.ws.Action;


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

    @Autowired
    private ExchangeRateRepositoryImpl exchangeRateRepository;

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
        this.save(exchangeRate);
        if(DirectConstants.NORMAL.equals(exchangeRate.getStatus())) {
            exchangeRateRepository.saveOrUpdateCache(exchangeRate);
        }else {
            exchangeRateRepository.delete(exchangeRate);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeExchangeRate(String id){
        Assert.hasText(id, "主键为空");
        ExchangeRate exchangeRate = this.getById(id);
        exchangeRate.setStatus(DirectConstants.DELETE);
        this.updateById(exchangeRate);
        exchangeRateRepository.delete(exchangeRate);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeExchangeRateByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        ids.stream().forEach(e ->{
            removeExchangeRate(e);
        });
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateExchangeRate(ExchangeRate exchangeRate){
        Assert.notNull(exchangeRate, "实时外汇数据为空");
        this.updateById(exchangeRate);
        if(DirectConstants.NORMAL.equals(exchangeRate.getStatus())) {
            exchangeRateRepository.saveOrUpdateCache(exchangeRate);
        }else {
            exchangeRateRepository.delete(exchangeRate);
        }
        return true;
    }

    @Override
    public ExchangeRate getExchangeRateById(String id){
        return  this.getById(id);
    }
}
