package com.scdzyc.springcloud.service;

import com.scdzyc.springcloud.entity.Account;
import com.scdzyc.springcloud.tools.AuthResponse;

/**
 * GatewayAuthService class
 *
 * @author yanchuan
 * @module com.scdzyc.springcloud.service
 * @blame yanchuan
 * @since 20/04/20 09:44
 */
public interface GatewayAuthService extends AuthService {

    @Override
    public AuthResponse login(Account account);

    @Override
    public AuthResponse verify(Account account);

    @Override
    public AuthResponse refersh(String refreshToken);
}
