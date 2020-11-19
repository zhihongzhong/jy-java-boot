package com.example.system.service.impl;


import com.example.constant.USER_AND_ROLE;
import com.example.system.entity.SysRole;
import com.example.system.entity.SysUser;
import com.example.system.entity.SysUserRole;
import com.example.system.mapper.SysRoleMapper;
import com.example.system.mapper.SysUserMapper;
import com.example.system.mapper.SysUserRoleMapper;
import com.example.system.service.ISysUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Log4j2
/**
 * 用户服务接口实现
 * @author ZzH
 * @since 2020.11.05
 * */
public class SysUserServiceImpl implements ISysUserService {

  @Autowired
  private SysUserMapper sysUserMapper;

  @Autowired
  private SysUserRoleMapper sysUserRoleMapper;

  @Autowired
  private SysRoleMapper sysRoleMapper;


  @Override
  @Transactional
  public SysUser selectSysByUsername(String username) {
    return sysUserMapper.selectByUsername(username);
  }

  /**
   * 根据用户名查找对应的 ROLE_IDs
   */
  @Override
  @Transactional
  public List<SysRole> selectSysRolesByUsername(String username) throws UsernameNotFoundException {
    SysUser user = sysUserMapper.selectByUsername(username);

    if (user == null) throw new UsernameNotFoundException("用户名未找到");
    String userId = user.getId();
    List<SysUserRole> userRoles = sysUserRoleMapper.selectByUserID(userId);

    List<String> roleIds = userRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());

    return sysRoleMapper.selectByPrimaryKeys(roleIds);
  }

  /**
   * 插入用户名和角色， 只能插入单个角色
   * */
  @Override
  @Transactional
  public SysUser insertUserWithRole(USER_AND_ROLE defaultProfile, String username, String password, String nickname) {
    SysUser user = new SysUser();

    final String userId = UUID.randomUUID().toString().replace("-", "");
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
    sysUserRoleMapper.insert(userRole);

    return user;
  }


}
