package com.scdzyc.springcloud.service;

import com.scdzyc.springcloud.entity.Account;
import com.scdzyc.springcloud.tools.AuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("auth-server")
@RequestMapping("/auth")
public interface AuthService {

    @PostMapping("/login")
    AuthResponse login(@RequestBody Account account);

    @PostMapping("/verify")
    AuthResponse verify(@RequestBody Account account);

    @PostMapping("/refresh")
    AuthResponse refersh(@RequestBody String refreshToken);

}
