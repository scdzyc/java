package com.scdzyc.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.scdzyc.springcloud.pojo.ServerInfo;
import com.scdzyc.springcloud.service.GetServerInfoCommand;
import com.scdzyc.springcloud.service.MyService;
import com.scdzyc.springcloud.service.RequestCacheService;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HystrixController {

    @Resource
    private MyService myService;

    @Autowired
    private RequestCacheService requestCacheService;

    @GetMapping("/fallback")
    public String fallback() {
        return myService.error();
    }

    @GetMapping("/timeout")
    public String timeout(int timeout) {
        return myService.retry(timeout);
    }

    @GetMapping("/cache")
    public ServerInfo requestCache(String name) {
        //缓存存放在hystrix的上下文中,需要初始化上下文,上下文打开后执行完还要关闭context.close();
        //使用try-catch-finally里去context.close();掉
        //或者使用lombok的@Cleanup注解,默认调用close方法,如果默认不是close方法而是其他方法关闭
        //可以这样来设置@Cleanup("shutup")
        @Cleanup
        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        //我们在这里调用两次看看执行过程
        ServerInfo serverInfo = requestCacheService.requestCache(name);
        serverInfo = requestCacheService.requestCache(name);
        return serverInfo;
    }

    /**
     * 多级降级的实现方式 二
     * @param second
     * @return
     */
    @GetMapping("/timeout2")
    @HystrixCommand(
            fallbackMethod = "timeoutfallback",
            //可以忽略不进行降级的异常
            ignoreExceptions = {IllegalArgumentException.class},
            //这个commandProperties是一个数组,所以可以配置多个HystrixProperty
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "6000")
            }
    )
    public String timeout2(int second){
        return myService.retry(second);
    }
    //这个是降级的实现方法
    public String timeoutfallback(int second){
        return "timeout success "+second;
    }

    @GetMapping("/command")
    public String serverInfoCommand() {
        com.netflix.hystrix.HystrixCommand<ServerInfo> hystrixCommand = new GetServerInfoCommand("cy");
        ServerInfo serverInfo = hystrixCommand.execute();
        return "success " + serverInfo.getName() + " --- " + serverInfo.getPort();
    }
}
