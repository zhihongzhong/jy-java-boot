package com.example.system.mapper;

import com.example.system.entity.SysSubjectOption;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysSubjectOptionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject_option
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject_option
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    int insert(SysSubjectOption record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject_option
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    int insertSelective(SysSubjectOption record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject_option
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    SysSubjectOption selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject_option
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    int updateByPrimaryKeySelective(SysSubjectOption record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_subject_option
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    int updateByPrimaryKey(SysSubjectOption record);

    List<SysSubjectOption> selectBySubjectId(String id);
}