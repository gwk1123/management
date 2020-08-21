package com.ry.manage.direct.controller;

import com.ry.manage.direct.entity.OtaRule;
import com.ry.manage.direct.service.OtaRuleService;
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
 * GDS规则信息(ml_gds_rule)  前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"GDS规则信息(ml_gds_rule) "})
@RestController
@RequestMapping("/gwk/ota-rule")
public class OtaRuleController {

    private final OtaRuleService taRuleService;

    public OtaRuleController (OtaRuleService taRuleService){this.taRuleService = taRuleService;}

    @ApiOperation(value = "新增GDS规则信息(ml_gds_rule) ")
    @PostMapping("/otaRule")
    public boolean saveOtaRule(@RequestBody OtaRule otaRule){
    return taRuleService.saveOtaRule(otaRule);
    }

    @ApiOperation(value = "删除GDS规则信息(ml_gds_rule) ")
    @DeleteMapping("/otaRule/{id}")
    public boolean removeOtaRule(@PathVariable("id") String id){
    return taRuleService.removeOtaRule(id);
    }

    @ApiOperation(value = "批量删除GDS规则信息(ml_gds_rule) ")
    @DeleteMapping("/otaRules")
    public boolean removeOtaRuleByIds(@RequestBody List <String> ids){
        return taRuleService.removeOtaRuleByIds(ids);
        }


        @ApiOperation(value = "更新GDS规则信息(ml_gds_rule) ")
        @PutMapping("/otaRule")
        public boolean updateOtaRule(@RequestBody OtaRule otaRule){
        return taRuleService.updateOtaRule(otaRule);
        }

        @ApiOperation(value = "查询GDS规则信息(ml_gds_rule) 分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "otaRule", value = "查询条件")
        })
        @GetMapping("/otaRule/page")
        public IPage<OtaRule> pageOtaRule(Page<OtaRule> page,OtaRule otaRule){
        return taRuleService.pageOtaRule(page, otaRule);
        }

        @ApiOperation(value = "id查询GDS规则信息(ml_gds_rule) ")
        @GetMapping("/otaRule/{id}")
        public OtaRule getOtaRuleById(@PathVariable String id){
        return taRuleService.getOtaRuleById(id);
        }

        }
