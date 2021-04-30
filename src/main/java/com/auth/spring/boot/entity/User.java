package com.auth.spring.boot.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER")
public class User implements Serializable {

  private static final long serialVersionD = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_user", updatable = false, nullable = false)
  private Integer idUser;

  @Column(name = "id", unique = true)
  private String id;

  @Column(name = "auth_token", columnDefinition = "TEXT")
  private String authToken;

  @Column(name = "email")
  private String email;

  @Column(name = "name")
  private String name;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "name_photo")
  private String namePhoto;

  @Column(name = "photo")
  private byte[] photo;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @Column(name = "update_date")
  private LocalDateTime updateDate;

  @Column(name = "password")
  private String password;
}
