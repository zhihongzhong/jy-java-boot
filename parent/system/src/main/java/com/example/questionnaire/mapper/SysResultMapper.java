package com.example.questionnaire.mapper;

import com.example.questionnaire.entity.SysResult;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface SysResultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_result
     *
     * @mbg.generated Mon Nov 23 16:36:26 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_result
     *
     * @mbg.generated Mon Nov 23 16:36:26 CST 2020
     */
    int insert(SysResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_result
     *
     * @mbg.generated Mon Nov 23 16:36:26 CST 2020
     */
    int insertSelective(SysResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_result
     *
     * @mbg.generated Mon Nov 23 16:36:26 CST 2020
     */
    SysResult selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_result
     *
     * @mbg.generated Mon Nov 23 16:36:26 CST 2020
     */
    int updateByPrimaryKeySelective(SysResult record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_result
     *
     * @mbg.generated Mon Nov 23 16:36:26 CST 2020
     */
    int updateByPrimaryKey(SysResult record);

    List<SysResult> selectAll();
}