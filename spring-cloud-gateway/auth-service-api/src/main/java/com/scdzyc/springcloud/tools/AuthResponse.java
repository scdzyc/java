package com.scdzyc.springcloud.tools;

import com.scdzyc.springcloud.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthResponse implements Serializable {

    private Account account;

    private Integer code;
}
