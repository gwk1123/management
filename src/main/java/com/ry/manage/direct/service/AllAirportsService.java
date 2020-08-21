package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.entity.AllAirports;

import java.util.List;

/**
 * <p>
 * 所有机场表 服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface AllAirportsService extends IService<AllAirports> {

    /**
     * 查询所有机场表分页数据
     *
     * @param page      分页参数
     * @param allAirports 查询条件
     * @return IPage<AllAirports>
     */
     IPage<AllAirports> pageAllAirports(Page<AllAirports> page,AllAirports allAirports);

    /**
     * 新增所有机场表
     *
     * @param allAirports 所有机场表
     * @return boolean
     */
    boolean saveAllAirports(AllAirports allAirports);

    /**
     * 删除所有机场表
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeAllAirports(String id);

    /**
     * 批量删除所有机场表
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeAllAirportsByIds(List<String> ids);

    /**
     * 修改所有机场表
     *
     * @param allAirports 所有机场表
     * @return boolean
     */
    boolean updateAllAirports(AllAirports allAirports);

    /**
     * id查询数据
     *
     * @param id id
     * @return AllAirports
     */
    AllAirports getAllAirportsById(String id);
}
