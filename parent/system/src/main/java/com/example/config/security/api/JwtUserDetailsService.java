package com.example.config.security.api;

import com.example.system.entity.SysRole;
import com.example.system.entity.SysUser;
import com.example.system.service.ISysUserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Component
@Log4j2
public class JwtUserDetailsService implements UserDetailsService {

  private ISysUserService userService;

  public JwtUserDetailsService (ISysUserService userService) {
    this.userService = userService;
  }


  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

    log.info("用户名 s [{}]", s);
    SysUser user  = userService.selectSysByUsername(s);
    if(user == null) throw new UsernameNotFoundException("用户名未找到");
    List<SysRole> roles = userService.selectSysRolesByUsername(s);
    return new JwtUserDetails(user, roles);
  }

  private static class JwtUserDetails implements UserDetails {

    private final SysUser user;
    private final java.util.List<SysRole> roles;
    public JwtUserDetails(SysUser user, java.util.List<SysRole> roles) {
      this.user = user;
      this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole_code())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
      return user.getPassword();
    }

    @Override
    public String getUsername() {
      return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }
  }
}
