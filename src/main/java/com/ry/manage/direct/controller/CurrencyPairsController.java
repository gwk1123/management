package com.ry.manage.direct.controller;

import com.ry.manage.direct.service.CurrencyPairsService;
import comm.repository.entity.CurrencyPairs;
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
@RequestMapping("/gwk/currency-pairs")
public class CurrencyPairsController {

    private final CurrencyPairsService urrencyPairsService;

    public CurrencyPairsController (CurrencyPairsService urrencyPairsService){this.urrencyPairsService = urrencyPairsService;}

    @ApiOperation(value = "新增实时外汇数据")
    @PostMapping("/currencyPairs")
    public boolean saveCurrencyPairs(@RequestBody CurrencyPairs currencyPairs){
    return urrencyPairsService.saveCurrencyPairs(currencyPairs);
    }

    @ApiOperation(value = "删除实时外汇数据")
    @DeleteMapping("/currencyPairs/{id}")
    public boolean removeCurrencyPairs(@PathVariable("id") String id){
    return urrencyPairsService.removeCurrencyPairs(id);
    }

    @ApiOperation(value = "批量删除实时外汇数据")
    @DeleteMapping("/currencyPairss")
    public boolean removeCurrencyPairsByIds(@RequestBody List <String> ids){
        return urrencyPairsService.removeCurrencyPairsByIds(ids);
        }


        @ApiOperation(value = "更新实时外汇数据")
        @PutMapping("/currencyPairs")
        public boolean updateCurrencyPairs(@RequestBody CurrencyPairs currencyPairs){
        return urrencyPairsService.updateCurrencyPairs(currencyPairs);
        }

        @ApiOperation(value = "查询实时外汇数据分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "currencyPairs", value = "查询条件")
        })
        @GetMapping("/currencyPairs/page")
        public IPage<CurrencyPairs> pageCurrencyPairs(Page<CurrencyPairs> page,CurrencyPairs currencyPairs){
        return urrencyPairsService.pageCurrencyPairs(page, currencyPairs);
        }

        @ApiOperation(value = "id查询实时外汇数据")
        @GetMapping("/currencyPairs/{id}")
        public CurrencyPairs getCurrencyPairsById(@PathVariable String id){
        return urrencyPairsService.getCurrencyPairsById(id);
        }

        }
