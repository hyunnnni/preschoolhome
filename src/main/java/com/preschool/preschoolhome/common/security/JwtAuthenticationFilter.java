package com.preschool.preschoolhome.common.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //요청때마다 실행
        String token = jwtTokenProvider.resolveToken(request);//토큰값을 보내줌 요청이 될때 마다 실행됨 reequest에 토큰이 담겨있는지 확인
        //값이 넘어 오면 로그인한 사용자, null이면 로그인 사용자가 아님
        log.info("JwtAuthentication - token : {}", token);

        if (token != null && jwtTokenProvider.isValidateToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth); //auth을 담아줌
                //auth 개인공간을 만들어 줌
            }
        }

        filterChain.doFilter(request,response);//사용하고 다시 다음 필터에 값을 넘겨줌

    }
}
