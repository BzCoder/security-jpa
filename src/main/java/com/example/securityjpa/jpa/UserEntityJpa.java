package com.example.securityjpa.jpa;

import com.example.securityjpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户信息JPA
 * @author BaoZhou
 * @date 2018/7/4
 */
public interface UserEntityJpa extends JpaRepository<UserEntity,Long> {
    public UserEntity findByUsername (String username);

}
