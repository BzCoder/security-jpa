package com.example.securityjpa.handler;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author BaoZhou
 * @date 2018/7/5
 */
public class myPassWordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(rawPassword.toString());
    }
}
