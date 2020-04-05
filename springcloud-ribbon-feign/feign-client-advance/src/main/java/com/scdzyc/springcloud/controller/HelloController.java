package com.scdzyc.springcloud.controller;

import com.scdzyc.springcloud.pojo.ServerInfo;
import com.scdzyc.springcloud.service.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController implements IHelloService {

    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String name;

    public ServerInfo sayHello() {
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setName(name);
        serverInfo.setPort(port);
        return serverInfo;
    }

    public ServerInfo sayHello(ServerInfo serverInfo) {
        log.info(serverInfo.toString());
        serverInfo.setPort(port);
        serverInfo.setName(name);
        return serverInfo;
    }
}
