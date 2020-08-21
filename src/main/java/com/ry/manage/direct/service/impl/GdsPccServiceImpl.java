package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.common.constant.DirectConstants;
import com.ry.manage.direct.entity.Gds;
import com.ry.manage.direct.entity.GdsPcc;
import com.ry.manage.direct.mapper.GdsPccMapper;
import com.ry.manage.direct.service.GdsPccService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 * GDS PCC配置(ml_gds_pcc) 服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class GdsPccServiceImpl extends ServiceImpl<GdsPccMapper, GdsPcc> implements GdsPccService {

    @Override
    public  IPage<GdsPcc> pageGdsPcc(Page<GdsPcc> page,GdsPcc gdsPcc){
        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<GdsPcc> queryWrapper =this.buildQueryWrapper(gdsPcc);
        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveGdsPcc(GdsPcc gdsPcc){
        Assert.notNull(gdsPcc, "GDS PCC配置为空");
        return this.save(gdsPcc);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeGdsPcc(String id){
        Assert.hasText(id, "主键为空");
        GdsPcc gdsPcc = this.getById(id);
        gdsPcc.setStatus(DirectConstants.DELETE);
        return this.updateById(gdsPcc);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeGdsPccByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        //TODO
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateGdsPcc(GdsPcc gdsPcc){
        Assert.notNull(gdsPcc, "GDS PCC配置为空");
        return this.updateById(gdsPcc);
    }

    @Override
    public GdsPcc getGdsPccById(String id){
        return  this.getById(id);
    }

    @Override
    public boolean changeGdsStatus(GdsPcc gdsPcc){
        GdsPcc status = this.getById(gdsPcc.getId());
        status.setStatus(gdsPcc.getStatus());
        return this.updateById(status);
    }

    public QueryWrapper<GdsPcc> buildQueryWrapper(GdsPcc gdsPcc){
        QueryWrapper<GdsPcc> gdsQueryWrapper=new QueryWrapper<>();
        if(gdsPcc != null){
            gdsQueryWrapper.lambda().eq(StringUtils.isNotBlank(gdsPcc.getGdsCode()),GdsPcc::getGdsCode,gdsPcc.getGdsCode())
                   .eq(StringUtils.isNotBlank(gdsPcc.getPccCode()),GdsPcc::getPccCode,gdsPcc.getPccCode());
        }
        if(gdsPcc != null && StringUtils.isNotBlank(gdsPcc.getStatus())){
            gdsQueryWrapper.lambda().eq(StringUtils.isNotBlank(gdsPcc.getStatus()),GdsPcc::getStatus,gdsPcc.getStatus());
        }else {
            gdsQueryWrapper.and(wrapper -> wrapper.lambda().eq(GdsPcc::getStatus, DirectConstants.NORMAL).or().eq(GdsPcc::getStatus,DirectConstants.FAILURE));
        }
        gdsQueryWrapper.lambda().orderByAsc(GdsPcc::getCreateTime);
        return gdsQueryWrapper;
    }

}
