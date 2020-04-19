package com.scdzyc.springcloud.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Account implements Serializable {

    private String userName;

    private String token;
    /** 当token接近失效的时候可以用refreshToken生成一个新的token*/
    private String refreshToken;

    private String password;
}
