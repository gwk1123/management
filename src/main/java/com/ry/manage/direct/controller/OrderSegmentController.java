package com.ry.manage.direct.controller;

import com.ry.manage.direct.entity.OrderSegment;
import com.ry.manage.direct.service.OrderSegmentService;
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
 * 航段信息表 前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"航段信息表"})
@RestController
@RequestMapping("/gwk/order-segment")
public class OrderSegmentController {

    private final OrderSegmentService rderSegmentService;

    public OrderSegmentController (OrderSegmentService rderSegmentService){this.rderSegmentService = rderSegmentService;}

    @ApiOperation(value = "新增航段信息表")
    @PostMapping("/orderSegment")
    public boolean saveOrderSegment(@RequestBody OrderSegment orderSegment){
    return rderSegmentService.saveOrderSegment(orderSegment);
    }

    @ApiOperation(value = "删除航段信息表")
    @DeleteMapping("/orderSegment/{id}")
    public boolean removeOrderSegment(@PathVariable("id") String id){
    return rderSegmentService.removeOrderSegment(id);
    }

    @ApiOperation(value = "批量删除航段信息表")
    @DeleteMapping("/orderSegments")
    public boolean removeOrderSegmentByIds(@RequestBody List <String> ids){
        return rderSegmentService.removeOrderSegmentByIds(ids);
        }


        @ApiOperation(value = "更新航段信息表")
        @PutMapping("/orderSegment")
        public boolean updateOrderSegment(@RequestBody OrderSegment orderSegment){
        return rderSegmentService.updateOrderSegment(orderSegment);
        }

        @ApiOperation(value = "查询航段信息表分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "orderSegment", value = "查询条件")
        })
        @GetMapping("/orderSegment/page")
        public IPage<OrderSegment> pageOrderSegment(Page<OrderSegment> page,OrderSegment orderSegment){
        return rderSegmentService.pageOrderSegment(page, orderSegment);
        }

        @ApiOperation(value = "id查询航段信息表")
        @GetMapping("/orderSegment/{id}")
        public OrderSegment getOrderSegmentById(@PathVariable String id){
        return rderSegmentService.getOrderSegmentById(id);
        }

        }
