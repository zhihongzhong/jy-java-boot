package com.example.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
@ApiModel(
  value = "无验证码登录"
)
public class SysUserWithoutCaptchaModel {

  @ApiModelProperty(
    value = "用户名"
  )
  @NotNull
  private String username;
  @ApiModelProperty(
    value = "密码"
  )
  @NotNull
  private String password;
}
