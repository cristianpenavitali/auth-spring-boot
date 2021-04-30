package com.auth.spring.boot.dto.create.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewUserRequest {

  private static final long serialVersionD = 1L;

  @NotBlank(message = "Email cannot be blank")
  @NotEmpty(message = "Email cannot be empty")
  @NotNull(message = "Email cannot be null")
  @Size(min = 2, max = 30)
  @Email(message = "Email should be valid")
  private String email;

  @NotBlank(message = "Password cannot be blank")
  @NotEmpty(message = "Password cannot be empty")
  @NotNull(message = "Password cannot be null")
  @Size(min = 2, max = 10)
  private String password;

  @NotBlank(message = "Name cannot be blank")
  @NotEmpty(message = "Name cannot be empty")
  @NotNull(message = "Name cannot be null")
  @Size(min = 2, max = 20)
  private String name;

  @NotBlank(message = "LastName cannot be blank")
  @NotEmpty(message = "LastName cannot be empty")
  @NotNull(message = "LastName cannot be null")
  @Size(min = 2, max = 20)
  private String lastName;
}
