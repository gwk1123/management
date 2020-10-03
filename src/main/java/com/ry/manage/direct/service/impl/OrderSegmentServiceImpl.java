package com.ry.manage.direct.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.OrderSegmentService;
import com.sibecommon.repository.entity.OrderSegment;
import com.sibecommon.repository.mapper.OrderSegmentMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 * 航段信息表 服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class OrderSegmentServiceImpl extends ServiceImpl<OrderSegmentMapper, OrderSegment> implements OrderSegmentService {

    @Override
    public  IPage<OrderSegment> pageOrderSegment(Page<OrderSegment> page, OrderSegment orderSegment){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<OrderSegment> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrderSegment(OrderSegment orderSegment){
        Assert.notNull(orderSegment, "航段信息表为空");
        return this.save(orderSegment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOrderSegment(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOrderSegmentByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOrderSegment(OrderSegment orderSegment){
        Assert.notNull(orderSegment, "航段信息表为空");
        return this.updateById(orderSegment);
    }

    @Override
    public OrderSegment getOrderSegmentById(String id){
        return  this.getById(id);
    }
}
