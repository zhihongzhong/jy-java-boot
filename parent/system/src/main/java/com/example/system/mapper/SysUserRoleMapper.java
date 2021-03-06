package com.example.system.mapper;

import com.example.system.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysUserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    int insert(SysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    int insertSelective(SysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    SysUserRole selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    int updateByPrimaryKeySelective(SysUserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_user_role
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    int updateByPrimaryKey(SysUserRole record);

    List<SysUserRole> selectByUserID(String userId);
}