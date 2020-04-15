package com.scdzyc.session.config;

import com.scdzyc.session.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    /*@Bean
    public FilterRegistrationBean myFilter(MyFilter filter) {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean();
        filterFilterRegistrationBean.setFilter(filter);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        filterFilterRegistrationBean.setOrder(99);
        return filterFilterRegistrationBean;
    }*/
}
