package com.ry.manage.direct.redis.impl;

import com.ry.manage.common.exception.CustomException;
import com.ry.manage.common.redis.RedisCache;
import com.ry.manage.common.utils.CopyUtils;
import com.ry.manage.direct.entity.PolicyGlobal;
import com.ry.manage.direct.redis.util.RedisCacheKeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author gwk
 */
@Component
public class PolicyGlobalRepositoryImpl {

    private final RedisCache redisCache;
    private final static String REDIS_KEY = "sibePolicyGlobal";

    public PolicyGlobalRepositoryImpl(RedisCache redisCache) {
        this.redisCache = redisCache;
    }


    public PolicyGlobal saveOrUpdate(PolicyGlobal item) {
        PolicyGlobal policyGlobal = CopyUtils.deepCopy(item);
        if (policyGlobal == null
                || StringUtils.isEmpty(policyGlobal.getOtaSiteCode())
                || StringUtils.isEmpty(policyGlobal.getOtaCode())
        ) {
            throw new CustomException("OTA规则信息不能为空");
        }
        return this.saveOrUpdateCache(policyGlobal);
    }

    public PolicyGlobal saveOrUpdateCache(PolicyGlobal policyGlobal) {
        String key = RedisCacheKeyUtil.getPolicyGlobalRedisCacheKey(policyGlobal);
        redisCache.saveCacheHashByKey(REDIS_KEY, key, policyGlobal);
        redisCache.saveCacheSetBykey(REDIS_KEY + ":s:" + policyGlobal.getOtaSiteCode(), key);
        return policyGlobal;
    }


    public void delete(PolicyGlobal policyGlobal) {
        String key = RedisCacheKeyUtil.getPolicyGlobalRedisCacheKey(policyGlobal);
        redisCache.deleteCacheHashByKey(REDIS_KEY, key, policyGlobal);
        redisCache.deleteCacheSetBykey(REDIS_KEY + ":s:" + policyGlobal.getOtaSiteCode(), key);
    }
}
