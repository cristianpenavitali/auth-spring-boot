package com.auth.spring.boot.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AuthUtils {

  public static String getJWTToken(String id, String email) {
    String secretKey = "mySecretKey";
    List<GrantedAuthority> grantedAuthorities =
        AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

    return Jwts.builder()
        .setId(id)
        .setSubject(email)
        .claim(
            "authorities",
            grantedAuthorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()))
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 600000 * 6 * 24))
        .signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
        .compact();
  }
}
