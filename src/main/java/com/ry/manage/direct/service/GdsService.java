package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sibecommon.repository.entity.Gds;

import java.util.List;

/**
 * <p>
 * GDS信息 服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface GdsService extends IService<Gds> {

    /**
     * 查询GDS信息分页数据
     *
     * @param page      分页参数
     * @param gds 查询条件
     * @return IPage<Gds>
     */
     IPage<Gds> pageGds(Page<Gds> page,Gds gds);

    /**
     * 新增GDS信息
     *
     * @param gds GDS信息
     * @return boolean
     */
    boolean saveGds(Gds gds);

    /**
     * 删除GDS信息
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeGds(String id);

    /**
     * 批量删除GDS信息
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeGdsByIds(List<String> ids);

    /**
     * 修改GDS信息
     *
     * @param gds GDS信息
     * @return boolean
     */
    boolean updateGds(Gds gds);

    /**
     * id查询数据
     *
     * @param id id
     * @return Gds
     */
    Gds getGdsById(String id);

    /**
     * 更改GDS状态
     * @param gds
     * @return
     */
    boolean changeGdsStatus(Gds gds);
}
