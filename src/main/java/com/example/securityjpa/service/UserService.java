package com.example.securityjpa.service;

import com.example.securityjpa.entity.UserEntity;
import com.example.securityjpa.jpa.UserEntityJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 自定义身份认证
 * 需要实现UserDetailsService接口
 * @author BaoZhou
 * @date 2018/7/4
 */
public class UserService implements UserDetailsService {
    @Autowired
    UserEntityJpa userEntityJpa;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityJpa.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("未找到用户名");
        }
        return userEntity;
    }
}
