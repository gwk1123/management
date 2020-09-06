package com.ry.manage.direct.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.common.CommonResult;
import com.ry.manage.direct.service.OtaRuleService;
import comm.repository.entity.OtaRule;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
@RequestMapping("/direct")
public class OtaRuleController {

    private final OtaRuleService taRuleService;

    public OtaRuleController(OtaRuleService taRuleService) {
        this.taRuleService = taRuleService;
    }

    @ApiOperation(value = "新增GDS规则信息(ml_gds_rule) ")
    @PostMapping("/ota_rule")
    public CommonResult saveOtaRule(@RequestBody OtaRule otaRule) {
        return CommonResult.success(taRuleService.saveOtaRule(otaRule));
    }

    @ApiOperation(value = "删除GDS规则信息(ml_gds_rule) ")
    @DeleteMapping("/ota_rule/{id}")
    public CommonResult removeOtaRule(@PathVariable("id") String id) {
        return CommonResult.success(taRuleService.removeOtaRule(id));
    }

    @ApiOperation(value = "批量删除GDS规则信息(ml_gds_rule) ")
    @DeleteMapping("/ota_rules")
    public CommonResult removeOtaRuleByIds(@RequestBody List<String> ids) {
        return CommonResult.success(taRuleService.removeOtaRuleByIds(ids));
    }


    @ApiOperation(value = "更新GDS规则信息(ml_gds_rule) ")
    @PutMapping("/ota_rule")
    public CommonResult updateOtaRule(@RequestBody OtaRule otaRule) {
        return CommonResult.success(taRuleService.updateOtaRule(otaRule));
    }

    @ApiOperation(value = "查询GDS规则信息(ml_gds_rule) 分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页参数"),
            @ApiImplicitParam(name = "otaRule", value = "查询条件")
    })
    @GetMapping("/ota_rule/page")
    public CommonResult pageOtaRule(Page<OtaRule> page, OtaRule otaRule) {
        return CommonResult.success(taRuleService.pageOtaRule(page, otaRule));
    }

    @ApiOperation(value = "id查询GDS规则信息(ml_gds_rule) ")
    @GetMapping("/ota_rule/{id}")
    public CommonResult getOtaRuleById(@PathVariable String id) {
        return CommonResult.success(taRuleService.getOtaRuleById(id));
    }

}
