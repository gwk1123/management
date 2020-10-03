package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sibecommon.repository.entity.ExchangeRate;

import java.util.List;

/**
 * <p>
 * 实时外汇数据 服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface ExchangeRateService extends IService<ExchangeRate> {

    /**
     * 查询实时外汇数据分页数据
     *
     * @param page      分页参数
     * @param exchangeRate 查询条件
     * @return IPage<ExchangeRate>
     */
     IPage<ExchangeRate> pageExchangeRate(Page<ExchangeRate> page,ExchangeRate exchangeRate);

    /**
     * 新增实时外汇数据
     *
     * @param exchangeRate 实时外汇数据
     * @return boolean
     */
    boolean saveExchangeRate(ExchangeRate exchangeRate);

    /**
     * 删除实时外汇数据
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeExchangeRate(String id);

    /**
     * 批量删除实时外汇数据
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeExchangeRateByIds(List<String> ids);

    /**
     * 修改实时外汇数据
     *
     * @param exchangeRate 实时外汇数据
     * @return boolean
     */
    boolean updateExchangeRate(ExchangeRate exchangeRate);

    /**
     * id查询数据
     *
     * @param id id
     * @return ExchangeRate
     */
    ExchangeRate getExchangeRateById(String id);
}
