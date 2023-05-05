package org.zucc.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来整合shiro框架相关的配置类
 */
@Configuration
@SuppressWarnings("all")
public class ShiroConfig {
    /**
     * Shiro官方支持多种模板方言，却没有为Thymeleaf提供支持，幸好有第三方提供的thymeleaf-extras-shiro
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 创建shiroFilter
     * 负责拦截所有请求
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //配置系统受限资源
        //配置系统公共系统
        Map<String, String> map = new HashMap<String, String>();
        //anon 设置为公共资源
        map.put("/operate/main", "anon");
        //anon 设置为公共资源
        map.put("/user/**", "anon");
        map.put("/system/list", "anon");
        //anon 设置为公共资源
        map.put("/asserts/**", "anon");
        //authc 请求这个资源需要认证和授权
        map.put("/**", "authc");
        //默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建安全管理器
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getRealm") Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置
        defaultWebSecurityManager.setRealm(realm);
        ThreadContext.bind(defaultWebSecurityManager);
        return defaultWebSecurityManager;
    }

    /**
     * 创建自定义realm
     */
    @Bean
    public Realm getRealm() {
        CustomerRealm customerRealm = new CustomerRealm();
        ////修改凭证校验匹配器
        //HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        ////设置加密算法为md5
        //credentialsMatcher.setHashAlgorithmName("MD5");
        ////设置散列次数
        //credentialsMatcher.setHashIterations(1024);
        //customerRealm.setCredentialsMatcher(credentialsMatcher);
        return customerRealm;
    }
}