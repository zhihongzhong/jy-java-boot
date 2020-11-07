package com.example.util;


import com.example.constant.RESPONSE_STATUS;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public class ResultJSON<T> {
  private String msg;
  private String errorMsg;
  private int code;
  T data;


  /**
   * 处理成功之后返回
   * */
  public static <T> ResultJSON<T> success() {
    ResultJSON<T> succeed = new ResultJSON<>();
    succeed.code = RESPONSE_STATUS.SUCCESS.getValue();
    succeed.msg = RESPONSE_STATUS.SUCCESS.getDescription();
    return succeed;
  }

  /**
   * 处理成功之后可以调用这个方法添加数据
   * */
  public ResultJSON<T> addData(T data) {
    log.debug("是否成功: [{}]", this.code);
    if(this.code == RESPONSE_STATUS.SUCCESS.getValue())
    this.data = data;
    return this;
  }

  /**
   * 处理失败的时候返回
   * */
  public static <T> ResultJSON<T> fail(RESPONSE_STATUS status) {

    ResultJSON<T> _this = new ResultJSON<>();
    _this.code = status.getValue();
    _this.errorMsg = status.getDescription();
    _this.data = null;

    return _this;
  }
}
