package com.auth.spring.boot.provider;

import com.auth.spring.boot.dto.AuthResponse;
import com.auth.spring.boot.dto.create.user.request.NewUserRequest;
import com.auth.spring.boot.dto.login.request.LoginRequest;
import com.auth.spring.boot.entity.User;
import com.auth.spring.boot.services.UserServices;
import com.auth.spring.boot.translator.UserTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class UserProviderImpl implements UserProvider {

  private final UserServices userServices;
  private final UserTranslator userTranslator;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserProviderImpl(
      UserServices userServices, UserTranslator userTranslator, PasswordEncoder passwordEncoder) {
    this.userServices = userServices;
    this.userTranslator = userTranslator;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public AuthResponse newUser(NewUserRequest newUserRequest) {
    Optional<User> userOptional = this.userServices.getUserByEmail(newUserRequest.getEmail());

    if (userOptional.isPresent()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
    }

    User user = this.userTranslator.requestUserTranslator(newUserRequest);

    this.userServices.save(user);

    return this.userTranslator.responseUserTranslator(user);
  }

  @Override
  public AuthResponse login(LoginRequest loginRequest) {
    Optional<User> userOptional = this.userServices.getUserByEmail(loginRequest.getEmail());

    if (userOptional.isEmpty()) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, String.format("User %s not found.", loginRequest.getEmail()));
    }

    if (!loginRequest.getEmail().equalsIgnoreCase(userOptional.get().getEmail())) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "User email or password is incorrect.");
    }

    if (!passwordEncoder.matches(loginRequest.getPassword(), userOptional.get().getPassword())) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "User email or password is incorrect.");
    }

    return this.userTranslator.responseUserTranslator(userOptional.get());
  }
}
