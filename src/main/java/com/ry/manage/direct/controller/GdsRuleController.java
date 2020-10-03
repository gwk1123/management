package com.ry.manage.direct.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.GdsRuleService;
import com.sibecommon.repository.entity.GdsRule;
import com.ry.manage.common.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * gds规则 前端控制器
 * </p>
 *
 * @author gwk
 * @since 2020-09-12
 */
@Api(tags = {"gds规则"})
@RestController
@RequestMapping("/direct")
public class GdsRuleController {

    private final GdsRuleService dsRuleService;

    public GdsRuleController(GdsRuleService dsRuleService) {
        this.dsRuleService = dsRuleService;
    }

    @ApiOperation(value = "新增gds规则")
    @PostMapping("/gds_rule")
    public CommonResult saveGdsRule(@RequestBody GdsRule gdsRule) {
        return CommonResult.success(dsRuleService.saveGdsRule(gdsRule));
    }

    @ApiOperation(value = "删除gds规则")
    @DeleteMapping("/gds_rule/{id}")
    public CommonResult removeGdsRule(@PathVariable("id") String id) {
        return CommonResult.success(dsRuleService.removeGdsRule(id));
    }

    @ApiOperation(value = "批量删除gds规则")
    @DeleteMapping("/gds_rule")
    public CommonResult removeGdsRuleByIds(@RequestBody List<String> ids) {
        return CommonResult.success(dsRuleService.removeGdsRuleByIds(ids));
    }


    @ApiOperation(value = "更新gds规则")
    @PutMapping("/gds_rule")
    public CommonResult updateGdsRule(@RequestBody GdsRule gdsRule) {
        return CommonResult.success(dsRuleService.updateGdsRule(gdsRule));
    }

    @ApiOperation(value = "查询gds规则分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页参数"),
            @ApiImplicitParam(name = "gdsRule", value = "查询条件")
    })
    @GetMapping("/gds_rule/page")
    public CommonResult pageGdsRule(Page<GdsRule> page, GdsRule gdsRule) {
        IPage<GdsRule> iPage = dsRuleService.pageGdsRule(page, gdsRule);
        return CommonResult.success(iPage);
    }

    @ApiOperation(value = "id查询gds规则")
    @GetMapping("/gds_rule/{id}")
    public CommonResult getGdsRuleById(@PathVariable String id) {
        return CommonResult.success(dsRuleService.getGdsRuleById(id));
    }

}
