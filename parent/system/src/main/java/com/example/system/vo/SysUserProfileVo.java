package com.example.system.vo;

import com.example.system.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


/**
 * 用户登录成功后返回
 * @author ZzH
 * @since 2020.11.07
 * */
@Data
@ToString
@AllArgsConstructor
public class SysUserProfileVo {

  /* 用户信息 */
  @JsonUnwrapped
  private SysUser user;

  @JsonUnwrapped
  private String token;
}
