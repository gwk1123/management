package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sibecommon.repository.entity.OrderPassenger;
import com.sibecommon.repository.mapper.OrderPassengerMapper;
import com.ry.manage.direct.service.OrderPassengerService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 * 乘客信息表 服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class OrderPassengerServiceImpl extends ServiceImpl<OrderPassengerMapper, OrderPassenger> implements OrderPassengerService {

    @Override
    public  IPage<OrderPassenger> pageOrderPassenger(Page<OrderPassenger> page,OrderPassenger orderPassenger){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<OrderPassenger> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrderPassenger(OrderPassenger orderPassenger){
        Assert.notNull(orderPassenger, "乘客信息表为空");
        return this.save(orderPassenger);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOrderPassenger(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOrderPassengerByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOrderPassenger(OrderPassenger orderPassenger){
        Assert.notNull(orderPassenger, "乘客信息表为空");
        return this.updateById(orderPassenger);
    }

    @Override
    public OrderPassenger getOrderPassengerById(String id){
        return  this.getById(id);
    }
}
