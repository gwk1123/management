package com.ry.manage.sys.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ry.manage.common.CommonResult;
import com.ry.manage.common.constant.Constants;
import com.ry.manage.common.utils.ServletUtils;
import com.ry.manage.sys.auth.bo.LoginBody;
import com.ry.manage.sys.entity.SysMenu;
import com.ry.manage.sys.entity.SysUser;
import com.ry.manage.sys.service.SysMenuService;
import com.ry.manage.sys.service.SysRoleService;
import com.ry.manage.sys.service.impl.SysLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@RestController
public class SysLoginController
{
    private final SysLoginServiceImpl sysLoginServiceImpl;

    private final SysMenuService sysMenuService;

    private final SysRoleService sysRoleService;

    public SysLoginController(SysLoginServiceImpl sysLoginServiceImpl, SysMenuService sysMenuService, SysRoleService sysRoleService) {
        this.sysLoginServiceImpl = sysLoginServiceImpl;
        this.sysMenuService = sysMenuService;
        this.sysRoleService = sysRoleService;
    }


    /**
     * 登录方法
     * 
     * @param loginBody 登陆信息
     * @return 结果
     */
    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginBody loginBody)
    {
        CommonResult commonResult = CommonResult.success();
        // 生成令牌
        String token = sysLoginServiceImpl.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        commonResult.put(Constants.TOKEN, token);
        return commonResult;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("/getInfo")
    public CommonResult getInfo()
    {
        SysUser user = sysLoginServiceImpl.getLoginUser(ServletUtils.getRequest());
        // 角色集合
        Set<String> roles = sysLoginServiceImpl.getRolePermission(user);
        // 权限集合
        Set<String> permissions = sysLoginServiceImpl.getPermissionBySysUser(user);
        CommonResult commonResult = CommonResult.success();
        commonResult.put("user", user);
        commonResult.put("roles", roles);
        commonResult.put("permissions", permissions);
        return commonResult;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public CommonResult getRouters(){
        // 用户信息
        SysUser user = sysLoginServiceImpl.getLoginUser(ServletUtils.getRequest());
        List<SysMenu> menus = sysMenuService.selectMenuTreeByUserId(user.getUserId());
        return CommonResult.success(sysMenuService.buildMenus(menus));
    }
}
