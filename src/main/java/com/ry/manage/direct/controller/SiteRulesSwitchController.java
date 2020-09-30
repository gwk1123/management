package com.ry.manage.direct.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.SiteRulesSwitchService;
import comm.repository.entity.SiteRulesSwitch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 站点规则开关 前端控制器
 * </p>
 *
 * @author gwk
 * @since 2020-09-05
 */
@Api(tags = {"站点规则开关"})
@RestController
@RequestMapping("/model/site_rules_switch")
public class SiteRulesSwitchController {

    private final SiteRulesSwitchService iteRulesSwitchService;

    public SiteRulesSwitchController(SiteRulesSwitchService iteRulesSwitchService){this.iteRulesSwitchService = iteRulesSwitchService;}

    @ApiOperation(value = "新增站点规则开关")
    @PostMapping("/siteRulesSwitch")
    public boolean saveSiteRulesSwitch(@RequestBody SiteRulesSwitch siteRulesSwitch){
    return iteRulesSwitchService.saveSiteRulesSwitch(siteRulesSwitch);
    }

    @ApiOperation(value = "删除站点规则开关")
    @DeleteMapping("/siteRulesSwitch/{id}")
    public boolean removeSiteRulesSwitch(@PathVariable("id") String id){
    return iteRulesSwitchService.removeSiteRulesSwitch(id);
    }

    @ApiOperation(value = "批量删除站点规则开关")
    @DeleteMapping("/siteRulesSwitchs")
    public boolean removeSiteRulesSwitchByIds(@RequestBody List <String> ids){
        return iteRulesSwitchService.removeSiteRulesSwitchByIds(ids);
        }


        @ApiOperation(value = "更新站点规则开关")
        @PutMapping("/siteRulesSwitch")
        public boolean updateSiteRulesSwitch(@RequestBody SiteRulesSwitch siteRulesSwitch){
        return iteRulesSwitchService.updateSiteRulesSwitch(siteRulesSwitch);
        }

        @ApiOperation(value = "查询站点规则开关分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "siteRulesSwitch", value = "查询条件")
        })
        @GetMapping("/siteRulesSwitch/page")
        public IPage<SiteRulesSwitch> pageSiteRulesSwitch(Page<SiteRulesSwitch> page,SiteRulesSwitch siteRulesSwitch){
        return iteRulesSwitchService.pageSiteRulesSwitch(page, siteRulesSwitch);
        }

        @ApiOperation(value = "id查询站点规则开关")
        @GetMapping("/siteRulesSwitch/{id}")
        public SiteRulesSwitch getSiteRulesSwitchById(@PathVariable String id){
        return iteRulesSwitchService.getSiteRulesSwitchById(id);
        }

        }
