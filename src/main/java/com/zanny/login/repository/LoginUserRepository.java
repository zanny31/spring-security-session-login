package com.zanny.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zanny.login.domain.LoginUser;

public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {
    Optional<LoginUser> findByUsername(String username);
}
