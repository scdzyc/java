package com.scdzyc.springcloud.controller;

import com.scdzyc.springcloud.pojo.ServerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello() {
        return restTemplate.getForObject("http://eureka-client/sayhello", String.class);
    }

    @PostMapping("hello")
    public ServerInfo hello(@RequestBody ServerInfo serverInfo) {
        log.info(serverInfo.toString());
        return restTemplate.postForObject("http://eureka-client/sayhello", serverInfo, ServerInfo.class);
    }
}
