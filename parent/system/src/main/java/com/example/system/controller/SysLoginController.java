package com.example.system.controller;

import cn.hutool.core.util.RandomUtil;
import com.example.common.config.jwt.JwtConfigProperty;
import com.example.common.config.jwt.JwtProvider;
import com.example.common.utils.RandomImageUtil;
import com.example.common.utils.RedisUtil;
import com.example.common.constant.RESPONSE_STATUS;
import com.example.constant.USER_AND_ROLE;
import com.example.system.dto.SysUserModel;
import com.example.system.dto.SysUserRegisterModel;
import com.example.system.exception.*;
import com.example.system.entity.SysUser;
import com.example.system.service.ISysUserService;
import com.example.system.vo.CaptchaVo;
import com.example.system.vo.SysUserProfileVo;
import com.example.common.utils.ResultJSON;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 用户登录 Controller
 * @author ZzH
 * @since 2020.11.06
 * */
@RestController
@RequestMapping("/sys/auth")
@Log4j2
public class SysLoginController {

  private final RedisUtil redisUtil;
  private final JwtProvider jwtProvider;

  private final ISysUserService userService;

  /* 验证码有效时间 */
  private static final int DEFAULT_CAPTCHA_TIME = 60 * 5;

  private final String passwordField;

  public SysLoginController(RedisUtil redisUtil, ISysUserService userService, JwtProvider jwtProvider, JwtConfigProperty property) {
    this.redisUtil = redisUtil;
    this.userService = userService;
    this.jwtProvider = jwtProvider;
    this.passwordField = property.getPasswordField();
  }

  private Boolean checkCaptcha(String captcha, String captchaKey) throws CaptchaException {
    if(captcha == null || captchaKey == null) {
      throw new CaptchaException();
    }


    final String cachedCaptcha = (String) redisUtil.get(captchaKey);
    log.info("取出缓存：[{}]", cachedCaptcha);

    return cachedCaptcha != null && cachedCaptcha.equals(captcha);
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
    throws UsernameNotFoundException, IncorrectPasswordException, CaptchaException, IncorrectCaptchaException {

    final String username = userModel.getUsername();
    final String password = userModel.getPassword();

    final String captchaKey = userModel.getCaptchaKey();
    final String captcha = userModel.getCaptcha();

    log.info("收到 userModel ： [{}]", userModel.toString());

    if(!checkCaptcha(captcha, captchaKey)) {
      throw new IncorrectCaptchaException();
    }


    /* 消费验证码 */
    redisUtil.delete(captchaKey);
    SysUser sysUser = userService.selectSysByUsername(username);

    if(sysUser == null) {
      throw new UsernameNotFoundException("用户名未找到");
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
   * 用户注册模块
   * */
  @ApiOperation(
    value = "用户注册",
    response = SysUserProfileVo.class
  )
  @PostMapping("register")
  public ResultJSON<SysUserProfileVo> register(@RequestBody SysUserRegisterModel userRegisterModel)
    throws UserAlreadyExistedException, CaptchaException, IncorrectCaptchaException, PasswordDiscrepancyException {

    final String username = userRegisterModel.getUsername();
    final String captcha = userRegisterModel.getCaptcha();
    final String captchaKey = userRegisterModel.getCaptchaKey();

    final String password = userRegisterModel.getPassword();
    final String confirmPassword = userRegisterModel.getConfirmPassword();

    final String nickname = userRegisterModel.getNickname();

    if(!checkCaptcha(captcha, captchaKey)) {
      throw new IncorrectCaptchaException();
    }

    if(!password.equals(confirmPassword)) {
      throw new PasswordDiscrepancyException();
    }

    if(userService.selectSysByUsername(username) != null) {
      throw new UserAlreadyExistedException();
    }

    SysUser user = userService.insertUserWithRole(USER_AND_ROLE.DEFAULT_USER_PROFILE, username, password, nickname);
    Map<String, String> claims = new HashMap<>();

    claims.put(passwordField, password);
    String token = jwtProvider.genToken(username, claims);
    SysUserProfileVo userProfileVo = new SysUserProfileVo(user, token);

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
  @ExceptionHandler(UsernameNotFoundException.class)
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

  /**
   * 处理验证码为空
   * */
  @ExceptionHandler(CaptchaException.class)
  public ResultJSON<String> handleCaptchaException() {
    return ResultJSON.fail(RESPONSE_STATUS.EMPTY_CAPTCHA);
  }

  /**
   * 处理用户已经存在
   * */
  @ExceptionHandler(UserAlreadyExistedException.class)
  public ResultJSON<String> handleUserAlreadyExistedException() {
    return ResultJSON.fail(RESPONSE_STATUS.USER_ALREADY_EXISTED_EXCEPTION);
  }

  /**
   * 处理验证码错误
   * */
  @ExceptionHandler(IncorrectCaptchaException.class)
  public ResultJSON<String> handleIncorrectCaptchaException() {
    return ResultJSON.fail(RESPONSE_STATUS.INCORRECT_CAPTCHA);
  }

  /**
   * 处理输入密码不一致
   * */
  @ExceptionHandler(PasswordDiscrepancyException.class)
  public ResultJSON<String> handlePasswordDiscrepancyException() {
    return ResultJSON.fail(RESPONSE_STATUS.PASSWORD_DISCREPANCY_EXCEPTION);
  }
}
