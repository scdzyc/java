package com.scdzyc.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.scdzyc.springcloud.pojo.ServerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class RequestCacheService {

    @Resource
    private MyService myService;

    @CacheResult
    @HystrixCommand(commandKey = "cacheKey")
    public ServerInfo requestCache(@CacheKey String name) {
        log.info("request cache name is " + name);
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setName(name);
        serverInfo.setPort("2020");
        serverInfo = myService.sayHello(serverInfo);
        log.info("after request cache name is "+ name);
        return serverInfo;
    }
}
