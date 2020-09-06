package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.SegmentRewardConfigService;
import comm.repository.entity.SegmentRewardConfig;
import comm.repository.mapper.SegmentRewardConfigMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 * 航段奖励(ml_segment_reward_config)  服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class SegmentRewardConfigServiceImpl extends ServiceImpl<SegmentRewardConfigMapper, SegmentRewardConfig> implements SegmentRewardConfigService {

    @Override
    public  IPage<SegmentRewardConfig> pageSegmentRewardConfig(Page<SegmentRewardConfig> page, SegmentRewardConfig segmentRewardConfig){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<SegmentRewardConfig> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveSegmentRewardConfig(SegmentRewardConfig segmentRewardConfig){
        Assert.notNull(segmentRewardConfig, "航段奖励(ml_segment_reward_config) 为空");
        return this.save(segmentRewardConfig);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeSegmentRewardConfig(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeSegmentRewardConfigByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSegmentRewardConfig(SegmentRewardConfig segmentRewardConfig){
        Assert.notNull(segmentRewardConfig, "航段奖励(ml_segment_reward_config) 为空");
        return this.updateById(segmentRewardConfig);
    }

    @Override
    public SegmentRewardConfig getSegmentRewardConfigById(String id){
        return  this.getById(id);
    }
}
