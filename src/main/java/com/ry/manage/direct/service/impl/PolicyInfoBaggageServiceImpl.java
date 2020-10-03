package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.manage.direct.service.PolicyInfoBaggageService;
import com.sibecommon.repository.entity.PolicyInfoBaggage;
import com.sibecommon.repository.mapper.PolicyInfoBaggageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gwk
 * @since 2020-08-23
 */
@Service
public class PolicyInfoBaggageServiceImpl extends ServiceImpl<PolicyInfoBaggageMapper, PolicyInfoBaggage> implements PolicyInfoBaggageService {

    @Override
    public  IPage<PolicyInfoBaggage> pagePolicyInfoBaggage(Page<PolicyInfoBaggage> page, PolicyInfoBaggage policyInfoBaggage){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<PolicyInfoBaggage> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean savePolicyInfoBaggage(PolicyInfoBaggage policyInfoBaggage){
        Assert.notNull(policyInfoBaggage, "为空");
        return this.save(policyInfoBaggage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removePolicyInfoBaggage(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removePolicyInfoBaggageByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePolicyInfoBaggage(PolicyInfoBaggage policyInfoBaggage){
        Assert.notNull(policyInfoBaggage, "为空");
        return this.updateById(policyInfoBaggage);
    }

    @Override
    public PolicyInfoBaggage getPolicyInfoBaggageById(String id){
        return  this.getById(id);
    }
}
