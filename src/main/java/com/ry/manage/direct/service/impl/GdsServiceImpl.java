package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.GdsService;
import comm.repository.entity.Gds;
import comm.repository.mapper.GdsMapper;
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
 * GDS信息 服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class GdsServiceImpl extends ServiceImpl<GdsMapper, Gds> implements GdsService {

    @Override
    public  IPage<Gds> pageGds(Page<Gds> page,Gds gds){
        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<Gds> queryWrapper = buildQueryWrapper(gds);
        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveGds(Gds gds){
        Assert.notNull(gds, "GDS信息为空");
        return this.save(gds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeGds(String id){
        Assert.hasText(id, "主键为空");
        Gds gds = this.getById(id);
        gds.setStatus(DirectConstants.DELETE);
        return this.updateById(gds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeGdsByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateGds(Gds gds){
        Assert.notNull(gds, "GDS信息为空");
        return this.updateById(gds);
    }

    @Override
    public Gds getGdsById(String id){
        return  this.getById(id);
    }

    @Override
    public boolean changeGdsStatus(Gds gds){
        Gds gdsStatus = this.getById(gds.getId());
        gdsStatus.setStatus(gds.getStatus());
        return this.updateById(gdsStatus);
    }

    public QueryWrapper<Gds> buildQueryWrapper(Gds gds){
        QueryWrapper<Gds> gdsQueryWrapper=new QueryWrapper<>();
        if(gds != null){
            gdsQueryWrapper.lambda().eq(StringUtils.isNotBlank(gds.getGdsCode()),Gds::getGdsCode,gds.getGdsCode())
                    .like(StringUtils.isNotBlank(gds.getGdsCname()),Gds::getGdsCname,gds.getGdsCname())
                    .like(StringUtils.isNotBlank(gds.getGdsEname()),Gds::getGdsEname,gds.getGdsEname());
        }
        if(gds != null && StringUtils.isNotBlank(gds.getStatus())){
            gdsQueryWrapper.lambda().eq(StringUtils.isNotBlank(gds.getStatus()),Gds::getStatus,gds.getStatus());
        }else {
            gdsQueryWrapper.and(wrapper -> wrapper.lambda().eq(Gds::getStatus,DirectConstants.NORMAL).or().eq(Gds::getStatus,DirectConstants.FAILURE));
        }
        gdsQueryWrapper.lambda().orderByAsc(Gds::getCreateTime);
        return gdsQueryWrapper;
    }

}
