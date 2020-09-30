package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.manage.direct.service.GdsRuleService;
import comm.repository.entity.GdsRule;
import comm.repository.mapper.GdsRuleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;


/**
 * <p>
 * gds规则 服务实现类
 * </p>
 *
 * @author gwk
 * @since 2020-09-12
 */
@Service
public class GdsRuleServiceImpl extends ServiceImpl<GdsRuleMapper, GdsRule> implements GdsRuleService {

    @Override
    public  IPage<GdsRule> pageGdsRule(Page<GdsRule> page,GdsRule gdsRule){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<GdsRule> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveGdsRule(GdsRule gdsRule){
        Assert.notNull(gdsRule, "gds规则为空");
        return this.save(gdsRule);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeGdsRule(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeGdsRuleByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateGdsRule(GdsRule gdsRule){
        Assert.notNull(gdsRule, "gds规则为空");
        return this.updateById(gdsRule);
    }

    @Override
    public GdsRule getGdsRuleById(String id){
        return  this.getById(id);
    }
}
