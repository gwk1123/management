package com.ry.manage.direct.controller;

import com.ry.manage.direct.service.SegmentRewardRuleService;
import com.sibecommon.repository.entity.SegmentRewardRule;
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
 * 航段奖励规则配置 前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"航段奖励规则配置"})
@RestController
@RequestMapping("/gwk/segment-reward-rule")
public class SegmentRewardRuleController {

    private final SegmentRewardRuleService egmentRewardRuleService;

    public SegmentRewardRuleController (SegmentRewardRuleService egmentRewardRuleService){this.egmentRewardRuleService = egmentRewardRuleService;}

    @ApiOperation(value = "新增航段奖励规则配置")
    @PostMapping("/segmentRewardRule")
    public boolean saveSegmentRewardRule(@RequestBody SegmentRewardRule segmentRewardRule){
    return egmentRewardRuleService.saveSegmentRewardRule(segmentRewardRule);
    }

    @ApiOperation(value = "删除航段奖励规则配置")
    @DeleteMapping("/segmentRewardRule/{id}")
    public boolean removeSegmentRewardRule(@PathVariable("id") String id){
    return egmentRewardRuleService.removeSegmentRewardRule(id);
    }

    @ApiOperation(value = "批量删除航段奖励规则配置")
    @DeleteMapping("/segmentRewardRules")
    public boolean removeSegmentRewardRuleByIds(@RequestBody List <String> ids){
        return egmentRewardRuleService.removeSegmentRewardRuleByIds(ids);
        }


        @ApiOperation(value = "更新航段奖励规则配置")
        @PutMapping("/segmentRewardRule")
        public boolean updateSegmentRewardRule(@RequestBody SegmentRewardRule segmentRewardRule){
        return egmentRewardRuleService.updateSegmentRewardRule(segmentRewardRule);
        }

        @ApiOperation(value = "查询航段奖励规则配置分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "segmentRewardRule", value = "查询条件")
        })
        @GetMapping("/segmentRewardRule/page")
        public IPage<SegmentRewardRule> pageSegmentRewardRule(Page<SegmentRewardRule> page,SegmentRewardRule segmentRewardRule){
        return egmentRewardRuleService.pageSegmentRewardRule(page, segmentRewardRule);
        }

        @ApiOperation(value = "id查询航段奖励规则配置")
        @GetMapping("/segmentRewardRule/{id}")
        public SegmentRewardRule getSegmentRewardRuleById(@PathVariable String id){
        return egmentRewardRuleService.getSegmentRewardRuleById(id);
        }

        }
