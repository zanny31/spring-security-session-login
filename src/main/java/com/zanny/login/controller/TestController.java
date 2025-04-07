package com.zanny.login.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TestController {

    @GetMapping("/debug/auth")
    public String debugAuthenticationInfo(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        return String.format("""
                [SecurityContextHolder 정보]
                - principal 클래스 : %s
                - principal 정보 : %s
                - 권한 목록: %s
                """,
                principal.getClass().getName(),
                principal,
                authentication.getAuthorities());
    }

    @GetMapping("/debug/session")
    public String debugSessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null)
            return "세션 없음";

        Object secContext = session.getAttribute("SPRING_SECURITY_CONTEXT");
        return String.format("""
                [HttpSession 정보]
                - 세션 ID: %s
                - SPRING_SECURITY_CONTEXT 클래스: %s
                - SPRING_SECURITY_CONTEXT 내용: %s
                """,
                session.getId(),
                (secContext != null ? secContext.getClass().getName() : "null"),
                secContext);

    }
}
