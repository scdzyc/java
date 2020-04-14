package com.scdzyc.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RefershConfigController class
 *
 * @author yanchuan
 * @module com.scdzyc.springcloud.controller
 * @blame yanchuan
 * @since 20/04/13 16:33
 */
// 允许bean 在运行时更新(进入源码了解细节),必须加这个注解，否则不会运行时不会更新
@RefreshScope
@RequestMapping("/refresh")
@RestController
public class RefreshConfigController {

    @Value("${words}")
    private String words;

    @GetMapping("/words")
    public String getWords() {
        return words;
    }
}
