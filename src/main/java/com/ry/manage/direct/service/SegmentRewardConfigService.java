package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.entity.SegmentRewardConfig;

import java.util.List;

/**
 * <p>
 * 航段奖励(ml_segment_reward_config)  服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface SegmentRewardConfigService extends IService<SegmentRewardConfig> {

    /**
     * 查询航段奖励(ml_segment_reward_config) 分页数据
     *
     * @param page      分页参数
     * @param segmentRewardConfig 查询条件
     * @return IPage<SegmentRewardConfig>
     */
     IPage<SegmentRewardConfig> pageSegmentRewardConfig(Page<SegmentRewardConfig> page,SegmentRewardConfig segmentRewardConfig);

    /**
     * 新增航段奖励(ml_segment_reward_config) 
     *
     * @param segmentRewardConfig 航段奖励(ml_segment_reward_config) 
     * @return boolean
     */
    boolean saveSegmentRewardConfig(SegmentRewardConfig segmentRewardConfig);

    /**
     * 删除航段奖励(ml_segment_reward_config) 
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeSegmentRewardConfig(String id);

    /**
     * 批量删除航段奖励(ml_segment_reward_config) 
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeSegmentRewardConfigByIds(List<String> ids);

    /**
     * 修改航段奖励(ml_segment_reward_config) 
     *
     * @param segmentRewardConfig 航段奖励(ml_segment_reward_config) 
     * @return boolean
     */
    boolean updateSegmentRewardConfig(SegmentRewardConfig segmentRewardConfig);

    /**
     * id查询数据
     *
     * @param id id
     * @return SegmentRewardConfig
     */
    SegmentRewardConfig getSegmentRewardConfigById(String id);
}
