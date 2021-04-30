package com.auth.spring.boot.controller;

import com.auth.spring.boot.dto.AuthResponse;
import com.auth.spring.boot.dto.create.user.request.NewUserRequest;
import com.auth.spring.boot.dto.login.request.LoginRequest;
import com.auth.spring.boot.usecase.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

  private final UserUseCase userUseCase;

  @Autowired
  public UserController(UserUseCase userUseCase) {
    this.userUseCase = userUseCase;
  }

  @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthResponse> newUser(@Valid @RequestBody NewUserRequest newUserRequest) {
    return new ResponseEntity<>(userUseCase.newUser(newUserRequest), HttpStatus.OK);
  }

  @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
    return new ResponseEntity<>(userUseCase.login(loginRequest), HttpStatus.OK);
  }

  @GetMapping(value = "/test")
  public ResponseEntity<String> test(
      @RequestParam(value = "text", defaultValue = "Hello") String text) {
    return new ResponseEntity<>("Text: " + text, HttpStatus.OK);
  }
}
