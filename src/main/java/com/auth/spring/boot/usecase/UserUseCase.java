package com.auth.spring.boot.usecase;

import com.auth.spring.boot.dto.AuthResponse;
import com.auth.spring.boot.dto.create.user.request.NewUserRequest;
import com.auth.spring.boot.dto.login.request.LoginRequest;
import com.auth.spring.boot.provider.UserProvider;
import org.springframework.stereotype.Component;

@Component
public class UserUseCase {

  private final UserProvider userProvider;

  public UserUseCase(UserProvider userProvider) {
    this.userProvider = userProvider;
  }

  public AuthResponse newUser(NewUserRequest newUserRequest) {
    return userProvider.newUser(newUserRequest);
  }

  public AuthResponse login(LoginRequest loginRequest) {
    return userProvider.login(loginRequest);
  }
}
