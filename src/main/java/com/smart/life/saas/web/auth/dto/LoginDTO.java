package com.smart.life.saas.web.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class LoginDTO {

    @NonNull
    @NotEmpty
    @Email
    private String email;

    @NonNull
    @NotEmpty
    private String password;

    @NonNull
    private String orgId;
}