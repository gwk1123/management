package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.manage.direct.service.GdsRuleService;
import com.sibecommon.repository.entity.GdsRule;
import com.sibecommon.repository.mapper.GdsRuleMapper;
import com.sibecommon.utils.redis.impl.GdsRuleRepositoryImpl;
import com.sibecommon.utils.constant.DirectConstants;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private GdsRuleRepositoryImpl gdsRuleRepository;

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
        this.save(gdsRule);
        if(DirectConstants.NORMAL.equals(gdsRule.getStatus())){
            gdsRuleRepository.saveOrUpdateCache(gdsRule);
        }else {
            gdsRuleRepository.delete(gdsRule);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeGdsRule(String id){
        Assert.hasText(id, "主键为空");
        GdsRule gdsRule =this.getById(id);
        gdsRule.setStatus(DirectConstants.DELETE);
        this.updateById(gdsRule);
        gdsRuleRepository.delete(gdsRule);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeGdsRuleByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        ids.stream().forEach(e ->{
            removeGdsRule(e);
        });
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateGdsRule(GdsRule gdsRule){
        Assert.notNull(gdsRule, "gds规则为空");
        this.updateById(gdsRule);
        if(DirectConstants.NORMAL.equals(gdsRule.getStatus())){
            gdsRuleRepository.saveOrUpdateCache(gdsRule);
        }else {
            gdsRuleRepository.delete(gdsRule);
        }
        return true;
    }

    @Override
    public GdsRule getGdsRuleById(String id){
        return  this.getById(id);
    }
}
