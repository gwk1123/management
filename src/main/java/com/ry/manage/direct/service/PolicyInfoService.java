package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ry.manage.direct.entity.PolicyInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gwk
 * @since 2020-08-22
 */
public interface PolicyInfoService extends IService<PolicyInfo> {

    /**
     * 查询分页数据
     *
     * @param page      分页参数
     * @param policyInfo 查询条件
     * @return IPage<PolicyInfo>
     */
     IPage<PolicyInfo> pagePolicyInfo(Page<PolicyInfo> page,PolicyInfo policyInfo);

    /**
     * 新增
     *
     * @param policyInfo
     * @return boolean
     */
    boolean savePolicyInfo(PolicyInfo policyInfo);

    /**
     * 删除
     *
     * @param id 主键
     * @return boolean
     */
    boolean removePolicyInfo(String id);

    /**
     * 批量删除
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removePolicyInfoByIds(List<String> ids);

    /**
     * 修改
     *
     * @param policyInfo
     * @return boolean
     */
    boolean updatePolicyInfo(PolicyInfo policyInfo);

    /**
     * id查询数据
     *
     * @param id id
     * @return PolicyInfo
     */
    PolicyInfo getPolicyInfoById(String id);

    /**
     * 更改G状态
     * @param policyInfo
     * @return
     */
    boolean changePolicyInfoStatus(PolicyInfo policyInfo);
}
