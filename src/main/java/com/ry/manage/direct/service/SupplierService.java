package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.entity.Supplier;

import java.util.List;

/**
 * <p>
 * 供应商信息(ml_supplier)  服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface SupplierService extends IService<Supplier> {

    /**
     * 查询供应商信息(ml_supplier) 分页数据
     *
     * @param page      分页参数
     * @param supplier 查询条件
     * @return IPage<Supplier>
     */
     IPage<Supplier> pageSupplier(Page<Supplier> page,Supplier supplier);

    /**
     * 新增供应商信息(ml_supplier) 
     *
     * @param supplier 供应商信息(ml_supplier) 
     * @return boolean
     */
    boolean saveSupplier(Supplier supplier);

    /**
     * 删除供应商信息(ml_supplier) 
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeSupplier(String id);

    /**
     * 批量删除供应商信息(ml_supplier) 
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeSupplierByIds(List<String> ids);

    /**
     * 修改供应商信息(ml_supplier) 
     *
     * @param supplier 供应商信息(ml_supplier) 
     * @return boolean
     */
    boolean updateSupplier(Supplier supplier);

    /**
     * id查询数据
     *
     * @param id id
     * @return Supplier
     */
    Supplier getSupplierById(String id);
}
