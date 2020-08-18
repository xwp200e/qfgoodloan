package com.project.config;

import com.project.shiro.MyRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager")DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> map = new HashMap<>();

        map.put("/login", "anon");
        map.put("/main", "roles[manager]");
        //map.put("/");
        //map.put("/main")

        shiroFilterFactoryBean.setLoginUrl("/login");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    /**
     * 注解WebSecurityManager对象
     * @param myrealm
     * @return
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myRealm") MyRealm myrealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        defaultWebSecurityManager.setRealm(myrealm);

        return defaultWebSecurityManager;
    }

    /**
     * 开启Shiro的注解
     *  需要借助SpringAOP扫描shiro注解的类，来进行安全校验
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();

        advisorAutoProxyCreator.setProxyTargetClass(true);

        return advisorAutoProxyCreator;
    }

    /**
     * 开启AOP的注解支持
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();

        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);

        return authorizationAttributeSourceAdvisor;
    }
}
