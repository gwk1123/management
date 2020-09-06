package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.OrderDataService;
import comm.repository.entity.OrderData;
import comm.repository.mapper.OrderDataMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 * data信息表 服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class OrderDataServiceImpl extends ServiceImpl<OrderDataMapper, OrderData> implements OrderDataService {

    @Override
    public  IPage<OrderData> pageOrderData(Page<OrderData> page, OrderData orderData){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<OrderData> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrderData(OrderData orderData){
        Assert.notNull(orderData, "data信息表为空");
        return this.save(orderData);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOrderData(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOrderDataByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOrderData(OrderData orderData){
        Assert.notNull(orderData, "data信息表为空");
        return this.updateById(orderData);
    }

    @Override
    public OrderData getOrderDataById(String id){
        return  this.getById(id);
    }
}
