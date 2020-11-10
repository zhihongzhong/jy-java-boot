package com.example.system.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@ToString
@ApiModel(
  value = "登录对象",
  description = "登录对象"
)
public class SysUserModel {

  @Min(value = 3, message = "用户名过短")
  @Max(value = 16, message = "用户名过长")
  @ApiModelProperty(value = "用户名")
  private String username;


  @Min(value = 8, message = "密码过短")
  @Max(value = 16, message = "密码过长")
  @ApiModelProperty(value = "密码")
  private String password;

  @Size(min = 4, max = 4, message = "4位验证码")
  @ApiModelProperty(value = "验证码")
  private String captcha;

  @Size(min = 11, max = 11, message = "11位验证码key")
  @ApiModelProperty(value = "验证码key")
  private String captchaKey;

}
