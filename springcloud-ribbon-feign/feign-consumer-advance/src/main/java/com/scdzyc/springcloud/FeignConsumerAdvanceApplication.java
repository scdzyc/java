package com.scdzyc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//这个地方要注意IService所在包路径,默认是扫当前包com.icodingedu.springcloud
//如果接口不在同一个包下就需要把包路径扫进来
//@EnableFeignClients(basePackages = "com.scdzyc.*")
@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
public class FeignConsumerAdvanceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignConsumerAdvanceApplication.class, args);
    }
}
