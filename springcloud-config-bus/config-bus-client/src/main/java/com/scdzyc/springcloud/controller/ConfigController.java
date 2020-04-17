package com.scdzyc.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ConfigController class
 *
 * @author yanchuan
 * @module com.scdzyc.springcloud.controller
 * @blame yanchuan
 * @since 20/04/13 16:19
 */
@RestController
@RefreshScope
public class ConfigController {

    // 直接从外部配置加载
    @Value("${name}")
    private String name;

    @Value("${localWords}")
    private String words;

    @GetMapping("/name")
    public String getName() {
        return name;
    }

    @GetMapping("/words")
    public String getWords() {
        return  words;
    }
}
