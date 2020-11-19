package com.example.system.exception;


import com.example.common.constant.RESPONSE_STATUS;

/**
 * 通用Api异常
 * */
public class ApiException extends Exception {
  private final RESPONSE_STATUS status;

  public ApiException(RESPONSE_STATUS status) {
    this.status = status;
  }

  @Override
  public String getMessage() {
    return status.getDescription();
  }

  public int statusCode() {
    return status.getValue();
  }

  public RESPONSE_STATUS getStatus() {
    return status;
  }
}
