package com.ry.manage.direct.controller;

import com.ry.manage.direct.service.QueueLetterBoxService;
import com.sibecommon.repository.entity.QueueLetterBox;
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
 * 信箱配置 前端控制器
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Api(tags = {"信箱配置"})
@RestController
@RequestMapping("/gwk/queue-letter-box")
public class QueueLetterBoxController {

    private final QueueLetterBoxService ueueLetterBoxService;

    public QueueLetterBoxController (QueueLetterBoxService ueueLetterBoxService){this.ueueLetterBoxService = ueueLetterBoxService;}

    @ApiOperation(value = "新增信箱配置")
    @PostMapping("/queueLetterBox")
    public boolean saveQueueLetterBox(@RequestBody QueueLetterBox queueLetterBox){
    return ueueLetterBoxService.saveQueueLetterBox(queueLetterBox);
    }

    @ApiOperation(value = "删除信箱配置")
    @DeleteMapping("/queueLetterBox/{id}")
    public boolean removeQueueLetterBox(@PathVariable("id") String id){
    return ueueLetterBoxService.removeQueueLetterBox(id);
    }

    @ApiOperation(value = "批量删除信箱配置")
    @DeleteMapping("/queueLetterBoxs")
    public boolean removeQueueLetterBoxByIds(@RequestBody List <String> ids){
        return ueueLetterBoxService.removeQueueLetterBoxByIds(ids);
        }


        @ApiOperation(value = "更新信箱配置")
        @PutMapping("/queueLetterBox")
        public boolean updateQueueLetterBox(@RequestBody QueueLetterBox queueLetterBox){
        return ueueLetterBoxService.updateQueueLetterBox(queueLetterBox);
        }

        @ApiOperation(value = "查询信箱配置分页数据")
        @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页参数"),
        @ApiImplicitParam(name = "queueLetterBox", value = "查询条件")
        })
        @GetMapping("/queueLetterBox/page")
        public IPage<QueueLetterBox> pageQueueLetterBox(Page<QueueLetterBox> page,QueueLetterBox queueLetterBox){
        return ueueLetterBoxService.pageQueueLetterBox(page, queueLetterBox);
        }

        @ApiOperation(value = "id查询信箱配置")
        @GetMapping("/queueLetterBox/{id}")
        public QueueLetterBox getQueueLetterBoxById(@PathVariable String id){
        return ueueLetterBoxService.getQueueLetterBoxById(id);
        }

        }
