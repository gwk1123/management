package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import comm.repository.entity.OtaSite;

import java.util.List;

/**
 * <p>
 * OTA平台站点表 服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface OtaSiteService extends IService<OtaSite> {

    /**
     * 查询OTA平台站点表分页数据
     *
     * @param page      分页参数
     * @param otaSite 查询条件
     * @return IPage<OtaSite>
     */
     IPage<OtaSite> pageOtaSite(Page<OtaSite> page,OtaSite otaSite);

    /**
     * 新增OTA平台站点表
     *
     * @param otaSite OTA平台站点表
     * @return boolean
     */
    boolean saveOtaSite(OtaSite otaSite);

    /**
     * 删除OTA平台站点表
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeOtaSite(String id);

    /**
     * 批量删除OTA平台站点表
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeOtaSiteByIds(List<String> ids);

    /**
     * 修改OTA平台站点表
     *
     * @param otaSite OTA平台站点表
     * @return boolean
     */
    boolean updateOtaSite(OtaSite otaSite);

    /**
     * id查询数据
     *
     * @param id id
     * @return OtaSite
     */
    OtaSite getOtaSiteById(String id);


    /**
     * 更改otaSite状态
     * @param otaSite
     * @return
     */
    boolean changeOtaSiteStatus(OtaSite otaSite);
}
