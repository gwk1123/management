package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import comm.repository.entity.QueueLetterBox;

import java.util.List;

/**
 * <p>
 * 信箱配置 服务类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
public interface QueueLetterBoxService extends IService<QueueLetterBox> {

    /**
     * 查询信箱配置分页数据
     *
     * @param page      分页参数
     * @param queueLetterBox 查询条件
     * @return IPage<QueueLetterBox>
     */
     IPage<QueueLetterBox> pageQueueLetterBox(Page<QueueLetterBox> page,QueueLetterBox queueLetterBox);

    /**
     * 新增信箱配置
     *
     * @param queueLetterBox 信箱配置
     * @return boolean
     */
    boolean saveQueueLetterBox(QueueLetterBox queueLetterBox);

    /**
     * 删除信箱配置
     *
     * @param id 主键
     * @return boolean
     */
    boolean removeQueueLetterBox(String id);

    /**
     * 批量删除信箱配置
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removeQueueLetterBoxByIds(List<String> ids);

    /**
     * 修改信箱配置
     *
     * @param queueLetterBox 信箱配置
     * @return boolean
     */
    boolean updateQueueLetterBox(QueueLetterBox queueLetterBox);

    /**
     * id查询数据
     *
     * @param id id
     * @return QueueLetterBox
     */
    QueueLetterBox getQueueLetterBoxById(String id);
}
