package com.example.system.controller;

import cn.hutool.core.util.RandomUtil;
import com.example.common.config.jwt.JwtProvider;
import com.example.common.utils.RandomImageUtil;
import com.example.common.utils.RedisUtil;

import com.example.constant.RESPONSE_STATUS;
import com.example.dto.SysUserModel;
import com.example.exception.CaptchaException;
import com.example.exception.IncorrectPasswordException;
import com.example.exception.UserNotFoundException;
import com.example.system.entity.SysUser;
import com.example.system.service.ISysUserService;
import com.example.system.vo.CaptchaVo;
import com.example.system.vo.SysUserProfileVo;
import com.example.util.ResultJSON;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 * 用户登录 Controller
 * @author ZzH
 * @since 2020.11.06
 * */
@RestController
@RequestMapping("/sys")
@Log4j2
public class SysLoginController {

  private final RedisUtil redisUtil;
  private final JwtProvider jwtProvider;

  private final ISysUserService userService;

  /* 验证码有效时间 */
  private static final int DEFAULT_CAPTCHA_TIME = 60 * 5;

  public SysLoginController(RedisUtil redisUtil, ISysUserService userService, JwtProvider jwtProvider) {
    this.redisUtil = redisUtil;
    this.userService = userService;
    this.jwtProvider = jwtProvider;
  }

  /**
   *
   * 生成验证码，
   * 有效时间 5 分钟
   * */
  @GetMapping(value = "captcha")
  public ResultJSON<CaptchaVo> genCaptcha(@RequestParam("seed")String seed) throws IOException{
    /* 4 位数验证码 */
    final String randomCode = RandomUtil.randomNumbers(4);
    /* 11 位缓存 key */
    String captchaKey = RandomUtil.randomNumbers(11);

    redisUtil.put(captchaKey, randomCode, DEFAULT_CAPTCHA_TIME);

    String base64Image = RandomImageUtil.generateBase64(randomCode);
    final CaptchaVo captchaVo = new CaptchaVo(captchaKey, base64Image);
    log.info("生成验证码： [{}]", captchaVo);
    return ResultJSON.<CaptchaVo>success().addData(captchaVo);
  }

  /**
   * 用户登录
   * */
  @PostMapping(value = "login")
  public ResultJSON<SysUserProfileVo> login(@RequestBody SysUserModel userModel)
    throws UserNotFoundException, IncorrectPasswordException, CaptchaException {

    final String username = userModel.getUsername();
    final String password = userModel.getPassword();

    final String captchaKey = userModel.getCaptchaKey();
    final String captcha = userModel.getCaptcha();

    log.info("收到 userModel ： [{}]", userModel.toString());
    if(captcha == null || captchaKey == null) {
      throw new CaptchaException();
    }


    final String cachedCaptcha = (String) redisUtil.get(captchaKey);
    log.info("取出缓存：[{}]", cachedCaptcha);

    if(cachedCaptcha == null || !cachedCaptcha.equals(captcha)) {
      throw new CaptchaException();
    }

    /* 消费验证码 */
    redisUtil.delete(captchaKey);
    SysUser sysUser = userService.selectSysByUsername(username);

    if(sysUser == null) {
      throw new UserNotFoundException();
    }

    if(!sysUser.getPassword().equals(password)) {
      throw new IncorrectPasswordException();
    }

    log.info("即将生产用户[{}]的token", username);
    String token = jwtProvider.genToken(username);
    SysUserProfileVo userProfileVo = new SysUserProfileVo(sysUser, token);
    log.info("获取 token: [{}]", token);
    return ResultJSON.<SysUserProfileVo>success().addData(userProfileVo);
  }

  /**
  * 处理 IO 异常
  */
  @ExceptionHandler(IOException.class)
  public ResultJSON<String> handleIOException() {
    return ResultJSON.fail(RESPONSE_STATUS.IO_EXCEPTION);
  }


  /**
   * 处理用户未找到异常
   * */
  @ExceptionHandler(UserNotFoundException.class)
  public ResultJSON<String> handleUserNotFoundException() {
    return ResultJSON.fail(RESPONSE_STATUS.USER_NOT_FOUND_EXCEPTION);
  }

  /**
   * 处理密码错误异常
   * */
  @ExceptionHandler(IncorrectPasswordException.class)
  public ResultJSON<String> handleIncorrectPasswordException() {
    return ResultJSON.fail(RESPONSE_STATUS.INCORRECT_PASSWORD_EXCEPTION);
  }

  @ExceptionHandler(CaptchaException.class)
  public ResultJSON<String> handleCaptchaException() {
    return ResultJSON.fail(RESPONSE_STATUS.EMPTY_CAPTCHA);
  }
}
