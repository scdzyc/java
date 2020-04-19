package com.scdzyc.springcloud.controlller;

import com.scdzyc.springcloud.constant.AuthResponseCode;
import com.scdzyc.springcloud.entity.Account;
import com.scdzyc.springcloud.service.AuthService;
import com.scdzyc.springcloud.tools.AuthResponse;
import com.scdzyc.springcloud.service.JWTAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class JwtControoler implements AuthService {

    @Autowired
    private JWTAuthService jwtAuthService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public AuthResponse login(Account account) {
        Assert.hasText(account.getUserName(),"用户登录名不能为空");
        Assert.hasText(account.getPassword(),"用户登录密码不能为空");
        String token = jwtAuthService.getToken(account);
        account.setToken(token);
        // 保存
        account.setRefreshToken(UUID.randomUUID().toString());
        // 保存到redis
        redisTemplate.opsForValue().set(account.getRefreshToken(), account);
        // 返回
        return AuthResponse.builder()
                .account(account)
                .code(AuthResponseCode.SUCCESS)
                .build();
    }

    @Override
    public AuthResponse verify(Account account) {
        Assert.hasText(account.getUserName(),"用户名不能为空");
        Assert.hasText(account.getToken(),"用户token不能为空");
        Boolean verify = jwtAuthService.verify(account);
        return AuthResponse.builder()
                .code(verify?AuthResponseCode.SUCCESS:AuthResponseCode.INVAID_TOKEN)
                .build();
    }

    @Override
    public AuthResponse refersh(String refreshToken) {
        Account account = (Account) redisTemplate.opsForValue().get(refreshToken);
        if(null == account) {
            return AuthResponse.builder()
                    .code(AuthResponseCode.USER_NOT_FOUND)
                    .build();
        }
        String token = jwtAuthService.getToken(account);
        account.setToken(token);
        //
        account.setRefreshToken(UUID.randomUUID().toString());
        redisTemplate.delete(refreshToken);
        redisTemplate.opsForValue().set(account.getRefreshToken(), account);

        return AuthResponse.builder()
                .account(account)
                .code(AuthResponseCode.SUCCESS)
                .build();
    }
}
