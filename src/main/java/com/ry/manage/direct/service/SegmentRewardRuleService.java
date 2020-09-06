package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import comm.repository.entity.SegmentRewardRule;

import java.util.List;

/**
 * <p>
 * 航段奖励规则配置 服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface SegmentRewardRuleService extends IService<SegmentRewardRule> {

    /**
     * 查询航段奖励规则配置分页数据
     *
     * @param page      分页参数
     * @param segmentRewardRule 查询条件
     * @return IPage<SegmentRewardRule>
     */
     IPage<SegmentRewardRule> pageSegmentRewardRule(Page<SegmentRewardRule> page, SegmentRewardRule segmentRewardRule);

    /**
     * 新增航段奖励规则配置
     *
     * @param segmentRewardRule 航段奖励规则配置
     * @return boolean
     */
    boolean saveSegmentRewardRule(SegmentRewardRule segmentRewardRule);

    /**
     * 删除航段奖励规则配置
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeSegmentRewardRule(String id);

    /**
     * 批量删除航段奖励规则配置
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeSegmentRewardRuleByIds(List<String> ids);

    /**
     * 修改航段奖励规则配置
     *
     * @param segmentRewardRule 航段奖励规则配置
     * @return boolean
     */
    boolean updateSegmentRewardRule(SegmentRewardRule segmentRewardRule);

    /**
     * id查询数据
     *
     * @param id id
     * @return SegmentRewardRule
     */
    SegmentRewardRule getSegmentRewardRuleById(String id);
}
