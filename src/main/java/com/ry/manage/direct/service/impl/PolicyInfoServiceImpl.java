package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ry.manage.common.constant.DirectConstants;
import com.ry.manage.direct.entity.PolicyInfo;
import com.ry.manage.direct.entity.PolicyInfoBaggage;
import com.ry.manage.direct.mapper.PolicyInfoMapper;
import com.ry.manage.direct.redis.RedisCrudRepository;
import com.ry.manage.direct.service.PolicyInfoBaggageService;
import com.ry.manage.direct.service.PolicyInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gwk
 * @since 2020-08-22
 */
@Service
public class PolicyInfoServiceImpl extends ServiceImpl<PolicyInfoMapper, PolicyInfo> implements PolicyInfoService {

    private final PolicyInfoBaggageService policyInfoBaggageService;

    private final RedisCrudRepository redisCrudRepository;

    public PolicyInfoServiceImpl(PolicyInfoBaggageService policyInfoBaggageService, RedisCrudRepository redisCrudRepository) {
        this.policyInfoBaggageService = policyInfoBaggageService;
        this.redisCrudRepository = redisCrudRepository;
    }

    @Override
    public IPage<PolicyInfo> pagePolicyInfo(Page<PolicyInfo> page, PolicyInfo policyInfo) {

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<PolicyInfo> queryWrapper = new QueryWrapper<>();

        return this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean savePolicyInfo(PolicyInfo policyInfo) {
        Assert.notNull(policyInfo, "为空");
        this.save(policyInfo);
        if (policyInfo.getBaggageType() == 1) {
            List<PolicyInfoBaggage> policyInfoBaggages = new ArrayList<>();
            PolicyInfoBaggage adultPolicyInfoBaggage = new PolicyInfoBaggage();
            adultPolicyInfoBaggage.setPolicyId(policyInfo.getId());
            adultPolicyInfoBaggage.setPassengerType(0);
            adultPolicyInfoBaggage.setBaggagePieces(policyInfo.getAdultBaggagePieces());
            adultPolicyInfoBaggage.setBaggageWeight(policyInfo.getAdultBaggageWeight());
            policyInfoBaggages.add(adultPolicyInfoBaggage);

            PolicyInfoBaggage childPolicyInfoBaggage = new PolicyInfoBaggage();
            adultPolicyInfoBaggage.setPolicyId(policyInfo.getId());
            childPolicyInfoBaggage.setPassengerType(1);
            childPolicyInfoBaggage.setBaggagePieces(policyInfo.getChildBaggagePieces());
            childPolicyInfoBaggage.setBaggageWeight(policyInfo.getChildBaggageWeight());
            policyInfoBaggages.add(childPolicyInfoBaggage);
            policyInfoBaggageService.saveBatch(policyInfoBaggages);
        }
        redisCrudRepository.saveOrUpdate(policyInfo);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removePolicyInfo(String id) {
        Assert.hasText(id, "主键为空");
        PolicyInfo policyInfo = this.getById(id);
        policyInfo.setStatus(DirectConstants.DELETE);
        return this.updateById(policyInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removePolicyInfoByIds(List<String> ids) {
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePolicyInfo(PolicyInfo policyInfo) {
        Assert.notNull(policyInfo, "为空");
        if (policyInfo.getBaggageType() == 1) {
            QueryWrapper<PolicyInfoBaggage> baggageQueryWrapper = new QueryWrapper<>();
            baggageQueryWrapper.lambda().eq(PolicyInfoBaggage::getPolicyId, policyInfo.getId())
                    .eq(PolicyInfoBaggage::getPassengerType, "0");
            PolicyInfoBaggage adultPolicyInfoBaggage = policyInfoBaggageService.getOne(baggageQueryWrapper);
            if (adultPolicyInfoBaggage == null) {
                adultPolicyInfoBaggage = new PolicyInfoBaggage();
                adultPolicyInfoBaggage.setPolicyId(policyInfo.getId());
                adultPolicyInfoBaggage.setPassengerType(0);
            }
            adultPolicyInfoBaggage.setBaggagePieces(policyInfo.getAdultBaggagePieces());
            adultPolicyInfoBaggage.setBaggageWeight(policyInfo.getAdultBaggageWeight());
            policyInfoBaggageService.saveOrUpdate(adultPolicyInfoBaggage, baggageQueryWrapper);

            QueryWrapper<PolicyInfoBaggage> childQueryWrapper = new QueryWrapper<>();
            childQueryWrapper.lambda().eq(PolicyInfoBaggage::getPolicyId, policyInfo.getId())
                    .eq(PolicyInfoBaggage::getPassengerType, "1");
            PolicyInfoBaggage childPolicyInfoBaggage = policyInfoBaggageService.getOne(childQueryWrapper);
            if (childPolicyInfoBaggage == null) {
                childPolicyInfoBaggage = new PolicyInfoBaggage();
                childPolicyInfoBaggage.setPolicyId(policyInfo.getId());
                childPolicyInfoBaggage.setPassengerType(1);
            }
            childPolicyInfoBaggage.setBaggagePieces(policyInfo.getChildBaggagePieces());
            childPolicyInfoBaggage.setBaggageWeight(policyInfo.getChildBaggageWeight());
            policyInfoBaggageService.saveOrUpdate(childPolicyInfoBaggage, childQueryWrapper);
        }
        redisCrudRepository.saveOrUpdate(policyInfo);
        return this.updateById(policyInfo);
    }

    @Override
    public PolicyInfo getPolicyInfoById(String id) {
        return this.getById(id);
    }

    @Override
    public boolean changePolicyInfoStatus(PolicyInfo policyInfo) {
        PolicyInfo status = this.getById(policyInfo.getId());
        status.setStatus(policyInfo.getStatus());
        return this.updateById(status);
    }
}
