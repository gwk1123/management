package com.ry.manage.direct.service.impl;

import com.alibaba.fastjson.JSON;
import com.ry.manage.direct.model.*;
import com.ry.manage.direct.service.GdsSearchService;
import com.sibecommon.ota.ctrip.model.CtripRouting;
import com.sibecommon.ota.ctrip.model.CtripSearchResponse;
import com.sibecommon.ota.site.*;
import com.sibecommon.utils.async.SibeSearchAsyncService;
import com.sibecommon.utils.exception.CustomException;
import com.sibecommon.utils.redis.GdsCacheService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GdsSearchServiceImpl implements GdsSearchService {

    private Logger logger = LoggerFactory.getLogger(GdsSearchServiceImpl.class);
    private static final String TRIP_TYPE_ROUND_WAY = "2";
    private static final String PLATFORM_CTRIP = "CTRIP";

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
        if (CollectionUtils.isEmpty(cacheContentKeySet)) {
            SibeSearchRequest sibeSearchRequest = generateSibeSearchRequest(gdsSearchVm);
            sibeSearchAsyncService.requestGdsAsyncB2C(sibeSearchRequest);
            Thread.sleep(5000);
        }
        cacheContentKeySet = gdsCacheService.findAllKeys(cacheKey);
        GdsSearchVo gdsSearchVo = new GdsSearchVo();
        if (cacheContentKeySet == null || cacheContentKeySet.size() == 0) {
            return gdsSearchVo;
        }

        Long t1 = System.currentTimeMillis();
        //处理GDS缓存
        List<SibeSearchResponse> sibeSearchResponses = new ArrayList<>();
        cacheContentKeySet.stream().forEach(gdsKey -> {
            SibeSearchResponse sibeSearchResponse = (SibeSearchResponse) gdsCacheService.findOne(cacheKey, gdsKey, 1);
            sibeSearchResponses.add(sibeSearchResponse);
        });
        Long t2 = System.currentTimeMillis();
        logger.info("获取GDS数据耗时:{}s", (t2 - t1) / 1000);
        //处理GDS
        gdsSearchVo = this.handleGdsInfo(sibeSearchResponses, gdsSearchVm);
        //处理站点
        for (String otaSite : gdsSearchVm.getOtaSites()) {
            String ota = "CTRIP";
            handleOtaSite(ota, otaSite, gdsSearchVo, cacheKey, gdsSearchVm.getTripType());
        }
        logger.info("处理GDS和站点数据耗时:{}s", (System.currentTimeMillis() - t2) / 1000);

        LocalGdsSearchVo localGdsSearchVo = new LocalGdsSearchVo();
        localGdsSearchVo.setOtaSite(gdsSearchVm.getOtaSites());
        localGdsSearchVo.setGdsSource(cacheContentKeySet);
        gdsSearchVo.setLocalGdsSearchVo(localGdsSearchVo);
        return gdsSearchVo;
    }


    /**
     * 生成search的缓存主key
     *
     * @param inputVM
     * @return
     */
    private String generateCacheKey(GdsSearchVm inputVM) {
        StringBuilder cacheKeyBuilder = new StringBuilder();

        cacheKeyBuilder
                .append(inputVM.getFromCity())
                .append(inputVM.getToCity())
                .append(inputVM.getFromDate());

        if (inputVM.getTripType().equals(TRIP_TYPE_ROUND_WAY)) {
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

    public GdsSearchVo handleGdsInfo(List<SibeSearchResponse> sibeSearchResponses, GdsSearchVm gdsSearchVm) {
        GdsSearchVo gdsSearchVo = new GdsSearchVo();
        Map<String, LocalSiteSearchVo> localSiteSearchVoMap = new ConcurrentHashMap<>();
        String tripType = gdsSearchVm.getTripType();
        sibeSearchResponses.stream().filter(Objects::nonNull).forEach(sibeResponse -> {
            sibeResponse.getRoutings().stream().filter(Objects::nonNull).forEach(sibeRouting -> {
                conversionGdsInfo(localSiteSearchVoMap, sibeRouting, tripType);
            });
        });
        gdsSearchVo.setLocalSiteSearchVoMap(localSiteSearchVoMap);
        return gdsSearchVo;
    }


    /**
     * 生成行程方案唯一特征key：航司航班号-舱位-出发时间;
     *
     * @param routing
     * @param tripType
     * @return
     */
    private String generateGeneralGdsKey(SibeRouting routing, String tripType) {
        StringBuilder generalPlanKeyBuilder = new StringBuilder();
        generateGdsKey(generalPlanKeyBuilder, routing.getFromSegments());
        if (tripType.equals(TRIP_TYPE_ROUND_WAY)) {
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
                    .append("-").append(segment.getDepAirport())
                    .append("-").append(segment.getArrAirport())
                    .append("-").append(segment.getDepTime())
                    .append("-").append(segment.getArrTime())
                    .append(";");
        }
    }

    public void conversionGdsInfo(Map<String, LocalSiteSearchVo> localSiteSearchVoMap, SibeRouting sibeRouting, String tripType) {
        String gdsKey = this.generateGeneralGdsKey(sibeRouting, tripType);
        if (!localSiteSearchVoMap.containsKey(gdsKey)) {
            LocalSiteSearchVo localSiteSearchVo = new LocalSiteSearchVo();
            SegmentInfo segmentInfo = new SegmentInfo();
            segmentInfo.setFromSegments(sibeRouting.getFromSegments());
            segmentInfo.setRetSegments(sibeRouting.getRetSegments());
            segmentInfo.setValidatingCarrier(sibeRouting.getValidatingCarrier());
            localSiteSearchVo.setSegmentInfo(segmentInfo);

            GdsInfoVo gdsInfoVo = new GdsInfoVo();
            gdsInfoVo.setAdultPriceGds(String.valueOf(sibeRouting.getAdultPriceGDS()));
            gdsInfoVo.setAdultTaxGds(String.valueOf(sibeRouting.getAdultTaxGDS()));
            gdsInfoVo.setChildPriceGds(String.valueOf(sibeRouting.getChildPriceGDS()));
            gdsInfoVo.setChildTaxGds(String.valueOf(sibeRouting.getChildTaxGDS()));
            gdsInfoVo.setGds(sibeRouting.getReservationType());
            gdsInfoVo.setPcc(sibeRouting.getOfficeId());
            gdsInfoVo.setSibeRouting(sibeRouting);
            List<GdsInfoVo> gdsInfoVoList = new ArrayList<>();
            gdsInfoVoList.add(gdsInfoVo);
            localSiteSearchVo.setGdsInfoVos(gdsInfoVoList);
            localSiteSearchVoMap.put(gdsKey, localSiteSearchVo);
        } else {
            GdsInfoVo gdsInfoVo = new GdsInfoVo();
            gdsInfoVo.setAdultPriceGds(String.valueOf(sibeRouting.getAdultPriceGDS()));
            gdsInfoVo.setAdultTaxGds(String.valueOf(sibeRouting.getAdultTaxGDS()));
            gdsInfoVo.setChildPriceGds(String.valueOf(sibeRouting.getChildPriceGDS()));
            gdsInfoVo.setChildTaxGds(String.valueOf(sibeRouting.getChildTaxGDS()));
            gdsInfoVo.setSibeRouting(sibeRouting);
            localSiteSearchVoMap.get(gdsKey).getGdsInfoVos().add(gdsInfoVo);
        }
    }

    public void handleOtaSite(String ota, String otaSite, GdsSearchVo gdsSearchVo, String cacheKey, String tripType) {
        Object cacheObj = gdsCacheService.findString(cacheKey + "_" + ota + "-" + otaSite);
        setSearchSiteInfoToSearchInfoDTO(ota, cacheObj, tripType, gdsSearchVo, otaSite);
    }

    private void setSearchSiteInfoToSearchInfoDTO(String ota, Object cacheObj, String tripType, GdsSearchVo gdsSearchVo, String otaSite) {
        switch (ota) {
            case PLATFORM_CTRIP:
                CtripSearchResponse ctripSearchResponse = JSON.parseObject(cacheObj.toString(), CtripSearchResponse.class);
                CtripRouting ctripRouting = ctripSearchResponse.getRoutings().get(0);
                String[] dataKeyArray = StringUtils.split(ctripRouting.getData(), "|");
                Object data = gdsCacheService.findOne(dataKeyArray[0]);
                Map<String, String> dataInfoMapFromRedis = (Map<String, String>) data;
                if (!CollectionUtils.isEmpty(dataInfoMapFromRedis)) {
                    conversionOtaSiteInfo(dataInfoMapFromRedis, gdsSearchVo, tripType, otaSite);
                }
                break;
            default:
        }
    }

    public void conversionOtaSiteInfo(Map<String, String> dataInfoMap, GdsSearchVo gdsSearchVo, String tripType, String otaSite) {
        Map<String, LocalSiteSearchVo> localSiteSearchVoMap = gdsSearchVo.getLocalSiteSearchVoMap();
        Collection<String> sibeRoutingDatas = dataInfoMap.values();
        sibeRoutingDatas.stream().filter(Objects::nonNull).forEach(dataStr -> {
            SibeRoutingData sibeRoutingData = JSON.parseObject(dataStr, SibeRoutingData.class);
            String otaSiteKey = generateGeneralPlanKeyForCtrip(tripType, sibeRoutingData);
            if (localSiteSearchVoMap.containsKey(otaSiteKey)) {
                LocalSiteSearchVo localSiteSearchVo = localSiteSearchVoMap.get(otaSiteKey);
                if (CollectionUtils.isEmpty(localSiteSearchVo.getSiteInfoVos())) {
                    List<SiteInfoVo> siteInfoVos = new ArrayList<>();
                    SiteInfoVo siteInfoVo = setOtaSiteInfo(sibeRoutingData, otaSite);
                    siteInfoVos.add(siteInfoVo);
                    localSiteSearchVo.setSiteInfoVos(siteInfoVos);
                } else {
                    SiteInfoVo siteInfoVo = setOtaSiteInfo(sibeRoutingData, otaSite);
                    localSiteSearchVo.getSiteInfoVos().add(siteInfoVo);
                }
            } else {
                //GDS的数据一定包含站点的数据
                throw new CustomException("data异常");
            }
        });

    }


    /**
     * 从携程的routing中获取信息，生成飞行方案key
     *
     * @param tripType
     * @return
     */
    private static String generateGeneralPlanKeyForCtrip(String tripType, SibeRoutingData sibeRoutingData) {
        StringBuilder generalPlanKeyBuilder = new StringBuilder();

        List<SibeSegment> segmentList = new ArrayList<>();
        segmentList.addAll(sibeRoutingData.getFromSegments());
        if (tripType.equals(TRIP_TYPE_ROUND_WAY)) {
            segmentList.addAll(sibeRoutingData.getRetSegments());
        }
        generatePlanKeyForCtrip(generalPlanKeyBuilder, segmentList, sibeRoutingData);
        return generalPlanKeyBuilder.toString();
    }

    /**
     * 生成携程某个航程的飞行方案key
     *
     * @param generalPlanKeyBuilder
     * @param segmentList
     */
    private static void generatePlanKeyForCtrip(StringBuilder generalPlanKeyBuilder, List<SibeSegment> segmentList, SibeRoutingData sibeRoutingData) {
        String[] originalCabins = sibeRoutingData.getSibePolicy().getOriginalCabins().split("-");

        for (int i = 0; i < segmentList.size(); i++) {
            SibeSegment segment = segmentList.get(i);
            generalPlanKeyBuilder
                    .append(segment.getCarrier())
                    .append(Integer.parseInt(segment.getFlightNumber()))
                    .append("-").append(originalCabins[i])
                    .append("-").append(segment.getDepAirport())
                    .append("-").append(segment.getArrAirport())
                    .append("-").append(segment.getDepTime())
                    .append("-").append(segment.getArrTime())
                    .append(";");
        }
    }

    public SiteInfoVo setOtaSiteInfo(SibeRoutingData sibeRoutingData, String otaSite) {
        SiteInfoVo siteInfoVo = new SiteInfoVo();
        siteInfoVo.setAdultPriceOta(String.valueOf(sibeRoutingData.getSibePolicy().getPolicyAdultPriceOTA()));
        siteInfoVo.setAdultTaxOta(String.valueOf(sibeRoutingData.getSibePolicy().getPolicyAdultTaxOTA()));
        siteInfoVo.setChildPriceOta(String.valueOf(sibeRoutingData.getSibePolicy().getPolicyChildPriceOTA()));
        siteInfoVo.setChildTaxOta(String.valueOf(sibeRoutingData.getSibePolicy().getPolicyChildTaxOTA()));
        siteInfoVo.setGds(sibeRoutingData.getSibeRoute().getSearchPcc().getGdsCode());
        siteInfoVo.setPcc(sibeRoutingData.getSibeRoute().getSearchPcc().getPccCode());
        siteInfoVo.setSite(otaSite);
        siteInfoVo.setPolicyInfoId(String.valueOf(sibeRoutingData.getSibePolicy().getPolicyId()));
        siteInfoVo.setPolicyGlobalId(String.valueOf(sibeRoutingData.getSibePolicy().getGlobalPolicyId()));
        return siteInfoVo;
    }


}
