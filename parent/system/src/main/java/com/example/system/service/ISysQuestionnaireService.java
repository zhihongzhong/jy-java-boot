package com.example.system.service;


import com.example.system.dto.questionnaire.QuestionnaireDto;
import com.example.system.entity.SysQuestionnaire;
import com.example.system.entity.SysQuestionnaireSubject;
import com.example.system.entity.SysSubject;
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

  /**
   * 返回一个问卷
   * */
  QuestionnaireDto getQuestionnaireByID(String questionnaireID);
}
