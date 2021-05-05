package com.smart.life.saas.web.auth;

import javax.validation.Valid;

import com.smart.life.config.JwtTokenProvider;
import com.smart.life.config.UserToken;
import com.smart.life.saas.domain.core.user.User;
import com.smart.life.saas.domain.core.user.UserService;
import com.smart.life.saas.web.auth.dto.AuthDTO;
import com.smart.life.saas.web.auth.dto.RefreshTokenDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth")
@RestController
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserService userService;

    private final JwtTokenProvider tokenProvider;

    public AuthController(UserService userService, JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping(path = "/token")
    @Operation(summary = "Auth user by token")
    @ApiResponse(description = " An user token config")
    public ResponseEntity<UserToken> createToken(@Valid @RequestBody AuthDTO authDTO) {
        User user = userService.findUserByEmailAndPassword(authDTO.getEmail(), authDTO.getPassword());
        UserToken userToken = tokenProvider.createUserToken(user);
        return ResponseEntity.ok(userToken);
    }

    @PostMapping(path = "/token/refresh")
    @Operation(summary = "Refresh user token")
    @ApiResponse(description = "An user token config")
    public ResponseEntity<UserToken> refreshToken(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) {
        return ResponseEntity.ok(new UserToken());
    }
}
