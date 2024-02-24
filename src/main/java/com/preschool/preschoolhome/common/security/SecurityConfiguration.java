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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

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
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,
                        "/api/album"
                        ,"/api/full"
                        ,"/api/kid"
                        ,"/api/kid/detail"
                        ,"/api/notice/tea"
                        ,"/api/memory"
                        ,"/api/teacher"
                        //).authenticated())
                        //).hasAnyRole("TEACHER","ADMIN"))
                        //.authorizeHttpRequests(auth -> auth
                        //.requestMatchers(HttpMethod.POST
                        ,"/api/teacher/signup"
                        //).authenticated()
                        //).hasAnyRole("ADMIN")
                        //.requestMatchers(HttpMethod.POST
                        ,"/api/album/comment"
                        ,"/api/notice/par"
                        ,"/api/notice/comment"
                        ).authenticated())
                        //).hasAnyRole("PARENT","TEACHER","ADMIN"))
                        .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET
                        ,"/api/album/edit"
                        ,"/api/full/edit"
                        ,"/api/kid/edit/{ikid}"
                        ,"/api/kid/detail/edit/{ikid}"
                        ,"/api/memory/edit"
                        ,"/api/memory/tag"
                        ,"/api/notice/tag"
                        ,"/api/teacher/kid"
                        ,"/api/teacher/parent"
                        ,"/api/teacher/edit"
                        ,"/api/teacher/parentedit"
                        //).authenticated())
                        //).hasAnyRole("TEACHER","ADMIN"))
                        //.authorizeHttpRequests(auth -> auth
                        //.requestMatchers(HttpMethod.GET
                        ,"/api/teacher"
                        //).authenticated())
                        //).hasAnyRole("ADMIN"))
                        //.authorizeHttpRequests(auth -> auth
                        //.requestMatchers(HttpMethod.GET
                        ,"/api/album"
                        ,"/api/album/listall"
                        ,"/api/full/"
                        ,"/api/full/listall"
                        ,"/api/kid/{year}/{ikid}"
                        ,"/api/memory"
                        ,"/api/memory/detail"
                        ,"/api/notice/edit"
                        ,"/api/notice"
                        ,"/api/notice/detail"
                        ,"/api/parent/edit"
                        ,"/api/parent/putparent"
                        ,"/api/parent/kidadd"
                        ,"/api/preschool/kid"
                        ).authenticated())
                        //).hasAnyRole("PARENT","TEACHER","ADMIN")
                        .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.PUT,
                        "/api/album"
                        ,"/api/full"
                        ,"/api/kid"
                        ,"/api/kid/detail"
                        ,"/api/teacher"
                        ,"/api/teacher/parent"
                        ,"/api/teacher/parentedit"
                        //).authenticated())
                        //).hasAnyRole("TEACHER","ADMIN"))
                        //.authorizeHttpRequests(auth -> auth
                        //.requestMatchers(HttpMethod.PUT
                        ,"/api/notice"
                        ).authenticated())
                        //).hasAnyRole("PARENT","TEACHER","ADMIN"))
                        .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.PATCH,
                        "/api/kid/code/{ikid}"
                        ,"/api/teacher"
                        ,"/api/teacher/kid"
                        //).authenticated())
                        //).hasAnyRole("TEACHER","ADMIN"))
                        //.authorizeHttpRequests(auth -> auth
                        //.requestMatchers(HttpMethod.PATCH
                        ,"/api/parent"
                        ).authenticated())
                        //).hasAnyRole("PARENT","TEACHER","ADMIN"))
                        .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.DELETE,
                        "/api/album"
                        ,"/api/kid"
                        ,"/api/memory"
                        ,"/api/teacher/disconnect"
                        //).authenticated())
                        //).hasAnyRole("TEACHER","ADMIN"))
                        //.authorizeHttpRequests(auth -> auth
                        //.requestMatchers(HttpMethod.DELETE
                        ,"/api/album/comment"
                        ,"/api/full"
                        ,"/api/memory/comment"
                        ,"/api/notice/comment"
                        ).authenticated()
                        //).hasAnyRole("PARENT","TEACHER","ADMIN")
                        .anyRequest().permitAll()
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
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
