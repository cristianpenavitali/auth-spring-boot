package com.auth.spring.boot.translator;

import com.auth.spring.boot.dto.AuthResponse;
import com.auth.spring.boot.dto.create.user.request.NewUserRequest;
import com.auth.spring.boot.entity.User;
import com.auth.spring.boot.utils.AuthUtils;
import com.auth.spring.boot.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserTranslator {

  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserTranslator(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public User requestUserTranslator(NewUserRequest newUserRequest) {
    String id = UUID.randomUUID().toString();
    return User.builder()
        .name(newUserRequest.getName())
        .lastName(newUserRequest.getLastName())
        .email(newUserRequest.getEmail())
        .password(passwordEncoder.encode(newUserRequest.getPassword()))
        .id(id)
        .authToken(AuthUtils.getJWTToken(id, newUserRequest.getEmail()))
        .namePhoto(Constant.EMPTY)
        .photo(new byte[0])
        .creationDate(LocalDateTime.now())
        .updateDate(LocalDateTime.now())
        .build();
  }

  public AuthResponse responseUserTranslator(User user) {
    return AuthResponse.builder()
        .id(user.getId())
        .authToken(AuthUtils.getJWTToken(user.getId(), user.getEmail()))
        .email(user.getEmail())
        .name(user.getName())
        .lastName(user.getLastName())
        .namePhoto(user.getNamePhoto())
        .photo(user.getPhoto())
        .updateDate(user.getUpdateDate())
        .build();
  }
}
