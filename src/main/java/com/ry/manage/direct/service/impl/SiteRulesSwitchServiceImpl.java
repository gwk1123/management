package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.manage.direct.service.SiteRulesSwitchService;
import comm.repository.entity.SiteRulesSwitch;
import comm.repository.mapper.SiteRulesSwitchMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;


/**
 * <p>
 * 站点规则开关 服务实现类
 * </p>
 *
 * @author gwk
 * @since 2020-09-05
 */
@Service
public class SiteRulesSwitchServiceImpl extends ServiceImpl<SiteRulesSwitchMapper, SiteRulesSwitch> implements SiteRulesSwitchService {

    @Override
    public  IPage<SiteRulesSwitch> pageSiteRulesSwitch(Page<SiteRulesSwitch> page, SiteRulesSwitch siteRulesSwitch){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<SiteRulesSwitch> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveSiteRulesSwitch(SiteRulesSwitch siteRulesSwitch){
        Assert.notNull(siteRulesSwitch, "站点规则开关为空");
        return this.save(siteRulesSwitch);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeSiteRulesSwitch(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeSiteRulesSwitchByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSiteRulesSwitch(SiteRulesSwitch siteRulesSwitch){
        Assert.notNull(siteRulesSwitch, "站点规则开关为空");
        return this.updateById(siteRulesSwitch);
    }

    @Override
    public SiteRulesSwitch getSiteRulesSwitchById(String id){
        return  this.getById(id);
    }
}
