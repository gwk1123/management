package com.ry.manage.direct.controller;

import com.ry.manage.direct.service.SegmentRewardConfigService;
import com.sibecommon.repository.entity.SegmentRewardConfig;
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
 * 航段奖励(ml_segment_reward_config)  前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"航段奖励(ml_segment_reward_config) "})
@RestController
@RequestMapping("/gwk/segment-reward-config")
public class SegmentRewardConfigController {

    private final SegmentRewardConfigService egmentRewardConfigService;

    public SegmentRewardConfigController (SegmentRewardConfigService egmentRewardConfigService){this.egmentRewardConfigService = egmentRewardConfigService;}

    @ApiOperation(value = "新增航段奖励(ml_segment_reward_config) ")
    @PostMapping("/segmentRewardConfig")
    public boolean saveSegmentRewardConfig(@RequestBody SegmentRewardConfig segmentRewardConfig){
    return egmentRewardConfigService.saveSegmentRewardConfig(segmentRewardConfig);
    }

    @ApiOperation(value = "删除航段奖励(ml_segment_reward_config) ")
    @DeleteMapping("/segmentRewardConfig/{id}")
    public boolean removeSegmentRewardConfig(@PathVariable("id") String id){
    return egmentRewardConfigService.removeSegmentRewardConfig(id);
    }

    @ApiOperation(value = "批量删除航段奖励(ml_segment_reward_config) ")
    @DeleteMapping("/segmentRewardConfigs")
    public boolean removeSegmentRewardConfigByIds(@RequestBody List <String> ids){
        return egmentRewardConfigService.removeSegmentRewardConfigByIds(ids);
        }


        @ApiOperation(value = "更新航段奖励(ml_segment_reward_config) ")
        @PutMapping("/segmentRewardConfig")
        public boolean updateSegmentRewardConfig(@RequestBody SegmentRewardConfig segmentRewardConfig){
        return egmentRewardConfigService.updateSegmentRewardConfig(segmentRewardConfig);
        }

        @ApiOperation(value = "查询航段奖励(ml_segment_reward_config) 分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "segmentRewardConfig", value = "查询条件")
        })
        @GetMapping("/segmentRewardConfig/page")
        public IPage<SegmentRewardConfig> pageSegmentRewardConfig(Page<SegmentRewardConfig> page,SegmentRewardConfig segmentRewardConfig){
        return egmentRewardConfigService.pageSegmentRewardConfig(page, segmentRewardConfig);
        }

        @ApiOperation(value = "id查询航段奖励(ml_segment_reward_config) ")
        @GetMapping("/segmentRewardConfig/{id}")
        public SegmentRewardConfig getSegmentRewardConfigById(@PathVariable String id){
        return egmentRewardConfigService.getSegmentRewardConfigById(id);
        }

        }
