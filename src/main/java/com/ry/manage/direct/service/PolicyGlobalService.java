package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ry.manage.direct.entity.PolicyGlobal;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gwk
 * @since 2020-08-23
 */
public interface PolicyGlobalService extends IService<PolicyGlobal> {

    /**
     * 查询分页数据
     *
     * @param page      分页参数
     * @param policyGlobal 查询条件
     * @return IPage<PolicyGlobal>
     */
     IPage<PolicyGlobal> pagePolicyGlobal(Page<PolicyGlobal> page,PolicyGlobal policyGlobal);

    /**
     * 新增
     *
     * @param policyGlobal
     * @return boolean
     */
    boolean savePolicyGlobal(PolicyGlobal policyGlobal);

    /**
     * 删除
     *
     * @param id 主键
     * @return boolean
     */
    boolean removePolicyGlobal(String id);

    /**
     * 批量删除
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removePolicyGlobalByIds(List<String> ids);

    /**
     * 修改
     *
     * @param policyGlobal
     * @return boolean
     */
    boolean updatePolicyGlobal(PolicyGlobal policyGlobal);

    /**
     * id查询数据
     *
     * @param id id
     * @return PolicyGlobal
     */
    PolicyGlobal getPolicyGlobalById(String id);

    /**
     * 更改状态
     * @param policyGlobal
     * @return
     */
    boolean changePolicyGlobalStatus(PolicyGlobal policyGlobal);
}
