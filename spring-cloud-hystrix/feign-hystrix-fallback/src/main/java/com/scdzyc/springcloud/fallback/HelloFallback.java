package com.scdzyc.springcloud.fallback;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.scdzyc.springcloud.pojo.ServerInfo;
import com.scdzyc.springcloud.service.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 这就是容错类
 */
@Slf4j
@Component(value = "helloFallback")
public class HelloFallback implements MyService {

    @Override
    public ServerInfo sayHello() {
        return null;
    }

    @Override
    public ServerInfo sayHello(ServerInfo serverInfo) {
        return null;
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallback2")
    public String error() {
        log.info("----------- sorry -------");
        return "I am sorry from com.scdzyc.springcloud.fallback.HelloFallback";
    }

    @Override
    public String retry(int timeout) {
        return "you are late!";
    }

    /**
     * 和 {@link #error()} 多级降级 这是实现方法之一
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback3")
    public String fallback2() {
        log.info("********* sorry again ********");
        throw new RuntimeException("first fallback again");
    }
    public String fallback3(){
        log.info("********* sorry again 2 ********");
        return "success sorry again 2!";
    }
}
