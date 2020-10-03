package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sibecommon.repository.entity.SegmentRewardRule;
import com.sibecommon.repository.mapper.SegmentRewardRuleMapper;
import com.ry.manage.direct.service.SegmentRewardRuleService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 * 航段奖励规则配置 服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class SegmentRewardRuleServiceImpl extends ServiceImpl<SegmentRewardRuleMapper, SegmentRewardRule> implements SegmentRewardRuleService {

    @Override
    public  IPage<SegmentRewardRule> pageSegmentRewardRule(Page<SegmentRewardRule> page, SegmentRewardRule segmentRewardRule){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<SegmentRewardRule> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveSegmentRewardRule(SegmentRewardRule segmentRewardRule){
        Assert.notNull(segmentRewardRule, "航段奖励规则配置为空");
        return this.save(segmentRewardRule);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeSegmentRewardRule(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeSegmentRewardRuleByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSegmentRewardRule(SegmentRewardRule segmentRewardRule){
        Assert.notNull(segmentRewardRule, "航段奖励规则配置为空");
        return this.updateById(segmentRewardRule);
    }

    @Override
    public SegmentRewardRule getSegmentRewardRuleById(String id){
        return  this.getById(id);
    }
}
