package com.scdzyc.eureka.controller;

import com.scdzyc.eureka.pojo.ServerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequestMapping("/")
public class HelloControoler {

    // 需要自己创建，并注入spring容器
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient client;
    //org.springframework.cloud.loadbalancer.config.BlockingLoadBalancerClientAutoConfiguration

    @GetMapping("/hello")
    public String sayHello() {
        // 获取指定服务
        String targetUrl = getUrl("eureka-client", "sayhello");
        log.info("url is: {}",targetUrl);
        return restTemplate.getForObject(targetUrl,String.class);
    }

    @PostMapping("hello")
    public ServerInfo serverInfo() {
        String targetUrl = getUrl("eureka-client", "sayhello");
        log.info("url is: {}",targetUrl);
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setName("consumer");
        serverInfo.setPort("9999");
        return restTemplate.postForObject(targetUrl,serverInfo,ServerInfo.class);
    }

    private String getUrl(String serviceId, String path) {
        // 获取指定服务
        ServiceInstance service = client.choose("eureka-client");
        if(null == service) {
            throw new RuntimeException("No available instance");
        }
        return String.format("http://%s:%s/%s",service.getHost(),service.getPort(),path);
    }
}
