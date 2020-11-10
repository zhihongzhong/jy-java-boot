package com.example.system.mapper;

import com.example.system.entity.SysQuestionnaireSubject;
import com.example.system.entity.param.QuestionnaireParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SysQuestionnaireSubjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_questionnaire_subject
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_questionnaire_subject
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    int insert(SysQuestionnaireSubject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_questionnaire_subject
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    int insertSelective(SysQuestionnaireSubject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_questionnaire_subject
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    SysQuestionnaireSubject selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_questionnaire_subject
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    int updateByPrimaryKeySelective(SysQuestionnaireSubject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_questionnaire_subject
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    int updateByPrimaryKey(SysQuestionnaireSubject record);

    /**
     * 判断关联是否存在
     * */
    SysQuestionnaireSubject isExists(SysQuestionnaireSubject record);

    /**
     * 判断关联是否存在
     * */
    List<SysQuestionnaireSubject> batchIsExists(QuestionnaireParam param);

    /**
     * 批量插入
     * */
    void insertAll(List<SysQuestionnaireSubject> records);

    /**
     * 根据 questionnaire id 查询
     * */
    List<SysQuestionnaireSubject> selectByQuestionnaireId(String questionnaireId);
}