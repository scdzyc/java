package com.scdzyc.springcloud.controller;

import com.scdzyc.springcloud.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class FeignControoler {

    @Autowired
    IService iService;

    @GetMapping("hello")
    public String hello() {

        return iService.hello();
    }
}
