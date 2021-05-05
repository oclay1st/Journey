package com.smart.life.saas.web.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthDTO {

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String orgId;
}