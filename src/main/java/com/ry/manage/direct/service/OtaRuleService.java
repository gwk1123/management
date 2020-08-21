package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.entity.OtaRule;

import java.util.List;

/**
 * <p>
 * GDS规则信息(ml_gds_rule)  服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface OtaRuleService extends IService<OtaRule> {

    /**
     * 查询GDS规则信息(ml_gds_rule) 分页数据
     *
     * @param page      分页参数
     * @param otaRule 查询条件
     * @return IPage<OtaRule>
     */
     IPage<OtaRule> pageOtaRule(Page<OtaRule> page,OtaRule otaRule);

    /**
     * 新增GDS规则信息(ml_gds_rule) 
     *
     * @param otaRule GDS规则信息(ml_gds_rule) 
     * @return boolean
     */
    boolean saveOtaRule(OtaRule otaRule);

    /**
     * 删除GDS规则信息(ml_gds_rule) 
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeOtaRule(String id);

    /**
     * 批量删除GDS规则信息(ml_gds_rule) 
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeOtaRuleByIds(List<String> ids);

    /**
     * 修改GDS规则信息(ml_gds_rule) 
     *
     * @param otaRule GDS规则信息(ml_gds_rule) 
     * @return boolean
     */
    boolean updateOtaRule(OtaRule otaRule);

    /**
     * id查询数据
     *
     * @param id id
     * @return OtaRule
     */
    OtaRule getOtaRuleById(String id);
}
