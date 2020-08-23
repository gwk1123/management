package com.ry.manage.direct.redis.repositoryImpl;

import com.ry.manage.common.constant.DirectConstants;
import com.ry.manage.common.redis.RedisCache;
import com.ry.manage.common.utils.CopyUtils;
import com.ry.manage.direct.entity.PolicyInfo;
import com.ry.manage.direct.redis.RedisCrudRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PolicyInfoRepositoryImpl implements RedisCrudRepository<PolicyInfo> {

    private final RedisCache redisCache;
    private final static String REDIS_KEY = "sibePolicy";

    public PolicyInfoRepositoryImpl(RedisCache redisCache) {
        this.redisCache = redisCache;
    }


    @Override
    public PolicyInfo saveOrUpdate(PolicyInfo item) {
        PolicyInfo policyInfo = CopyUtils.deepCopy(item);
        if (policyInfo == null
//                || StringUtils.isEmpty(policyInfo.getOtaCode())
//                || StringUtils.isEmpty(policyInfo.getOtaSiteCode())
                || StringUtils.isEmpty(policyInfo.getAirline())
                || StringUtils.isEmpty(policyInfo.getTripType())){
            throw new IllegalStateException("细节政策不能为空");
        }

        //如果出发地，或者目的地为空，则不限(UNLIMIT)， 不转换为TC1/TC2/TC3(增加重复的内容)
        String depCityInfo = policyInfo.getDepCity();
        String arrCityInfo = policyInfo.getArrCity();

        depCityInfo = StringUtils.isEmpty(depCityInfo)? DirectConstants.ALL :depCityInfo;
        arrCityInfo = StringUtils.isEmpty(arrCityInfo)?DirectConstants.ALL:arrCityInfo;
        policyInfo.setDepCity(depCityInfo);
        policyInfo.setArrCity(arrCityInfo);
        saveOrUpdateCache(policyInfo);
        return null;
    }


    public PolicyInfo saveOrUpdateCache(PolicyInfo apiPolicyInfoRedis) {
        String key= this.getPolicyInfoRedisCacheKey(apiPolicyInfoRedis);
        redisCache.setCacheByKey(REDIS_KEY, key, apiPolicyInfoRedis);
        //s:site, a:airline, t:trip type, d:dep_city

        if(DirectConstants.TRIP_TYPE_ALL.equals(apiPolicyInfoRedis.getTripType())){
            //1 单程
            redisCache.setCacheSetBykey(REDIS_KEY + ":s:" + apiPolicyInfoRedis.getOtaSiteCode()
                            + ":t:1"
                    , key);

            redisCache.setCacheSetBykey(REDIS_KEY + ":s:" + apiPolicyInfoRedis.getOtaSiteCode()
                            + ":t:1"
                            + ":a:" + apiPolicyInfoRedis.getAirline()
                    , key);

            // 2 往返
            redisCache.setCacheSetBykey(REDIS_KEY + ":s:" + apiPolicyInfoRedis.getOtaSiteCode()
                            + ":t:2"
                    , key);

            redisCache.setCacheSetBykey(REDIS_KEY + ":s:" + apiPolicyInfoRedis.getOtaSiteCode()
                            + ":t:2"
                            + ":a:" + apiPolicyInfoRedis.getAirline()
                    , key);

        }else {
            redisCache.setCacheSetBykey(REDIS_KEY + ":s:" + apiPolicyInfoRedis.getOtaSiteCode()
                            + ":t:" + apiPolicyInfoRedis.getTripType()
                    , key);

            redisCache.setCacheSetBykey(REDIS_KEY + ":s:" + apiPolicyInfoRedis.getOtaSiteCode()
                            + ":t:" + apiPolicyInfoRedis.getTripType()
                            + ":a:" + apiPolicyInfoRedis.getAirline()
                    , key);
        }

        return apiPolicyInfoRedis;
    }


    public String getPolicyInfoRedisCacheKey(PolicyInfo apiPolicyInfoRedis) {
        StringBuilder sb = new StringBuilder();
        sb.append(apiPolicyInfoRedis.getId());
        sb.append("_");
        sb.append(apiPolicyInfoRedis.getAirline());
        sb.append("_");
        sb.append(apiPolicyInfoRedis.getTripType());
        return sb.toString();
    }


}
