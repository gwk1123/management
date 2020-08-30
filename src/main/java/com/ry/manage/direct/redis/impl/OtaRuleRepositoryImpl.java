package com.ry.manage.direct.redis.impl;

import com.ry.manage.common.constant.DirectConstants;
import com.ry.manage.common.exception.CustomException;
import com.ry.manage.common.redis.RedisCache;
import com.ry.manage.common.utils.CopyUtils;
import com.ry.manage.direct.entity.OtaRule;
import com.ry.manage.direct.redis.util.RedisCacheKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author gwk
 */
@Component
public class OtaRuleRepositoryImpl {

    private final RedisCache redisCache;
    private final static String REDIS_KEY = "sibeOtaRule";

    public OtaRuleRepositoryImpl(RedisCache redisCache) {
        this.redisCache = redisCache;
    }

    public OtaRule saveOrUpdate(OtaRule item) {
        OtaRule otaRule = CopyUtils.deepCopy(item);
        if (otaRule == null
                || StringUtils.isEmpty(otaRule.getOtaCode())
                || StringUtils.isEmpty(otaRule.getOtaSiteCode())
                || StringUtils.isEmpty(otaRule.getRuleType())
        ) {
            throw new CustomException("OTA规则信息不能为空");
        }
        //如果出发地，或者目的地为空，则不限(UNLIMIT)， 不转换为TC1/TC2/TC3(增加重复的内容)
        String originInfo = otaRule.getOrigin();
        String destinationInfo = otaRule.getDestination();
        originInfo = StringUtils.isEmpty(originInfo) ? DirectConstants.ALL  : originInfo;
        destinationInfo = StringUtils.isEmpty(destinationInfo) ? DirectConstants.ALL : destinationInfo;
        otaRule.setOrigin(originInfo);
        otaRule.setDestination(destinationInfo);
        return this.saveOrUpdateCache(otaRule);
    }

    public OtaRule saveOrUpdateCache(OtaRule otaRule) {
        String key = RedisCacheKeyUtil.getOtaRuleCacheKey(otaRule);
        redisCache.saveCacheHashByKey(REDIS_KEY, key, otaRule);
        redisCache.saveCacheSetBykey(REDIS_KEY+":s:"+otaRule.getOtaSiteCode()+":r:"+
                otaRule.getRuleType(),key);
        return otaRule;
    }


    public void delete(OtaRule otaRule) {
        String key = RedisCacheKeyUtil.getOtaRuleCacheKey(otaRule);
        redisCache.deleteCacheHashByKey(REDIS_KEY, key);
        redisCache.deleteCacheSetBykey(REDIS_KEY+":s:"+otaRule.getOtaSiteCode()+":r:"+
                otaRule.getRuleType(),key);
    }

}
