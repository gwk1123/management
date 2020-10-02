package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.manage.direct.service.PolicyGlobalService;
import comm.repository.entity.PolicyGlobal;
import comm.repository.mapper.PolicyGlobalMapper;
import comm.utils.constant.DirectConstants;
import comm.utils.redis.impl.PolicyGlobalRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PolicyGlobalServiceImpl extends ServiceImpl<PolicyGlobalMapper, PolicyGlobal> implements PolicyGlobalService {

    @Autowired
    private PolicyGlobalRepositoryImpl policyGlobalRepository;

    @Override
    public  IPage<PolicyGlobal> pagePolicyGlobal(Page<PolicyGlobal> page,PolicyGlobal policyGlobal){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<PolicyGlobal> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean savePolicyGlobal(PolicyGlobal policyGlobal){
        Assert.notNull(policyGlobal, "为空");
        this.save(policyGlobal);
        if(DirectConstants.NORMAL.equals(policyGlobal.getStatus())){
            policyGlobalRepository.saveOrUpdateCache(policyGlobal);
        }else {
            policyGlobalRepository.delete(policyGlobal);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removePolicyGlobal(String id){
        Assert.hasText(id, "主键为空");
        PolicyGlobal policyGlobal=this.getById(id);
        policyGlobal.setStatus(DirectConstants.DELETE);
        policyGlobalRepository.delete(policyGlobal);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removePolicyGlobalByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        ids.stream().forEach(e ->{
            removePolicyGlobal(e);
        });
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePolicyGlobal(PolicyGlobal policyGlobal){
        Assert.notNull(policyGlobal, "为空");
        this.updateById(policyGlobal);
        if(DirectConstants.NORMAL.equals(policyGlobal.getStatus())){
            policyGlobalRepository.saveOrUpdateCache(policyGlobal);
        }else {
            policyGlobalRepository.delete(policyGlobal);
        }
        return true;
    }

    @Override
    public PolicyGlobal getPolicyGlobalById(String id){
        return  this.getById(id);
    }

    @Override
    public boolean changePolicyGlobalStatus(PolicyGlobal policyGlobal) {
        PolicyGlobal status = this.getById(policyGlobal.getId());
        status.setStatus(policyGlobal.getStatus());
        this.updateById(status);
        if(DirectConstants.NORMAL.equals(status.getStatus())){
            policyGlobalRepository.saveOrUpdateCache(policyGlobal);
        }else {
            policyGlobalRepository.delete(policyGlobal);
        }
        return true;
    }
}
