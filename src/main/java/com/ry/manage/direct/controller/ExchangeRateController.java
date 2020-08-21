package com.ry.manage.direct.controller;

import com.ry.manage.direct.entity.ExchangeRate;
import com.ry.manage.direct.service.ExchangeRateService;
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
@RequestMapping("/gwk/exchange-rate")
public class ExchangeRateController {

    private final ExchangeRateService xchangeRateService;

    public ExchangeRateController (ExchangeRateService xchangeRateService){this.xchangeRateService = xchangeRateService;}

    @ApiOperation(value = "新增实时外汇数据")
    @PostMapping("/exchangeRate")
    public boolean saveExchangeRate(@RequestBody ExchangeRate exchangeRate){
    return xchangeRateService.saveExchangeRate(exchangeRate);
    }

    @ApiOperation(value = "删除实时外汇数据")
    @DeleteMapping("/exchangeRate/{id}")
    public boolean removeExchangeRate(@PathVariable("id") String id){
    return xchangeRateService.removeExchangeRate(id);
    }

    @ApiOperation(value = "批量删除实时外汇数据")
    @DeleteMapping("/exchangeRates")
    public boolean removeExchangeRateByIds(@RequestBody List <String> ids){
        return xchangeRateService.removeExchangeRateByIds(ids);
        }


        @ApiOperation(value = "更新实时外汇数据")
        @PutMapping("/exchangeRate")
        public boolean updateExchangeRate(@RequestBody ExchangeRate exchangeRate){
        return xchangeRateService.updateExchangeRate(exchangeRate);
        }

        @ApiOperation(value = "查询实时外汇数据分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "exchangeRate", value = "查询条件")
        })
        @GetMapping("/exchangeRate/page")
        public IPage<ExchangeRate> pageExchangeRate(Page<ExchangeRate> page,ExchangeRate exchangeRate){
        return xchangeRateService.pageExchangeRate(page, exchangeRate);
        }

        @ApiOperation(value = "id查询实时外汇数据")
        @GetMapping("/exchangeRate/{id}")
        public ExchangeRate getExchangeRateById(@PathVariable String id){
        return xchangeRateService.getExchangeRateById(id);
        }

        }
