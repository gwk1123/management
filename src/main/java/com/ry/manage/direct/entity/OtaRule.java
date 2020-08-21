package com.ry.manage.direct.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * GDS规则信息(ml_gds_rule) 
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OtaRule对象", description="GDS规则信息(ml_gds_rule) ")
public class OtaRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "OTA平台编码")
    private String otaCode;

    @ApiModelProperty(value = "OTA站点编码")
    private String otaSiteCode;

    @ApiModelProperty(value = "GDS规则类型")
    private String ruleType;

    @ApiModelProperty(value = "出发地，多个逗号隔开 如CN,HK")
    private String origin;

    @ApiModelProperty(value = "目的地，多个逗号隔开 如CN,HK")
    private String destination;

    @ApiModelProperty(value = "双向标识1-单项，2-双向")
    private Integer bothWaysFlag;

    @ApiModelProperty(value = "开始旅行日期 如2020-01-12>2020-09-30,2020-01-12>2020-09-30")
    private String travelPeriodFrom;

    @ApiModelProperty(value = "结束旅行日期如2020-01-12>2020-09-30,2020-01-12>2020-09-30")
    private String travelPeriodTo;

    @ApiModelProperty(value = "自定义内容一")
    private String parameter1;

    @ApiModelProperty(value = "自定义内容二")
    private String parameter2;

    @ApiModelProperty(value = "自定义内容三")
    private String parameter3;

    @ApiModelProperty(value = "自定义内容四")
    private String parameter4;

    @ApiModelProperty(value = "自定义内容五")
    private String parameter5;

    @ApiModelProperty(value = "自定义内容六")
    private String parameter6;

    @ApiModelProperty(value = "自定义内容七")
    private String parameter7;

    @ApiModelProperty(value = "自定义内容八")
    private String parameter8;

    @ApiModelProperty(value = "版本控制")
    private Integer version;

    @ApiModelProperty(value = "备注说明")
    private String remark;

    @ApiModelProperty(value = "优先排序序号")
    private Integer sortSeq;

    @ApiModelProperty(value = "0-有效(正常),1-暂停(挂起) 99-无效(删除)")
    private Boolean status;

    @ApiModelProperty(value = "生效日期(开始)")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "失效日期(截止)")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "最后修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人ID")
    private Integer createUserId;

    @ApiModelProperty(value = "创建人名称")
    private String createUserName;

    @ApiModelProperty(value = "修改人ID")
    private Integer updateUserId;

    @ApiModelProperty(value = "修改人名称")
    private String updateUserName;


}
