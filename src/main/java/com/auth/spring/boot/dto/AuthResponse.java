package com.auth.spring.boot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

  private static final long serialVersionD = 1L;

  private String id;
  private String authToken;
  private String email;
  private String name;
  private String lastName;
  private String namePhoto;
  private byte[] photo;
  private LocalDateTime updateDate;
}
