package com.ry.manage.direct.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.ResignConfigService;
import com.sibecommon.repository.entity.ResignConfig;
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
 * @since 2020-11-17
 */
@Api(tags = {""})
@RestController
@RequestMapping("/direct")
public class ResignConfigController {

    private final ResignConfigService esignConfigService;

    public ResignConfigController(ResignConfigService esignConfigService){this.esignConfigService = esignConfigService;}

    @ApiOperation(value = "新增")
    @PostMapping("/resign_config")
    public boolean saveResignConfig(@RequestBody ResignConfig resignConfig){
    return esignConfigService.saveResignConfig(resignConfig);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/resign_config/{id}")
    public boolean removeResignConfig(@PathVariable("id") String id){
    return esignConfigService.removeResignConfig(id);
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/resign_configs")
    public boolean removeResignConfigByIds(@RequestBody List <String> ids){
        return esignConfigService.removeResignConfigByIds(ids);
        }


        @ApiOperation(value = "更新")
        @PutMapping("/resign_config")
        public boolean updateResignConfig(@RequestBody ResignConfig resignConfig){
        return esignConfigService.updateResignConfig(resignConfig);
        }

        @ApiOperation(value = "查询分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "resignConfig", value = "查询条件")
        })
        @GetMapping("/resign_config/page")
        public IPage<ResignConfig> pageResignConfig(Page<ResignConfig> page,ResignConfig resignConfig){
        return esignConfigService.pageResignConfig(page, resignConfig);
        }

        @ApiOperation(value = "id查询")
        @GetMapping("/resign_config/{id}")
        public ResignConfig getResignConfigById(@PathVariable String id){
        return esignConfigService.getResignConfigById(id);
        }

        }
