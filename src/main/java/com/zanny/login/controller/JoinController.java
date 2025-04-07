package com.zanny.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zanny.login.domain.LoginUser;
import com.zanny.login.repository.LoginUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class JoinController {

    private final LoginUserRepository loginUserRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    public String join(@RequestParam String username, @RequestParam String password) {
        LoginUser user = LoginUser.builder()
                .username(username)
                .password(passwordEncoder.encode(password)) // 암호화 필수!
                .role("USER")
                .build();

        loginUserRepository.save(user);
        return "redirect:/login";
    }
}
