package com.ry.manage.direct.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.common.CommonResult;
import com.ry.manage.direct.service.PolicyInfoService;
import com.sibecommon.repository.entity.PolicyInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gwk
 * @since 2020-08-22
 */
@Api(tags = {"细节政策表"})
@RestController
@RequestMapping("/direct")
public class PolicyInfoController {

    private final PolicyInfoService olicyInfoService;

    public PolicyInfoController(PolicyInfoService olicyInfoService){this.olicyInfoService = olicyInfoService;}

    @ApiOperation(value = "新增")
    @PostMapping("/policy_info")
    public CommonResult savePolicyInfo(@RequestBody PolicyInfo policyInfo){
    return CommonResult.success(olicyInfoService.savePolicyInfo(policyInfo));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/policy_info/{id}")
    public CommonResult removePolicyInfo(@PathVariable("id") String id){
    return CommonResult.success(olicyInfoService.removePolicyInfo(id));
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/policy_infos")
    public CommonResult removePolicyInfoByIds(@RequestBody List <String> ids){
        return CommonResult.success(olicyInfoService.removePolicyInfoByIds(ids));
        }


        @ApiOperation(value = "更新")
        @PutMapping("/policy_info")
        public CommonResult updatePolicyInfo(@RequestBody PolicyInfo policyInfo){
        return CommonResult.success(olicyInfoService.updatePolicyInfo(policyInfo));
        }

        @ApiOperation(value = "查询分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "policyInfo", value = "查询条件")
        })
        @GetMapping("/policy_info/page")
        public CommonResult pagePolicyInfo(Page<PolicyInfo> page,PolicyInfo policyInfo){
        return CommonResult.success(olicyInfoService.pagePolicyInfo(page, policyInfo));
        }

        @ApiOperation(value = "id查询")
        @GetMapping("/policy_info/{id}")
        public CommonResult getPolicyInfoById(@PathVariable String id){
        return CommonResult.success(olicyInfoService.getPolicyInfoById(id));
        }

    @ApiOperation(value = "根据id更改GDS状态")
    @PutMapping("/policy_info/changeStatus")
    public CommonResult changePolicyInfoStatus(@RequestBody PolicyInfo policyInfo) {
        return CommonResult.success(olicyInfoService.changePolicyInfoStatus(policyInfo));
    }


        }
