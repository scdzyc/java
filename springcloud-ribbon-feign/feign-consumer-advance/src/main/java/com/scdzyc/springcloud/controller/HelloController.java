package com.scdzyc.springcloud.controller;

import com.scdzyc.springcloud.pojo.ServerInfo;
import com.scdzyc.springcloud.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private IHelloService iHelloService;

    @GetMapping("/hello")
    public ServerInfo hello() {
        return iHelloService.sayHello();
    }

    @PostMapping("hello")
    public ServerInfo hello(@RequestBody ServerInfo serverInfo) {
        return iHelloService.sayHello(serverInfo);
    }
}
