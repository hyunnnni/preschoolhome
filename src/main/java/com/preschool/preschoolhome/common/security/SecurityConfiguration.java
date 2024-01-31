package com.preschool.preschoolhome.common.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@EnableWebSecurity 원래는 있어야됨 지금은 업데이트 되서 필요 없음
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //세션을 사용하지 않겠다 - 로그인 처리를 세션을 사용하지 않기 떄문에 껐음
                .httpBasic(http -> http.disable())
                //기본적으로(기본제공) 시큐리티는 백엔드에서도 화면의 ui를 만든다(로그인) - 필요없어서 껐음
                .formLogin(form ->form.disable())
                //폼 로그인 끔 -화면을 안써서
                .csrf(csrf -> csrf.disable())
                //스프링이 기본제공해주는 보안기법 - 화면상에서 보안해줘서 필요없음
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        "/api/main/**"
                        ,"/api/teacher/signin"
                        ,"/api/parent/signin"
                        ,"/api/parent/signup"
                        ,"/api/teacher/signup"
                        ,"/api/preschool/teacher"
                        ,"/api/full/listall"
                        ,"/api/parent/check"
                        ,"/api/parent/refresh-token"
                        ,"/api/teacher/refresh-token"
                        ,"/api/teacher/firebase-token"
                        ,"/api/parent/firebase-token"
                        ,"/error"
                        ,"/err"
                        ,"/"
                        ,"/images/**"
                        ,"/pic/**"
                        ,"/profile"
                        ,"/profile/**"
                        ,"/css/**"
                        ,"/index.html"
                        ,"/static/**"
                        ,"/swagger.html"
                        ,"/swagger-ui/**"
                        ,"/v3/api-docs/**"
                        ,"/api/open/**"
                        ,"/info/location"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/main").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(except -> {
                    except.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                            .accessDeniedHandler(new JwtAccessDeniedHandler());
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
