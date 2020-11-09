package com.example.constant;

public enum RESPONSE_STATUS {

  /* 用户名未找到 */
  USER_NOT_FOUND_EXCEPTION(400, "用户未找到"),

  /* 权限拒绝 */
  ACCESS_DENIED_EXCEPTION(401, "权限拒绝"),

  /* 密码错误 */
  INCORRECT_PASSWORD_EXCEPTION(402, "密码错误"),

  /* 验证码为空 */
  EMPTY_CAPTCHA(403, "验证码为空"),

  /* 验证码不正确 */
  INCORRECT_CAPTCHA(404, "验证码不正确"),

  /* 用户已存在 */
  USER_ALREADY_EXISTED_EXCEPTION(405, "用户已存在"),

  /* 密码不一致 */
  PASSWORD_DISCREPANCY_EXCEPTION(406, "密码不一致"),


  /* IO 异常时候返回 */
  IO_EXCEPTION(500, "IO异常"),


  /* 请求正常返回 */
  SUCCESS(0, "操作成功");

  private final int value;
  private final String description;

  RESPONSE_STATUS(int value, String description) {
    this.value = value;
    this.description = description;
  }

  public int getValue() {
    return value;
  }

  public String getDescription() {
    return description;
  }
}
