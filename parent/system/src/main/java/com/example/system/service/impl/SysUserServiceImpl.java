package com.example.system.service.impl;


import com.example.common.utils.MD5Util;
import com.example.constant.USER_AND_ROLE;
import com.example.exception.UserNotFoundException;
import com.example.system.entity.SysRole;
import com.example.system.entity.SysUser;
import com.example.system.entity.SysUserRole;
import com.example.system.mapper.SysRoleMapper;
import com.example.system.mapper.SysUserMapper;
import com.example.system.mapper.SysUserRoleMapper;
import com.example.system.service.ISysUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Log4j2
public class SysUserServiceImpl implements ISysUserService  {

  @Autowired
  private SysUserMapper sysUserMapper;

  @Autowired
  private SysUserRoleMapper sysUserRoleMapper;

  @Autowired
  private SysRoleMapper sysRoleMapper;


  @Override
  public SysUser selectSysByUsername(String username) {
    return sysUserMapper.selectByUsername(username);
  }

  @Override
  public SysRole selectSysRoleByUsername(String username) throws UserNotFoundException {
    SysUser user = sysUserMapper.selectByUsername(username);

    if(user == null) throw new UserNotFoundException();
    String userId = user.getId();
    SysUserRole userRole = sysUserRoleMapper.selectByUserID(userId);

    return sysRoleMapper.selectByPrimaryKey(userRole.getRoleId());
  }

  @Override
  @Transactional
  public SysUser insertUserWithRole(USER_AND_ROLE defaultProfile, String username, String password, String nickname) {
    SysUser user = new SysUser();

    final String userId = MD5Util.MD5Encode(username, "");
    user.setId(userId);
    user.setAvatar(defaultProfile.getAvatar());
    user.setUsername(username);
    user.setPassword(password);
    user.setNickname(nickname);
    user.setCreated_at(new Date());

    sysUserMapper.insert(user);
    SysRole role = sysRoleMapper.selectByRoleCode(defaultProfile.getRole());

    SysUserRole userRole = new SysUserRole();
    userRole.setId(userId);
    userRole.setUserId(userId);
    userRole.setRoleId(role.getId());

    return user;
  }


}
