package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.OtaSiteService;
import comm.repository.entity.OtaSite;
import comm.repository.mapper.OtaSiteMapper;
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
 * OTA平台站点表 服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class OtaSiteServiceImpl extends ServiceImpl<OtaSiteMapper, OtaSite> implements OtaSiteService {

    @Override
    public  IPage<OtaSite> pageOtaSite(Page<OtaSite> page, OtaSite otaSite){
        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<OtaSite> queryWrapper = this.buildQueryWrapper(otaSite);
        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOtaSite(OtaSite otaSite){
        Assert.notNull(otaSite, "OTA平台站点表为空");
        return this.save(otaSite);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOtaSite(String id){
        Assert.hasText(id, "主键为空");
        OtaSite otaSite = this.getById(id);
        otaSite.setStatus(DirectConstants.DELETE);
        return this.updateById(otaSite);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOtaSiteByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateOtaSite(OtaSite otaSite){
        Assert.notNull(otaSite, "OTA平台站点表为空");
        return this.updateById(otaSite);
    }

    @Override
    public OtaSite getOtaSiteById(String id){
        return  this.getById(id);
    }

    @Override
    public boolean changeOtaSiteStatus(OtaSite otaSite){
        OtaSite status = this.getById(otaSite.getId());
        status.setStatus(otaSite.getStatus());
        return this.updateById(status);
    }

    public QueryWrapper<OtaSite> buildQueryWrapper(OtaSite otaSite){
        QueryWrapper<OtaSite> gdsQueryWrapper=new QueryWrapper<>();
        if(otaSite != null){
            gdsQueryWrapper.lambda().eq(StringUtils.isNotBlank(otaSite.getOtaCode()),OtaSite::getOtaCode,otaSite.getOtaCode())
                    .eq(StringUtils.isNotBlank(otaSite.getOtaSiteCode()),OtaSite::getOtaSiteCode,otaSite.getOtaSiteCode());
        }
        if(otaSite != null && StringUtils.isNotBlank(otaSite.getStatus())){
            gdsQueryWrapper.lambda().eq(StringUtils.isNotBlank(otaSite.getStatus()),OtaSite::getStatus,otaSite.getStatus());
        }else {
            gdsQueryWrapper.and(wrapper -> wrapper.lambda().eq(OtaSite::getStatus, DirectConstants.NORMAL).or().eq(OtaSite::getStatus,DirectConstants.FAILURE));
        }
        gdsQueryWrapper.lambda().orderByAsc(OtaSite::getCreateTime);
        return gdsQueryWrapper;
    }

}
