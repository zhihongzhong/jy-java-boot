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

  /**
   * 创建一条结果记录
   **/
  void createResultByName(String name);

  /**
   * 新建一条记录
   * */
  void createResultByResultDto(CreateResultDto resultDto);

  /**
   * 获取题目列表
   * */
  List<SubjectDto> getSubjectList();

  /**
   * 获取结果列表
   * */
  List<ResultDto> getResultList();

  /**
   * 获取结果对应操作列表
   * */
  List<ResOptionDto> getResultOptionList();
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
  @Deprecated
  void save(QuestionAnswerDto answerDto, String userName);

  /**
  * 判断用户是否提交了问卷
  * */
  Boolean isSubmitted(String questionnaireId, String username);

  /**
   * 用户一进来的时候请求的接口
   * */
  AnswerDto getBeforeAnswerDto(String questionnaireId) throws QuestionnaireNotFoundException;

  /**
   * 用户的答题接口， 根据上一道题返回下一道题
   * */
  AnswerDto getNextQuestion(AnswerModel answerModel) throws QuestionnaireNotFoundException;



}
