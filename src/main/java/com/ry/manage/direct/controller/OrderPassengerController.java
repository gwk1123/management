package com.ry.manage.direct.controller;

import com.ry.manage.direct.service.OrderPassengerService;
import comm.repository.entity.OrderPassenger;
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
 * 乘客信息表 前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"乘客信息表"})
@RestController
@RequestMapping("/gwk/order-passenger")
public class OrderPassengerController {

    private final OrderPassengerService rderPassengerService;

    public OrderPassengerController (OrderPassengerService rderPassengerService){this.rderPassengerService = rderPassengerService;}

    @ApiOperation(value = "新增乘客信息表")
    @PostMapping("/orderPassenger")
    public boolean saveOrderPassenger(@RequestBody OrderPassenger orderPassenger){
    return rderPassengerService.saveOrderPassenger(orderPassenger);
    }

    @ApiOperation(value = "删除乘客信息表")
    @DeleteMapping("/orderPassenger/{id}")
    public boolean removeOrderPassenger(@PathVariable("id") String id){
    return rderPassengerService.removeOrderPassenger(id);
    }

    @ApiOperation(value = "批量删除乘客信息表")
    @DeleteMapping("/orderPassengers")
    public boolean removeOrderPassengerByIds(@RequestBody List <String> ids){
        return rderPassengerService.removeOrderPassengerByIds(ids);
        }


        @ApiOperation(value = "更新乘客信息表")
        @PutMapping("/orderPassenger")
        public boolean updateOrderPassenger(@RequestBody OrderPassenger orderPassenger){
        return rderPassengerService.updateOrderPassenger(orderPassenger);
        }

        @ApiOperation(value = "查询乘客信息表分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "orderPassenger", value = "查询条件")
        })
        @GetMapping("/orderPassenger/page")
        public IPage<OrderPassenger> pageOrderPassenger(Page<OrderPassenger> page,OrderPassenger orderPassenger){
        return rderPassengerService.pageOrderPassenger(page, orderPassenger);
        }

        @ApiOperation(value = "id查询乘客信息表")
        @GetMapping("/orderPassenger/{id}")
        public OrderPassenger getOrderPassengerById(@PathVariable String id){
        return rderPassengerService.getOrderPassengerById(id);
        }

        }
