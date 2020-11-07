package com.example.system.security.api;

import com.example.common.config.jwt.JwtProvider;
import com.example.system.entity.SysRole;
import com.example.system.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.stream.Collectors;

public class JwtUserDetailsService implements UserDetailsService {

  private final JwtProvider jwtProvider;

  public JwtUserDetailsService (JwtProvider jwtProvider) {
    this.jwtProvider = jwtProvider;
  }


  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    return null;
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
