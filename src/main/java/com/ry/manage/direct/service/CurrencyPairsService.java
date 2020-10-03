package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sibecommon.repository.entity.CurrencyPairs;

import java.util.List;

/**
 * <p>
 * 实时外汇数据 服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface CurrencyPairsService extends IService<CurrencyPairs> {

    /**
     * 查询实时外汇数据分页数据
     *
     * @param page      分页参数
     * @param currencyPairs 查询条件
     * @return IPage<CurrencyPairs>
     */
     IPage<CurrencyPairs> pageCurrencyPairs(Page<CurrencyPairs> page,CurrencyPairs currencyPairs);

    /**
     * 新增实时外汇数据
     *
     * @param currencyPairs 实时外汇数据
     * @return boolean
     */
    boolean saveCurrencyPairs(CurrencyPairs currencyPairs);

    /**
     * 删除实时外汇数据
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeCurrencyPairs(String id);

    /**
     * 批量删除实时外汇数据
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeCurrencyPairsByIds(List<String> ids);

    /**
     * 修改实时外汇数据
     *
     * @param currencyPairs 实时外汇数据
     * @return boolean
     */
    boolean updateCurrencyPairs(CurrencyPairs currencyPairs);

    /**
     * id查询数据
     *
     * @param id id
     * @return CurrencyPairs
     */
    CurrencyPairs getCurrencyPairsById(String id);
}
