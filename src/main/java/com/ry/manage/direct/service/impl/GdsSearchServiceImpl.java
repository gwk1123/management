package com.ry.manage.direct.service.impl;

import com.ry.manage.direct.model.GdsSearchVm;
import com.ry.manage.direct.model.GdsSearchVo;
import com.ry.manage.direct.model.LocalSiteSearchVo;
import com.ry.manage.direct.service.GdsSearchService;
import com.sibecommon.ota.site.SibeRouting;
import com.sibecommon.ota.site.SibeSearchRequest;
import com.sibecommon.ota.site.SibeSearchResponse;
import com.sibecommon.ota.site.SibeSegment;
import com.sibecommon.utils.async.SibeSearchAsyncService;
import com.sibecommon.utils.redis.GdsCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GdsSearchServiceImpl implements GdsSearchService {

    private Logger logger  = LoggerFactory.getLogger(GdsSearchServiceImpl.class);
    private final String TRIP_TYPE_ROUND_WAY = "2";

    @Autowired
    private GdsCacheService gdsCacheService;
    @Autowired
    private SibeSearchAsyncService sibeSearchAsyncService;

    @Override
    public GdsSearchVo findGdsSearch(GdsSearchVm gdsSearchVm) throws InterruptedException {
        //1.获取缓存中的内容
        String cacheKey = generateCacheKey(gdsSearchVm);
        //获取GDS的Key
        Set<String> cacheContentKeySet = gdsCacheService.findAllKeys(cacheKey);
        //2.如果无缓存，则发起同步GDS请求
        //2.1生成同步步请求GDS对象
        if(cacheContentKeySet == null || cacheContentKeySet.size() == 0){
            SibeSearchRequest sibeSearchRequest = generateSibeSearchRequest(gdsSearchVm);
            sibeSearchAsyncService.requestGdsAsyncB2C(sibeSearchRequest);
        }
        Thread.sleep(5000);
        cacheContentKeySet = gdsCacheService.findAllKeys(cacheKey);
        GdsSearchVo gdsSearchVo =new GdsSearchVo();
        if(cacheContentKeySet == null || cacheContentKeySet.size() == 0){
            return gdsSearchVo;
        }

        //处理GDS缓存
        List<SibeSearchResponse> sibeSearchResponses = new ArrayList<>();
        cacheContentKeySet.stream().forEach(gdsKey ->{
            SibeSearchResponse sibeSearchResponse = (SibeSearchResponse) gdsCacheService.findOne(cacheKey, gdsKey, 1);
            sibeSearchResponses.add(sibeSearchResponse);
        });

        return null;
    }


    /**
     * 生成search的缓存主key
     * @param inputVM
     * @return
     */
    private String generateCacheKey(GdsSearchVm inputVM) {
        StringBuilder cacheKeyBuilder = new StringBuilder();

        cacheKeyBuilder
                .append(inputVM.getFromCity())
                .append(inputVM.getToCity())
                .append(inputVM.getFromDate());

        if(inputVM.getTripType().equals(TRIP_TYPE_ROUND_WAY)){
            cacheKeyBuilder.append(inputVM.getRetDate());
        }
        String cacheKey = cacheKeyBuilder.toString();
        return cacheKey;
    }

    private SibeSearchRequest generateSibeSearchRequest(GdsSearchVm inputVM) {
        SibeSearchRequest sibeOrderRequest = new SibeSearchRequest();
        sibeOrderRequest.setTripType(inputVM.getTripType());//todo 确认tripType的内容
        sibeOrderRequest.setFromCity(inputVM.getFromCity());
        sibeOrderRequest.setToCity(inputVM.getToCity());
        sibeOrderRequest.setFromDate(inputVM.getFromDate());
        sibeOrderRequest.setRetDate(inputVM.getRetDate());

        sibeOrderRequest.setDirectOnly(false);
        sibeOrderRequest.setOtaCarrier("");

        //新的查询，使用新的Uid
        String searchUuid = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddHHmmss")) + UUID.randomUUID().toString().split("-")[4];
        sibeOrderRequest.setUuid(searchUuid);
//        sibeOrderRequest.setB2CSearchSiteInfoList(inputVM.getSites());

        return sibeOrderRequest;
    }

    public GdsSearchVo handleGdsInfo(List<SibeSearchResponse> sibeSearchResponses,GdsSearchVm gdsSearchVm){
        GdsSearchVo gdsSearchVo=new GdsSearchVo();
        Map<String, LocalSiteSearchVo> localSiteSearchVoMap = new ConcurrentHashMap<>();
        String tripType = gdsSearchVm.getTripType();
        sibeSearchResponses.stream().filter(Objects::nonNull).forEach(sibeResponse ->{
            sibeResponse.getRoutings().stream().filter(Objects::nonNull).forEach(routing ->{

            });
        });
        return gdsSearchVo;
    }


    /**
     * 生成行程方案唯一特征key：航司航班号-舱位-出发时间;
     * @param routing
     * @param tripType
     * @return
     */
    private String generateGeneralGdsKey(SibeRouting routing, String tripType) {
        StringBuilder generalPlanKeyBuilder = new StringBuilder();

        generalPlanKeyBuilder.append(routing.getValidatingCarrier());
        generateGdsKey(generalPlanKeyBuilder, routing.getFromSegments());

        if(tripType.equals(TRIP_TYPE_ROUND_WAY)){
            generateGdsKey(generalPlanKeyBuilder, routing.getRetSegments());
        }

        return generalPlanKeyBuilder.toString();
    }

    private void generateGdsKey(StringBuilder generalPlanKeyBuilder, List<SibeSegment> segmentList) {
        for (SibeSegment segment : segmentList) {
            generalPlanKeyBuilder
                    .append(segment.getCarrier())
                    .append(segment.getFlightNumber())
                    .append("-").append(segment.getCabin())
                    .append("-").append(segment.getDepTime())
                    .append("-").append(segment.getArrTime())
                    .append(";");
        }
    }

    public void a(Map<String, LocalSiteSearchVo> localSiteSearchVoMap,SibeRouting sibeRouting,String tripType){
        String gdsKey =this.generateGeneralGdsKey(sibeRouting, tripType);
        if(localSiteSearchVoMap.containsKey(gdsKey)) {
            LocalSiteSearchVo localSiteSearchVo = new LocalSiteSearchVo();
            localSiteSearchVo.getSegmentInfo().setFromSegments(sibeRouting.getFromSegments());
            localSiteSearchVo.getSegmentInfo().setRetSegments(sibeRouting.getRetSegments());
            localSiteSearchVo.getSegmentInfo().setValidatingCarrier(sibeRouting.getValidatingCarrier());
        }else {

        }

    }


}
