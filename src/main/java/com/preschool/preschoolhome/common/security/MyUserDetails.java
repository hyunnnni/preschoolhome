package com.preschool.preschoolhome.common.security;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Data
@Builder
public class MyUserDetails implements UserDetails {

    private MyPrincipal myPrincipal;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //호출후 값으로 권한 처리
        if (myPrincipal == null) {
            return null;
        }
        return Collections.singleton
                (new SimpleGrantedAuthority
                        ("ROLE_" + this.myPrincipal.getRole()));  //권한을 부여할때 ROLE_을 붙여줘야함
        //ROLE_은 스프링 시큐리티에서 권한을 인식하기 위해 붙여줘야함

    }
    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
