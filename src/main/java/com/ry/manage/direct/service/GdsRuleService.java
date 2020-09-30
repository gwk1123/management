package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import comm.repository.entity.GdsRule;

import java.util.List;

/**
 * <p>
 * gds规则 服务类
 * </p>
 *
 * @author gwk
 * @since 2020-09-12
 */
public interface GdsRuleService extends IService<GdsRule> {

    /**
     * 查询gds规则分页数据
     *
     * @param page      分页参数
     * @param gdsRule 查询条件
     * @return IPage<GdsRule>
     */
     IPage<GdsRule> pageGdsRule(Page<GdsRule> page,GdsRule gdsRule);

    /**
     * 新增gds规则
     *
     * @param gdsRule gds规则
     * @return boolean
     */
    boolean saveGdsRule(GdsRule gdsRule);

    /**
     * 删除gds规则
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeGdsRule(String id);

    /**
     * 批量删除gds规则
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeGdsRuleByIds(List<String> ids);

    /**
     * 修改gds规则
     *
     * @param gdsRule gds规则
     * @return boolean
     */
    boolean updateGdsRule(GdsRule gdsRule);

    /**
     * id查询数据
     *
     * @param id id
     * @return GdsRule
     */
    GdsRule getGdsRuleById(String id);
}
