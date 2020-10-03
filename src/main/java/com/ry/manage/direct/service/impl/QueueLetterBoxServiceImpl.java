package com.ry.manage.direct.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ry.manage.direct.service.QueueLetterBoxService;
import com.sibecommon.repository.entity.QueueLetterBox;
import com.sibecommon.repository.mapper.QueueLetterBoxMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import java.util.List;
import java.util.Optional;
import org.springframework.util.CollectionUtils;


/**
 * <p>
 * 信箱配置 服务实现类
 * </p>
 *
 * @author liuyc
 * @since 2020-08-16
 */
@Service
public class QueueLetterBoxServiceImpl extends ServiceImpl<QueueLetterBoxMapper, QueueLetterBox> implements QueueLetterBoxService {

    @Override
    public  IPage<QueueLetterBox> pageQueueLetterBox(Page<QueueLetterBox> page,QueueLetterBox queueLetterBox){

        page = Optional.ofNullable(page).orElse(new Page<>());
        QueryWrapper<QueueLetterBox> queryWrapper = new QueryWrapper<>();

        return  this.page(page, queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveQueueLetterBox(QueueLetterBox queueLetterBox){
        Assert.notNull(queueLetterBox, "信箱配置为空");
        return this.save(queueLetterBox);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeQueueLetterBox(String id){
        Assert.hasText(id, "主键为空");
        return this.removeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeQueueLetterBoxByIds(List<String> ids){
        Assert.isTrue(!CollectionUtils.isEmpty(ids), "主键集合为空");
        return this.removeByIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateQueueLetterBox(QueueLetterBox queueLetterBox){
        Assert.notNull(queueLetterBox, "信箱配置为空");
        return this.updateById(queueLetterBox);
    }

    @Override
    public QueueLetterBox getQueueLetterBoxById(String id){
        return  this.getById(id);
    }
}
