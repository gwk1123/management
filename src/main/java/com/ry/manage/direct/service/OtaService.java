package com.ry.manage.direct.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sibecommon.repository.entity.Ota;


import java.util.List;

/**
 * <p>
 * OTA平台信息 服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface OtaService extends IService<Ota> {

    /**
     * 查询OTA平台信息分页数据
     *
     * @param page      分页参数
     * @param ota 查询条件
     * @return IPage<Ota>
     */
     IPage<Ota> pageOta(Page<Ota> page,Ota ota);

    /**
     * 新增OTA平台信息
     *
     * @param ota OTA平台信息
     * @return boolean
     */
    boolean saveOta(Ota ota);

    /**
     * 删除OTA平台信息
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeOta(String id);

    /**
     * 批量删除OTA平台信息
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeOtaByIds(List<String> ids);

    /**
     * 修改OTA平台信息
     *
     * @param ota OTA平台信息
     * @return boolean
     */
    boolean updateOta(Ota ota);

    /**
     * id查询数据
     *
     * @param id id
     * @return Ota
     */
    Ota getOtaById(String id);

    /**
     * 更改OTA状态
     * @param ota
     * @return
     */
    boolean changeOtaStatus(Ota ota);
}
