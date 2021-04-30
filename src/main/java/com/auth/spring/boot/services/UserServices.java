package com.auth.spring.boot.services;

import com.auth.spring.boot.entity.User;
import com.auth.spring.boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

  private final UserRepository userRepository;

  @Autowired
  public UserServices(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public void save(User user) {
    userRepository.save(user);
  }
}
