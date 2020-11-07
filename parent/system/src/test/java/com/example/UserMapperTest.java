package com.example;


import com.example.system.entity.SysUser;
import com.example.system.mapper.SysUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SystemApplication.class})
@Profile(value = "dev")
public class UserMapperTest {

  @Autowired
  private SysUserMapper userMapper;

  @Test
  public void testSelectUser() {
    final String username = "admin";

    SysUser sysUser = userMapper.selectByUsername(username);
    assertEquals(sysUser.getUsername(), username);
  }
}
