package com.example.system.service;

import com.example.exception.UserNotFoundException;
import com.example.system.entity.SysRole;
import com.example.system.entity.SysUser;

/**
 * 用户接口
 * @author ZzH
 * @since 2020.11.06
 * */
public interface ISysUserService {

  /**
   * 根据用户名查询
   * */
  SysUser selectSysByUsername(String username);

  /**
   * 查询用户角色
   * */
  SysRole selectSysRoleByUsername(String username)
    throws UserNotFoundException;

}
