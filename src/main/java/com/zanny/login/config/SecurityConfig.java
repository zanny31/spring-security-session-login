package com.zanny.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // TODO: crsf 비활성화는 개발용, 운영 환경에서는 활성화 필요
                .csrf(crsf -> crsf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                .authorizeHttpRequests((auth) -> auth
                        // TODO: h2-console, join 및 dub 경로는 테스트용, 운영 환경에서는 제거 필요
                        .requestMatchers("/h2-console/**", "/join", "/join.html", "/debug/auth", "/debug/session")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
