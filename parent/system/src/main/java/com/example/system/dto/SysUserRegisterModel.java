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
  value = "注册对象",
  description = "注册用户时导入"
)
public class SysUserRegisterModel {
  @Min(value = 3, message = "用户名过短")
  @Max(value = 16, message = "用户名过长")
  @ApiModelProperty(value = "用户名")
  private String username;


  @Min(value = 8, message = "密码过短")
  @Max(value = 16, message = "密码过长")
  @ApiModelProperty(value = "密码")
  private String password;

  @Size(min = 8, max = 16, message = "确认密码必须8-16位数")
  private String confirmPassword;

  @Size(min = 4, max = 16, message = "昵称必须4-16位字符串")
  private String nickname;

  @Size(min = 4, max = 4, message = "4位验证码")
  @ApiModelProperty(value = "验证码")
  private String captcha;

  @Size(min = 11, max = 11, message = "11位验证码key")
  @ApiModelProperty(value = "key")
  private String captchaKey;

}
