package com.preschool.preschoolhome.common.security;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPrincipal {
    private int iuser;
    private int ilevel;

}
