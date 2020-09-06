package com.ry.manage.direct.controller;

import com.ry.manage.direct.service.OrderDataService;
import comm.repository.entity.OrderData;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

    import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * data信息表 前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"data信息表"})
@RestController
@RequestMapping("/gwk/order-data")
public class OrderDataController {

    private final OrderDataService rderDataService;

    public OrderDataController (OrderDataService rderDataService){this.rderDataService = rderDataService;}

    @ApiOperation(value = "新增data信息表")
    @PostMapping("/orderData")
    public boolean saveOrderData(@RequestBody OrderData orderData){
    return rderDataService.saveOrderData(orderData);
    }

    @ApiOperation(value = "删除data信息表")
    @DeleteMapping("/orderData/{id}")
    public boolean removeOrderData(@PathVariable("id") String id){
    return rderDataService.removeOrderData(id);
    }

    @ApiOperation(value = "批量删除data信息表")
    @DeleteMapping("/orderDatas")
    public boolean removeOrderDataByIds(@RequestBody List <String> ids){
        return rderDataService.removeOrderDataByIds(ids);
        }


        @ApiOperation(value = "更新data信息表")
        @PutMapping("/orderData")
        public boolean updateOrderData(@RequestBody OrderData orderData){
        return rderDataService.updateOrderData(orderData);
        }

        @ApiOperation(value = "查询data信息表分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "orderData", value = "查询条件")
        })
        @GetMapping("/orderData/page")
        public IPage<OrderData> pageOrderData(Page<OrderData> page,OrderData orderData){
        return rderDataService.pageOrderData(page, orderData);
        }

        @ApiOperation(value = "id查询data信息表")
        @GetMapping("/orderData/{id}")
        public OrderData getOrderDataById(@PathVariable String id){
        return rderDataService.getOrderDataById(id);
        }

        }
