package com.ry.manage.direct.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.common.CommonResult;
import com.ry.manage.direct.service.PolicyGlobalService;
import comm.repository.entity.PolicyGlobal;
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
 * @since 2020-08-23
 */
@Api(tags = {"全局政策表"})
@RestController
@RequestMapping("/direct")
public class PolicyGlobalController {

    private final PolicyGlobalService olicyGlobalService;

    public PolicyGlobalController(PolicyGlobalService olicyGlobalService){this.olicyGlobalService = olicyGlobalService;}

    @ApiOperation(value = "新增")
    @PostMapping("/policy_global")
    public CommonResult savePolicyGlobal(@RequestBody PolicyGlobal policyGlobal){
    return CommonResult.success(olicyGlobalService.savePolicyGlobal(policyGlobal));
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/policy_global/{id}")
    public CommonResult removePolicyGlobal(@PathVariable("id") String id){
    return CommonResult.success(olicyGlobalService.removePolicyGlobal(id));
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/policy_globals")
    public CommonResult removePolicyGlobalByIds(@RequestBody List <String> ids){
        return CommonResult.success(olicyGlobalService.removePolicyGlobalByIds(ids));
        }


        @ApiOperation(value = "更新")
        @PutMapping("/policy_global")
        public CommonResult updatePolicyGlobal(@RequestBody PolicyGlobal policyGlobal){
        return CommonResult.success(olicyGlobalService.updatePolicyGlobal(policyGlobal));
        }

        @ApiOperation(value = "查询分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "policyGlobal", value = "查询条件")
        })
        @GetMapping("/policy_global/page")
        public CommonResult pagePolicyGlobal(Page<PolicyGlobal> page,PolicyGlobal policyGlobal){
        return CommonResult.success(olicyGlobalService.pagePolicyGlobal(page, policyGlobal));
        }

        @ApiOperation(value = "id查询")
        @GetMapping("/policy_global/{id}")
        public CommonResult getPolicyGlobalById(@PathVariable String id){
        return CommonResult.success(olicyGlobalService.getPolicyGlobalById(id));
        }

    @ApiOperation(value = "根据id更改GDS状态")
    @PutMapping("/policy_global/changeStatus")
    public CommonResult changePolicyGlobalStatus(@RequestBody PolicyGlobal policyGlobal) {
        return CommonResult.success(olicyGlobalService.changePolicyGlobalStatus(policyGlobal));
    }

        }
