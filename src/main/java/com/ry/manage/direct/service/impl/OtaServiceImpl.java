package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.OtaService;
import comm.repository.entity.Ota;
import comm.repository.mapper.OtaMapper;
import comm.utils.constant.DirectConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 * OTA平台信息 服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class OtaServiceImpl extends ServiceImpl<OtaMapper, Ota> implements OtaService {

    @Override
    public  IPage<Ota> pageOta(Page<Ota> page,Ota ota){
        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<Ota> queryWrapper = this.buildQueryWrapper(ota);
        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOta(Ota ota){
        Assert.notNull(ota, "OTA平台信息为空");
        return this.save(ota);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOta(String id){
        Assert.hasText(id, "主键为空");
        Ota ota = this.getById(id);
        ota.setStatus(DirectConstants.DELETE);
        return this.updateById(ota);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOtaByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOta(Ota ota){
        Assert.notNull(ota, "OTA平台信息为空");
        return this.updateById(ota);
    }

    @Override
    public Ota getOtaById(String id){
        return  this.getById(id);
    }

    @Override
    public boolean changeOtaStatus(Ota ota){
        Ota status = this.getById(ota.getId());
        status.setStatus(ota.getStatus());
        return this.updateById(status);
    }


    public QueryWrapper<Ota> buildQueryWrapper(Ota ota){
        QueryWrapper<Ota> gdsQueryWrapper=new QueryWrapper<>();
        if(ota != null){
            gdsQueryWrapper.lambda().eq(StringUtils.isNotBlank(ota.getOtaCode()),Ota::getOtaCode,ota.getOtaCode())
                    .like(StringUtils.isNotBlank(ota.getOtaCname()),Ota::getOtaCname,ota.getOtaCname());
        }
        if(ota != null && StringUtils.isNotBlank(ota.getStatus())){
            gdsQueryWrapper.lambda().eq(StringUtils.isNotBlank(ota.getStatus()),Ota::getStatus,ota.getStatus());
        }else {
            gdsQueryWrapper.and(wrapper -> wrapper.lambda().eq(Ota::getStatus, DirectConstants.NORMAL).or().eq(Ota::getStatus,DirectConstants.FAILURE));
        }
        gdsQueryWrapper.lambda().orderByAsc(Ota::getCreateTime);
        return gdsQueryWrapper;
    }
}
