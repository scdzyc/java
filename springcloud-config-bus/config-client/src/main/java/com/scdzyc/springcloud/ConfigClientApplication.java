package com.scdzyc.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * ConfigClientApplication class
 *
 * @author yanchuan
 * @module com.scdzyc.springcloud
 * @blame yanchuan
 * @since 20/04/13 16:18
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }
}
