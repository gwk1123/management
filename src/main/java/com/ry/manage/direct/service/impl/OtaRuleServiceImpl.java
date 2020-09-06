package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.OtaRuleService;
import comm.repository.entity.OtaRule;
import comm.repository.mapper.OtaRuleMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 * GDS规则信息(ml_gds_rule)  服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class OtaRuleServiceImpl extends ServiceImpl<OtaRuleMapper, OtaRule> implements OtaRuleService {

    @Override
    public  IPage<OtaRule> pageOtaRule(Page<OtaRule> page,OtaRule otaRule){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<OtaRule> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOtaRule(OtaRule otaRule){
        Assert.notNull(otaRule, "GDS规则信息(ml_gds_rule) 为空");
        return this.save(otaRule);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOtaRule(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOtaRuleByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOtaRule(OtaRule otaRule){
        Assert.notNull(otaRule, "GDS规则信息(ml_gds_rule) 为空");
        return this.updateById(otaRule);
    }

    @Override
    public OtaRule getOtaRuleById(String id){
        return  this.getById(id);
    }
}
