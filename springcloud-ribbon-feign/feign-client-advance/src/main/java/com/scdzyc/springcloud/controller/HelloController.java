package com.scdzyc.springcloud.controller;

import com.scdzyc.springcloud.pojo.ServerInfo;
import com.scdzyc.springcloud.service.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController implements IHelloService {

    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String name;

    @Override
    public ServerInfo sayHello() {
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setName(name);
        serverInfo.setPort(port);
        return serverInfo;
    }

    @Override
    public ServerInfo sayHello(ServerInfo serverInfo) {
        log.info(serverInfo.toString());
        serverInfo.setPort(port);
        serverInfo.setName(name);
        return serverInfo;
    }

    @Override
    public String error() {
        throw new RuntimeException("is bad way");
    }

    @Override
    public String retry(@RequestParam(name = "timeout")int timeout) {
        try{
            while (timeout-- > 0){
                Thread.sleep(1000);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        log.info("retry is :" + port);
        return port;
    }

    @Override
    public String valid() {
        int i = 1 / 0;
        return "volid test success";
    }
}
