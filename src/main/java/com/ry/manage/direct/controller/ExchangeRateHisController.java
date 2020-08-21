package com.ry.manage.direct.controller;

import com.ry.manage.direct.entity.ExchangeRateHis;
import com.ry.manage.direct.service.ExchangeRateHisService;
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
 * 实时外汇数据 前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"实时外汇数据"})
@RestController
@RequestMapping("/gwk/exchange-rate-his")
public class ExchangeRateHisController {

    private final ExchangeRateHisService xchangeRateHisService;

    public ExchangeRateHisController (ExchangeRateHisService xchangeRateHisService){this.xchangeRateHisService = xchangeRateHisService;}

    @ApiOperation(value = "新增实时外汇数据")
    @PostMapping("/exchangeRateHis")
    public boolean saveExchangeRateHis(@RequestBody ExchangeRateHis exchangeRateHis){
    return xchangeRateHisService.saveExchangeRateHis(exchangeRateHis);
    }

    @ApiOperation(value = "删除实时外汇数据")
    @DeleteMapping("/exchangeRateHis/{id}")
    public boolean removeExchangeRateHis(@PathVariable("id") String id){
    return xchangeRateHisService.removeExchangeRateHis(id);
    }

    @ApiOperation(value = "批量删除实时外汇数据")
    @DeleteMapping("/exchangeRateHiss")
    public boolean removeExchangeRateHisByIds(@RequestBody List <String> ids){
        return xchangeRateHisService.removeExchangeRateHisByIds(ids);
        }


        @ApiOperation(value = "更新实时外汇数据")
        @PutMapping("/exchangeRateHis")
        public boolean updateExchangeRateHis(@RequestBody ExchangeRateHis exchangeRateHis){
        return xchangeRateHisService.updateExchangeRateHis(exchangeRateHis);
        }

        @ApiOperation(value = "查询实时外汇数据分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "exchangeRateHis", value = "查询条件")
        })
        @GetMapping("/exchangeRateHis/page")
        public IPage<ExchangeRateHis> pageExchangeRateHis(Page<ExchangeRateHis> page,ExchangeRateHis exchangeRateHis){
        return xchangeRateHisService.pageExchangeRateHis(page, exchangeRateHis);
        }

        @ApiOperation(value = "id查询实时外汇数据")
        @GetMapping("/exchangeRateHis/{id}")
        public ExchangeRateHis getExchangeRateHisById(@PathVariable String id){
        return xchangeRateHisService.getExchangeRateHisById(id);
        }

        }
