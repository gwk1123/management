package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sibecommon.repository.entity.OrderPassenger;

import java.util.List;

/**
 * <p>
 * 乘客信息表 服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface OrderPassengerService extends IService<OrderPassenger> {

    /**
     * 查询乘客信息表分页数据
     *
     * @param page      分页参数
     * @param orderPassenger 查询条件
     * @return IPage<OrderPassenger>
     */
     IPage<OrderPassenger> pageOrderPassenger(Page<OrderPassenger> page,OrderPassenger orderPassenger);

    /**
     * 新增乘客信息表
     *
     * @param orderPassenger 乘客信息表
     * @return boolean
     */
    boolean saveOrderPassenger(OrderPassenger orderPassenger);

    /**
     * 删除乘客信息表
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeOrderPassenger(String id);

    /**
     * 批量删除乘客信息表
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeOrderPassengerByIds(List<String> ids);

    /**
     * 修改乘客信息表
     *
     * @param orderPassenger 乘客信息表
     * @return boolean
     */
    boolean updateOrderPassenger(OrderPassenger orderPassenger);

    /**
     * id查询数据
     *
     * @param id id
     * @return OrderPassenger
     */
    OrderPassenger getOrderPassengerById(String id);
}
