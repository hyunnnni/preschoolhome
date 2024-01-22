package com.preschool.preschoolhome.common.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {
    public MyUserDetails getLoginUser(){

        return (MyUserDetails)SecurityContextHolder
                .getContext()
                .getAuthentication() //토큰값이 넘어옴
                .getPrincipal(); //오브젝트에서 형변환
    }

    public int getLoginUserPk(){
        MyUserDetails myUserDetails = getLoginUser();
        return myUserDetails == null
                ? 0
                : myUserDetails
                .getMyPrincipal()
                .getIuser();
    }

    public int getLevelPk(){
        MyUserDetails myUserDetails = getLoginUser();
        return myUserDetails == null
                ? 0
                : myUserDetails
                .getMyPrincipal()
                .getIlevel();
    }
}
