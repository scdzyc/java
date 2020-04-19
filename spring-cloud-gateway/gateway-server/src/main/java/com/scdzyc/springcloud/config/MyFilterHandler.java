package com.scdzyc.springcloud.config;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.handler.FilteringWebHandler;

import java.util.List;

/**
 *  过滤器
 */
public class MyFilterHandler extends FilteringWebHandler {

    public MyFilterHandler(List<GlobalFilter> globalFilters) {
        super(globalFilters);
    }
}
