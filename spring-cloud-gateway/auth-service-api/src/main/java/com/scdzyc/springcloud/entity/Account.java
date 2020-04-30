package com.scdzyc.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Account implements Serializable {

    private String userName;

    private String token;
    /** 当token接近失效的时候可以用refreshToken生成一个新的token*/
    private String refreshToken;

    private String password;
}
