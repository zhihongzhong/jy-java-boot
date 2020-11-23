package com.example.questionnaire.service;


import com.example.questionnaire.dto.*;
import com.example.questionnaire.entity.SysQuestionnaire;
import com.example.questionnaire.entity.SysSubject;
import com.example.questionnaire.exception.AlreadySubmittedException;
import com.example.questionnaire.exception.QuestionnaireNotFoundException;

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
  void associateQuestionnaireWithSubject(AssociationDto dto) throws AlreadySubmittedException;

  /**
   * 批量关联
   * */
  void batchAssociateQuestionnaireWithSubject(SysQuestionnaire questionnaire, List<SysSubject> subjects);
  /**
   * 创建一道题目
   * */
  void createSubjectWithOptions(AddSubjectDto dto);


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

  AnswerDto getBeforeAnswerDto(String questionnaireId) throws QuestionnaireNotFoundException;


  AnswerDto getNextQuestion(AnswerModel answerModel) throws QuestionnaireNotFoundException;

}
