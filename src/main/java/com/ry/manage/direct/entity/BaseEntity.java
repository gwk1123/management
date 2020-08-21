package com.ry.manage.direct.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author gwk
 * @version 1.0.0
 */
@Data
public class BaseEntity {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id",type = IdType.AUTO)
    private String id;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createUserName;

    @ApiModelProperty(value = "创建人ID")
    @TableField(fill = FieldFill.INSERT)
    private String createUserId;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;
    /**
     * 更新人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUserName;

    @ApiModelProperty(value = "修改人ID")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateUserId;

    /**
     * 状态
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "状态：0-正常,1-暂停（挂起）,2-删除")
    private String status;
}
