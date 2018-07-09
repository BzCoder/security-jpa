package com.example.securityjpa.config;

import com.example.securityjpa.handler.CustomAccessDeniedHandler;
import com.example.securityjpa.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

/**
 * @author: BaoZhou
 * @date : 2018/7/4 17:15
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService getServiceDetail() {
        return new UserService();
    }

    /**
     * 去除身份前缀，SpringSecurity会默认在数据库中取得的身份数据前加上ROLE_前缀
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.expressionHandler(new DefaultWebSecurityExpressionHandler() {
            @Override
            protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
                WebSecurityExpressionRoot root = (WebSecurityExpressionRoot) super.createSecurityExpressionRoot(authentication, fi);
                //remove the prefix ROLE_
                root.setDefaultRolePrefix("");
                return root;
            }
        });
    }

    /**
     * 添加机密方法，这里选择的是BCryptPasswordEncoder
     * 数据库中也需要存储BCryptPasswordEncoder加密后的密码
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入自定义认证类
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getServiceDetail());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*匹配所有路径的*/
        http
                /*关闭跨站支持,不关闭的话，无法登陆Druid监控页面*/
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/druid/**").permitAll()
                /*level1路径下需要VIP1身份才能访问*/
                .antMatchers("/level1/**").hasRole("VIP1")
                /*level1路径下需要VIP2身份才能访问*/
                .antMatchers("/level2/**").hasRole("VIP2")
                /*level1路径下需要VIP3身份才能访问*/
                .antMatchers("/level3/**").hasRole("VIP3")
                .and()
                /*
                1.formLogin系统会自动配置/login页面用于登录
                2.假如登录失败会重定向到login/error/
                 */
                .formLogin()
                .loginPage("/userlogin")
                /*
               定制自己的登录界面
               默认username字段提交用户名，可以通过usernameParameter自定义
               默认password字段提交密码，可以用过passwordParameter自定义
               定制了登录页面后
               登录页面地址的POST请求就是登录请求，可以用过loginProcessingUrl自定义
               */
                .and()
                /*开启注销功能,访问/logout并清空session*/
                .logout()
                /*注销成功去首页*/
                .and()
                .exceptionHandling()
                /*设置403错误跳转页面*/
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .and()
                /*开启记住我功能，登录会添加Cookie,点击注销会删除Cookie*/
                .rememberMe()
                /*设置记住我参数*/
                .rememberMeParameter("remember");
    }
}