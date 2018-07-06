package com.example.securityjpa.config;

import com.example.securityjpa.handler.CustomAccessDeniedHandler;
import com.example.securityjpa.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author: BaoZhou
 * @date : 2018/7/4 17:15
 */

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    UserDetailsService service()
    {
        return new UserService();
    }

    public SecurityConfig() {
        super(true);
    }

    /**
     * 注入自定义认证类
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /*匹配所有路径的*/
        http
                .csrf()
                .disable()
                .authorizeRequests().antMatchers("/").permitAll()
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
                /*
                定制自己的登录界面
                默认username字段提交用户名，可以通过usernameParameter自定义
                默认password字段提交密码，可以用过passwordParameter自定义
                定制了登录页面后
                登录页面地址的POST请求就是登录请求，可以用过loginProcessingUrl自定义
                */
//                .loginPage("/userlogin")
                .and()
                /*开启注销功能,访问/logout并清空session*/
                .logout()
                /*注销成功去首页*/
                .logoutSuccessUrl("/")
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