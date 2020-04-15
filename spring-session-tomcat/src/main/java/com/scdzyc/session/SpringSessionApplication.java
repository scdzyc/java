package com.scdzyc.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringSessionApplication /*extends SpringBootServletInitializer*/ {

    public static void main(String[] args) {
        SpringApplication.run(SpringSessionApplication.class,args);
    }


    /**
     * 使用tomcat 作为服务器容器 需要继承 SpringBootServletInitializer，实现configure方法
     * @param builder
     * @return
     */
    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringSessionApplication.class);
    }*/
}
