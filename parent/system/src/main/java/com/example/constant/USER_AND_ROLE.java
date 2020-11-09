package com.example.constant;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum  USER_AND_ROLE {

  DEFAULT_USER_PROFILE("http://minio.jeecg.com/otatest/temp/lgo33_1583397323099.png", "admin");

  private final String avatar;
  private final String role;
  USER_AND_ROLE(String avatar, String role) {
    this.avatar = avatar;
    this.role = role;
  }
}
