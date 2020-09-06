package com.ry.manage.direct.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import comm.repository.entity.PolicyInfoBaggage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gwk
 * @since 2020-08-23
 */
public interface PolicyInfoBaggageService extends IService<PolicyInfoBaggage> {

    /**
     * 查询分页数据
     *
     * @param page      分页参数
     * @param policyInfoBaggage 查询条件
     * @return IPage<PolicyInfoBaggage>
     */
     IPage<PolicyInfoBaggage> pagePolicyInfoBaggage(Page<PolicyInfoBaggage> page,PolicyInfoBaggage policyInfoBaggage);

    /**
     * 新增
     *
     * @param policyInfoBaggage
     * @return boolean
     */
    boolean savePolicyInfoBaggage(PolicyInfoBaggage policyInfoBaggage);

    /**
     * 删除
     *
     * @param id 主键
     * @return boolean
     */
    boolean removePolicyInfoBaggage(String id);

    /**
     * 批量删除
     *
     * @param ids 主键集合
     * @return boolean
     */
    boolean removePolicyInfoBaggageByIds(List<String> ids);

    /**
     * 修改
     *
     * @param policyInfoBaggage
     * @return boolean
     */
    boolean updatePolicyInfoBaggage(PolicyInfoBaggage policyInfoBaggage);

    /**
     * id查询数据
     *
     * @param id id
     * @return PolicyInfoBaggage
     */
    PolicyInfoBaggage getPolicyInfoBaggageById(String id);
}
