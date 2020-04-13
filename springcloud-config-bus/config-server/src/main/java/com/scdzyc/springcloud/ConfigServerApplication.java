package com.scdzyc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * ConfigServerApplication class
 *
 * @author yanchuan
 * @module com.scdzyc.springcloud
 * @blame yanchuan
 * @since 20/04/13 16:05
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
