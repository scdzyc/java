package com.scdzyc.springcloud.config;

import com.scdzyc.springcloud.filter.AuthFilter;
import com.scdzyc.springcloud.filter.TimerFilter;
import com.scdzyc.springcloud.tools.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Configuration
public class GatewayCongfig {

    @Autowired
    private TimerFilter timerFilter;

    @Autowired
    private AuthFilter authFilter;

    @Bean
    @Order
    public RouteLocator customerRoutes(RouteLocatorBuilder builder) {
        LocalDateTime start_ldt = LocalDateTime.of(2020, 4, 19,13,55,00);
        LocalDateTime end_ldt = LocalDateTime.of(2020, 4, 19,13,59,00);
        return builder.routes()
            .route(r -> r.path("/path/**") //path断言
                    .and().method(HttpMethod.GET)   //method 断言
                    .and().query("name","zs") // RequestParam 断言
                    .and().header("Authorization")  //Header 断言
                    //.and().cookie("name","icoding") //Cookie断言
                    .filters(f -> f.stripPrefix(1)
                            .addResponseHeader("java_param","gateway-config")
                            .filter(timerFilter) // 使用自定义 Filter
                    )
                    .uri("lb://feign-client")
            )
            // 使用时间片匹配进行秒杀业务
            .route(r -> r.path("/secondkill/**")
                    .and().after(ZonedDateTime.now().plusSeconds(60L))
                    .filters(f -> f.stripPrefix(1))
                    .uri("lb://feign-client")
            )
            // 指定时间
            .route(r -> r.path("/sk/**")
                    .and().between(
                            ZonedDateTime.of(start_ldt, ZoneId.of("Asia/Shanghai")),
                            ZonedDateTime.of(end_ldt, ZoneId.of("Asia/Shanghai")))
                    .filters(f -> f.stripPrefix(1))
                    .uri("lb://feign-client")
            )
            .route(r -> r.path("/jwt/**")
                    .filters( f -> f.stripPrefix(1).filter(authFilter))
                    .uri("lb://feign-client")
            )
            .build();
    }

}
