package com.scdzyc.eureka.controller;

import com.scdzyc.eureka.pojo.ServerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/")
public class HelloController {

    @Value("${server.port}")
    private String port;
    @Value("${spring.application.name}")
    private String name;

    @GetMapping("/sayhello")
    public String sayHello() {
        return "my port is "+ port;
    }

    @PostMapping("/sayhello")
    public ServerInfo sayServer(@RequestBody ServerInfo serverInfo) {
        log.info(serverInfo.toString());
        serverInfo.setName(name);
        serverInfo.setPort(port);
        return null;
    }
}
