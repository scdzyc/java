package com.scdzyc.springcloud.service;

import com.netflix.hystrix.*;
import com.scdzyc.springcloud.pojo.ServerInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GetServerInfoCommand extends HystrixCommand<ServerInfo> {

    private String name;

    public GetServerInfoCommand(String name) {
        super(Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("GetServerInfoCommandPool"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetServerInfoCommandKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(name))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
                        .withExecutionTimeoutInMilliseconds(25000)
                )
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                        .withCoreSize(3)
                        .withQueueSizeRejectionThreshold(1)
                ));
        this.name = name;
    }

    @Override
    protected ServerInfo run() throws Exception {
        log.info("********进入线程池******");
        Thread.sleep(5000);
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setName(name);
        serverInfo.setPort("99999");
        log.info("********执行完毕******");
        return serverInfo;
    }
}
