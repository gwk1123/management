package com.ry.manage.direct.controller;

import com.ry.manage.common.CommonResult;
import com.ry.manage.direct.service.GdsPccService;
import com.sibecommon.repository.entity.GdsPcc;
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
 * GDS PCC配置 前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"GDSPCC配置"})
@RestController
@RequestMapping("/direct")
public class GdsPccController {

    private final GdsPccService dsPccService;

    public GdsPccController (GdsPccService dsPccService){this.dsPccService = dsPccService;}

    @ApiOperation(value = "新增GDS PCC配置")
    @PostMapping("/gds_pcc")
    public CommonResult saveGdsPcc(@RequestBody GdsPcc gdsPcc){
    return CommonResult.success(dsPccService.saveGdsPcc(gdsPcc));
    }

    @ApiOperation(value = "删除GDS PCC配置")
    @DeleteMapping("/gds_pcc/{id}")
    public CommonResult removeGdsPcc(@PathVariable("id") String id){
    return CommonResult.success(dsPccService.removeGdsPcc(id));
    }

    @ApiOperation(value = "批量删除GDS PCC配置")
    @DeleteMapping("/gds_pcs")
    public CommonResult removeGdsPccByIds(@RequestBody List <String> ids){
        return CommonResult.success(dsPccService.removeGdsPccByIds(ids));
        }


        @ApiOperation(value = "更新GDS PCC配置")
        @PutMapping("/gds_pcc")
        public CommonResult updateGdsPcc(@RequestBody GdsPcc gdsPcc){
        return CommonResult.success(dsPccService.updateGdsPcc(gdsPcc));
        }

        @ApiOperation(value = "查询GDS PCC配置分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "gdsPcc", value = "查询条件")
        })
        @GetMapping("/gds_pcc/page")
        public CommonResult pageGdsPcc(Page<GdsPcc> page, GdsPcc gdsPcc){
            IPage<GdsPcc> iPage = dsPccService.pageGdsPcc(page, gdsPcc);
            return CommonResult.success(iPage);
        }

        @ApiOperation(value = "id查询GDS PCC配置")
        @GetMapping("/gds_pcc/{id}")
        public CommonResult getGdsPccById(@PathVariable String id){
        return CommonResult.success(dsPccService.getGdsPccById(id));
        }

    @ApiOperation(value = "根据id更改GDS状态")
    @PutMapping("/gds_pcc/changeStatus")
    public CommonResult changeGdsStatus(@RequestBody GdsPcc gdsPcc) {
        return CommonResult.success(dsPccService.changeGdsStatus(gdsPcc));
    }

        }
