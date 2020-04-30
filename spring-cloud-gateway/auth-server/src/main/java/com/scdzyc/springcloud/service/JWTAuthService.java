package com.scdzyc.springcloud.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.scdzyc.springcloud.entity.Account;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JWTAuthService {

    //生产环境中应该从外部加密后传入
    private static final String KEY = "you must change it";
    //生产环境中应该从外部加密后传入
    private static final String ISSUER = "icodingedu";
    //定义个过期时间
    private static final long TOKEN_EXP_TIME = 60000;
    //定义传入的参数名
    private static final String USERNAME = "username";

    public String getToken(Account account) {
        Date now = new Date();
        Algorithm algorithm = Algorithm.HMAC256(KEY);
        String token = JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXP_TIME))
                .withClaim(USERNAME, account.getUserName())
                //.withClaim(ROLE,"roleName") 还可以传入其他内容
                .sign(algorithm);
        log.info("jwt generated username={}",account.getUserName());
        return token;
    }

    public Boolean verify(Account account) {
        Algorithm algorithm = Algorithm.HMAC256(KEY);
        log.info("verify jwt - userName={}", account.getUserName());
        try{
            JWTVerifier verify = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .withClaim(USERNAME, account.getUserName())
                    .build();
            verify.verify(account.getToken());
            return true;
        }catch(Exception e) {
            log.error("auth failed :", e.getMessage());
        }
        return false;
    }
}
