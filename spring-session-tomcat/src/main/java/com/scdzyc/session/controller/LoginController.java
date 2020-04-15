package com.scdzyc.session.controller;

import com.scdzyc.session.config.SessionManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class LoginController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/login")
    public String login(@RequestParam String name, @RequestParam String password) {
        log.info("登录信息为：" + name + " - " + password);
        return port;
    }
}
