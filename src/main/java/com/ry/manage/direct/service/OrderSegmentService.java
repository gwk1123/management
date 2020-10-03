package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sibecommon.repository.entity.OrderSegment;

import java.util.List;

/**
 * <p>
 * 航段信息表 服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface OrderSegmentService extends IService<OrderSegment> {

    /**
     * 查询航段信息表分页数据
     *
     * @param page      分页参数
     * @param orderSegment 查询条件
     * @return IPage<OrderSegment>
     */
     IPage<OrderSegment> pageOrderSegment(Page<OrderSegment> page,OrderSegment orderSegment);

    /**
     * 新增航段信息表
     *
     * @param orderSegment 航段信息表
     * @return boolean
     */
    boolean saveOrderSegment(OrderSegment orderSegment);

    /**
     * 删除航段信息表
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeOrderSegment(String id);

    /**
     * 批量删除航段信息表
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeOrderSegmentByIds(List<String> ids);

    /**
     * 修改航段信息表
     *
     * @param orderSegment 航段信息表
     * @return boolean
     */
    boolean updateOrderSegment(OrderSegment orderSegment);

    /**
     * id查询数据
     *
     * @param id id
     * @return OrderSegment
     */
    OrderSegment getOrderSegmentById(String id);
}
