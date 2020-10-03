package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.SupplierService;
import com.sibecommon.repository.entity.Supplier;
import com.sibecommon.repository.mapper.SupplierMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 * 供应商信息(ml_supplier)  服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {

    @Override
    public  IPage<Supplier> pageSupplier(Page<Supplier> page,Supplier supplier){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<Supplier> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveSupplier(Supplier supplier){
        Assert.notNull(supplier, "供应商信息(ml_supplier) 为空");
        return this.save(supplier);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeSupplier(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeSupplierByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSupplier(Supplier supplier){
        Assert.notNull(supplier, "供应商信息(ml_supplier) 为空");
        return this.updateById(supplier);
    }

    @Override
    public Supplier getSupplierById(String id){
        return  this.getById(id);
    }
}
