package com.ry.manage.direct.controller;

import com.ry.manage.direct.entity.Supplier;
import com.ry.manage.direct.service.SupplierService;
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
 * 供应商信息(ml_supplier)  前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"供应商信息(ml_supplier) "})
@RestController
@RequestMapping("/gwk/supplier")
public class SupplierController {

    private final SupplierService upplierService;

    public SupplierController (SupplierService upplierService){this.upplierService = upplierService;}

    @ApiOperation(value = "新增供应商信息(ml_supplier) ")
    @PostMapping("/supplier")
    public boolean saveSupplier(@RequestBody Supplier supplier){
    return upplierService.saveSupplier(supplier);
    }

    @ApiOperation(value = "删除供应商信息(ml_supplier) ")
    @DeleteMapping("/supplier/{id}")
    public boolean removeSupplier(@PathVariable("id") String id){
    return upplierService.removeSupplier(id);
    }

    @ApiOperation(value = "批量删除供应商信息(ml_supplier) ")
    @DeleteMapping("/suppliers")
    public boolean removeSupplierByIds(@RequestBody List <String> ids){
        return upplierService.removeSupplierByIds(ids);
        }


        @ApiOperation(value = "更新供应商信息(ml_supplier) ")
        @PutMapping("/supplier")
        public boolean updateSupplier(@RequestBody Supplier supplier){
        return upplierService.updateSupplier(supplier);
        }

        @ApiOperation(value = "查询供应商信息(ml_supplier) 分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "supplier", value = "查询条件")
        })
        @GetMapping("/supplier/page")
        public IPage<Supplier> pageSupplier(Page<Supplier> page,Supplier supplier){
        return upplierService.pageSupplier(page, supplier);
        }

        @ApiOperation(value = "id查询供应商信息(ml_supplier) ")
        @GetMapping("/supplier/{id}")
        public Supplier getSupplierById(@PathVariable String id){
        return upplierService.getSupplierById(id);
        }

        }
