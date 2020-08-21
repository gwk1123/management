package com.ry.manage.sys.auth.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ry.manage.security.component.DynamicSecurityService;
import com.ry.manage.security.config.SecurityConfig;
import com.ry.manage.sys.entity.SysMenu;
import com.ry.manage.sys.entity.SysUser;
import com.ry.manage.sys.service.SysMenuService;
import com.ry.manage.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.xml.namespace.QName;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mall-security模块相关配置
 * Created by macro on 2019/11/9.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MallSecurityConfig extends SecurityConfig {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuService sysMenuService;

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> sysUserService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                QueryWrapper<SysMenu> sysMenuQueryWrapper=new QueryWrapper<>();
                sysMenuQueryWrapper.lambda().isNotNull(SysMenu::getPerms);
                List<SysMenu> sysMenus = sysMenuService.list(sysMenuQueryWrapper);
                for (SysMenu sysMenu : sysMenus) {
                    map.put(sysMenu.getPerms(), new org.springframework.security.access.SecurityConfig(String.valueOf(sysMenu.getMenuId())));
                }
                return map;
            }
        };
    }
}
