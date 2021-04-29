package com.smart.life.config;

import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

enum JwtTokenType {
    ACCESS, REFRESH
}

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:my-secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}")
    private long accessTokenExpireTime = 60 * 60 * 1000L; // 1h

    @Value("${security.jwt.token.expire-length:86400000}")
    private long refreshTokenExpireTime = 24 * 60 * 60 * 1000L; // 1d

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String TOKEN_PREFIX = "Bearer ";

    private final UserDetailsService userDetailService;

    public JwtTokenProvider(@Lazy UserDetailsService userDetailService) {
        this.userDetailService = userDetailService;

    }

    /**
     * Create jwt token given an user
     * 
     * @param user
     * @return
     */
    public UserToken createUserToken(UserDetails user) {

        String accessToken = generateAccessToken(user.getUsername());

        String refreshToken = generateRefreshToken(user.getUsername());

        LocalDateTime refreshTokenExpireDateTime = LocalDateTime.now().plusSeconds(refreshTokenExpireTime/1000);

        return new UserToken(accessToken, refreshToken, accessTokenExpireTime, refreshTokenExpireDateTime);

    }

    private String generateAccessToken(String idenity) {
        
        Claims claims = Jwts.claims().setSubject(idenity);
        claims.put("type", JwtTokenType.ACCESS);

        Date now = new Date();
        Date expire = new Date(now.getTime() + accessTokenExpireTime);

        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(expire)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    private String generateRefreshToken(String idenity) {
        Claims claims = Jwts.claims().setSubject(idenity);
        claims.put("type", JwtTokenType.REFRESH);

        Date now = new Date();
        Date expire = new Date(now.getTime() + refreshTokenExpireTime);

        return Jwts.builder().setClaims(claims).setIssuedAt(now).setExpiration(expire)
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) throws ExpiredJwtException, UnsupportedJwtException,
            MalformedJwtException, SignatureException, IllegalArgumentException {
        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return true;
    }

}