package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sibecommon.repository.entity.OrderData;

import java.util.List;

/**
 * <p>
 * data信息表 服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface OrderDataService extends IService<OrderData> {

    /**
     * 查询data信息表分页数据
     *
     * @param page      分页参数
     * @param orderData 查询条件
     * @return IPage<OrderData>
     */
     IPage<OrderData> pageOrderData(Page<OrderData> page,OrderData orderData);

    /**
     * 新增data信息表
     *
     * @param orderData data信息表
     * @return boolean
     */
    boolean saveOrderData(OrderData orderData);

    /**
     * 删除data信息表
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeOrderData(String id);

    /**
     * 批量删除data信息表
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeOrderDataByIds(List<String> ids);

    /**
     * 修改data信息表
     *
     * @param orderData data信息表
     * @return boolean
     */
    boolean updateOrderData(OrderData orderData);

    /**
     * id查询数据
     *
     * @param id id
     * @return OrderData
     */
    OrderData getOrderDataById(String id);
}
