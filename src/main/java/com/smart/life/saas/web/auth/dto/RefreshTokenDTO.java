package com.smart.life.saas.web.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RefreshTokenDTO {
    @NonNull
    private String refreshToken;
}
