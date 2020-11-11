package com.example.system.service;


import com.example.system.dto.questionnaire.QuestionAnswerDto;
import com.example.system.dto.questionnaire.QuestionnaireDetailDto;
import com.example.system.entity.SysQuestionnaire;
import com.example.system.entity.SysQuestionnaireSubject;
import com.example.system.entity.SysSubject;
import com.example.system.dto.questionnaire.SubjectDto;

import java.util.List;
/**
 * 问卷服务
 * @author ZzH
 * @since 2020.11.10
 * */
public interface ISysQuestionnaireService {

  /**
   * 新建一个问卷
   * */
  void createQuestionnaireByName(String questionnaireName);

  /**
   * 将题目关联到问卷
   * */
  void associateQuestionnaireWithSubject(SysQuestionnaire questionnaire, SysSubject subject);

  /**
   * 批量关联
   * */
  void batchAssociateQuestionnaireWithSubject(SysQuestionnaire questionnaire, List<SysSubject> subjects);
  /**
   * 创建一道题目
   * */
  void createSubjectWithOptions(SysQuestionnaireSubject subject, List<SysQuestionnaire> options);


  List<SubjectDto> getSubjectList();
  /**
   * 返回一个问卷详情
   * */
  QuestionnaireDetailDto getQuestionnaireByID(String questionnaireID);

  /**
   * 返回所有问卷列表
   * */
  List<SysQuestionnaire> getAllQuestionnaires();

  /**
   * 保存答案
   * */

  void save(QuestionAnswerDto answerDto, String userName);

  Boolean isSubmitted(String questionnaireId, String username);
}
