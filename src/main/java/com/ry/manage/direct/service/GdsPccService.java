package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.entity.Gds;
import com.ry.manage.direct.entity.GdsPcc;

import java.util.List;

/**
 * <p>
 * GDS PCC配置(ml_gds_pcc) 服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface GdsPccService extends IService<GdsPcc> {

    /**
     * 查询GDS PCC配置(ml_gds_pcc)分页数据
     *
     * @param page      分页参数
     * @param gdsPcc 查询条件
     * @return IPage<GdsPcc>
     */
     IPage<GdsPcc> pageGdsPcc(Page<GdsPcc> page,GdsPcc gdsPcc);

    /**
     * 新增GDS PCC配置(ml_gds_pcc)
     *
     * @param gdsPcc GDS PCC配置(ml_gds_pcc)
     * @return boolean
     */
    boolean saveGdsPcc(GdsPcc gdsPcc);

    /**
     * 删除GDS PCC配置(ml_gds_pcc)
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeGdsPcc(String id);

    /**
     * 批量删除GDS PCC配置(ml_gds_pcc)
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeGdsPccByIds(List<String> ids);

    /**
     * 修改GDS PCC配置(ml_gds_pcc)
     *
     * @param gdsPcc GDS PCC配置(ml_gds_pcc)
     * @return boolean
     */
    boolean updateGdsPcc(GdsPcc gdsPcc);

    /**
     * id查询数据
     *
     * @param id id
     * @return GdsPcc
     */
    GdsPcc getGdsPccById(String id);

    /**
     * 更改GDS状态
     * @param gdsPcc
     * @return
     */
    boolean changeGdsStatus(GdsPcc gdsPcc);
}
