package com.ry.manage.direct.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.GdsRuleService;
import comm.repository.entity.GdsRule;
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
@RequestMapping("/gwk/gds-rule")
public class GdsRuleController {

    private final GdsRuleService dsRuleService;

    public GdsRuleController(GdsRuleService dsRuleService){this.dsRuleService = dsRuleService;}

    @ApiOperation(value = "新增gds规则")
    @PostMapping("/gdsRule")
    public boolean saveGdsRule(@RequestBody GdsRule gdsRule){
    return dsRuleService.saveGdsRule(gdsRule);
    }

    @ApiOperation(value = "删除gds规则")
    @DeleteMapping("/gdsRule/{id}")
    public boolean removeGdsRule(@PathVariable("id") String id){
    return dsRuleService.removeGdsRule(id);
    }

    @ApiOperation(value = "批量删除gds规则")
    @DeleteMapping("/gdsRules")
    public boolean removeGdsRuleByIds(@RequestBody List <String> ids){
        return dsRuleService.removeGdsRuleByIds(ids);
        }


        @ApiOperation(value = "更新gds规则")
        @PutMapping("/gdsRule")
        public boolean updateGdsRule(@RequestBody GdsRule gdsRule){
        return dsRuleService.updateGdsRule(gdsRule);
        }

        @ApiOperation(value = "查询gds规则分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "gdsRule", value = "查询条件")
        })
        @GetMapping("/gdsRule/page")
        public IPage<GdsRule> pageGdsRule(Page<GdsRule> page,GdsRule gdsRule){
        return dsRuleService.pageGdsRule(page, gdsRule);
        }

        @ApiOperation(value = "id查询gds规则")
        @GetMapping("/gdsRule/{id}")
        public GdsRule getGdsRuleById(@PathVariable String id){
        return dsRuleService.getGdsRuleById(id);
        }

        }
