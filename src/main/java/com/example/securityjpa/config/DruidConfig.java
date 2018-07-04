package com.example.securityjpa.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author: BaoZhou
 * @date : 2018/7/4 17:15
 */

public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
        return DruidDataSourceBuilder.create().build();
    }
    @Bean
    public ServletRegistrationBean startViewServlet()
    {
        //参数可以在StatViewServlet和ResourceServlet中查看
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        Map<String,String> params = new HashMap<>();
        //用户名
        params.put("loginUsername","admin");
        //密码
        params.put("loginPassword","123456");
        //IP白名单 (不填写代表允许所有IP)
        params.put("allow","");
        //IP黑名单 (存在共同时，deny优先于allow)
        //initParameters.put("deny", "192.168.20.38");
        bean.setInitParameters(params);
        return bean;
    }

    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> initParams = new HashMap<>();
        //排除拦截
        initParams.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return  bean;
    }
}