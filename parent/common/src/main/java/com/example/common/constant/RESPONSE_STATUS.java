package com.example.common.constant;

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


  /* 文件名不支持 */
  FILE_TYPE_NOT_SUPPORT(600, "文件类型不支持"),

  /* 问卷不存在 */
  QUESTIONNAIRE_NOT_FOUND(700, "问卷不存在"),

  /* 题目列表不完整 */
  SUBJECT_NOT_FOUND(701, "题目列表不完整"),

  /* 参数列表有误 */
  QUESTIONNAIRE_FORMAT_BROKEN(702, "参数列表不完整"),

  /* 选项为空 */
  QUESTIONNAIRE_EMPTY_OPTION(703, "选项为空"),

  /* 已提交 */
  QUESTIONNAIRE_ALREADY_SUBMITTED(704, "用户已提交过问卷"),

  /* 选项不能为空 */
  QUESTIONNAIRE_EMPTY_OPTIONS(705, "选项不能为空"),
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
