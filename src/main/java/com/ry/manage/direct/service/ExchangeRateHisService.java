package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sibecommon.repository.entity.ExchangeRateHis;

import java.util.List;

/**
 * <p>
 * 实时外汇数据 服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface ExchangeRateHisService extends IService<ExchangeRateHis> {

    /**
     * 查询实时外汇数据分页数据
     *
     * @param page      分页参数
     * @param exchangeRateHis 查询条件
     * @return IPage<ExchangeRateHis>
     */
     IPage<ExchangeRateHis> pageExchangeRateHis(Page<ExchangeRateHis> page,ExchangeRateHis exchangeRateHis);

    /**
     * 新增实时外汇数据
     *
     * @param exchangeRateHis 实时外汇数据
     * @return boolean
     */
    boolean saveExchangeRateHis(ExchangeRateHis exchangeRateHis);

    /**
     * 删除实时外汇数据
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeExchangeRateHis(String id);

    /**
     * 批量删除实时外汇数据
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeExchangeRateHisByIds(List<String> ids);

    /**
     * 修改实时外汇数据
     *
     * @param exchangeRateHis 实时外汇数据
     * @return boolean
     */
    boolean updateExchangeRateHis(ExchangeRateHis exchangeRateHis);

    /**
     * id查询数据
     *
     * @param id id
     * @return ExchangeRateHis
     */
    ExchangeRateHis getExchangeRateHisById(String id);
}
