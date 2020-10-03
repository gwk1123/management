package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.OtaRuleService;
import com.sibecommon.repository.entity.OtaRule;
import com.sibecommon.repository.mapper.OtaRuleMapper;
import com.sibecommon.utils.constant.DirectConstants;
import com.sibecommon.utils.redis.impl.OtaRuleRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private OtaRuleRepositoryImpl otaRuleRepository;

    @Override
    public  IPage<OtaRule> pageOtaRule(Page<OtaRule> page,OtaRule otaRule){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<OtaRule> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOtaRule(OtaRule otaRule){
        Assert.notNull(otaRule, "otaRule为空");
        this.save(otaRule);
        if(DirectConstants.NORMAL.equals(otaRule.getStatus())){
            otaRuleRepository.saveOrUpdateCache(otaRule);
        }else {
            otaRuleRepository.delete(otaRule);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOtaRule(String id){
        Assert.hasText(id, "主键为空");
        OtaRule otaRule =this.getById(id);
        otaRule.setStatus(DirectConstants.DELETE);
        otaRuleRepository.delete(otaRule);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOtaRuleByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        ids.stream().forEach(e ->{
            removeOtaRule(e);
        });
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOtaRule(OtaRule otaRule){
        Assert.notNull(otaRule, "GDS规则信息(ml_gds_rule) 为空");
        this.updateById(otaRule);
        if(DirectConstants.NORMAL.equals(otaRule.getStatus())){
            otaRuleRepository.saveOrUpdateCache(otaRule);
        }else {
            otaRuleRepository.delete(otaRule);
        }
        return true;
    }

    @Override
    public OtaRule getOtaRuleById(String id){
        return  this.getById(id);
    }
}
