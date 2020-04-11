package com.scdzyc.springcloud.service;

import com.scdzyc.springcloud.fallback.HelloFallback;
import com.scdzyc.springcloud.pojo.ServerInfo;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 这是一个整体的容错方案，接口里的所有方法都进行了容错管理，在fallback指定的类中实现
 */
@FeignClient(name = "feign-client", fallback = HelloFallback.class)
public interface MyService extends IHelloService {

}
