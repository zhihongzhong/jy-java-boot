package com.example.system.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CaptchaVo {
  private String key;
  private String image;
}
