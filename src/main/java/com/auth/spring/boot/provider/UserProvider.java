package com.auth.spring.boot.provider;

import com.auth.spring.boot.dto.AuthResponse;
import com.auth.spring.boot.dto.create.user.request.NewUserRequest;
import com.auth.spring.boot.dto.login.request.LoginRequest;
import org.springframework.stereotype.Component;

@Component
public interface UserProvider {

  AuthResponse newUser(NewUserRequest newUserRequest);

  AuthResponse login(LoginRequest loginRequest);
}
