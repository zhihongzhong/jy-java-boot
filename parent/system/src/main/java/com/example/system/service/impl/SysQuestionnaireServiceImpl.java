package com.example.system.service.impl;

import com.example.common.utils.UUIDUtil;
import com.example.constant.QUESTIONNAIRE_AND_SUBJECT;
import com.example.system.dto.questionnaire.QuestionnaireDto;
import com.example.system.dto.questionnaire.SubjectDto;
import com.example.system.entity.SysQuestionnaire;
import com.example.system.entity.SysQuestionnaireSubject;
import com.example.system.entity.SysSubject;
import com.example.system.entity.SysSubjectOption;
import com.example.system.entity.param.QuestionnaireParam;
import com.example.system.mapper.*;
import com.example.system.service.ISysQuestionnaireService;
import lombok.extern.log4j.Log4j2;
import org.omg.PortableInterceptor.SUCCESSFUL;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class SysQuestionnaireServiceImpl implements ISysQuestionnaireService {

  private final SysQuestionnaireAnswerMapper answerMapper;
  private final SysSubjectMapper subjectMapper;
  private final SysQuestionnaireMapper questionnaireMapper;
  private final SysSubjectOptionMapper optionMapper;
  private final SysQuestionnaireSubjectMapper questionnaireSubjectMapper;

  public SysQuestionnaireServiceImpl(
    SysQuestionnaireAnswerMapper answerMapper,
    SysSubjectMapper subjectMapper,
    SysQuestionnaireMapper questionnaireMapper,
    SysSubjectOptionMapper optionMapper,
    SysQuestionnaireSubjectMapper questionnaireSubjectMapper) {

    this.answerMapper = answerMapper;
    this.subjectMapper = subjectMapper;
    this.questionnaireMapper = questionnaireMapper;
    this.optionMapper = optionMapper;
    this.questionnaireSubjectMapper = questionnaireSubjectMapper;
  }

  /**
   * 生成一条问卷记录
   * 没有必要使用事务
   * */
  @Override
  public void createQuestionnaireByName(String questionnaireName) {
    SysQuestionnaire questionnaire = new SysQuestionnaire();
    questionnaire.setId(UUIDUtil.uuid());
    questionnaire.setCreatedAt(new Date());
    questionnaire.setStatus(QUESTIONNAIRE_AND_SUBJECT.ACTIVE_STATUS);
    questionnaire.setQuestionnaireName(questionnaireName);
    questionnaireMapper.insert(questionnaire);
  }

  /**
   * 关联问卷和题目
   * */
  @Override
  public void associateQuestionnaireWithSubject(SysQuestionnaire questionnaire, SysSubject subject) {
    String questionnaireId = questionnaire.getId();
    String subjectId = subject.getId();

    SysQuestionnaireSubject questionnaireSubject = new SysQuestionnaireSubject();
    questionnaireSubject.setId(UUIDUtil.uuid());
    questionnaireSubject.setQuestionnaireId(questionnaireId);
    questionnaireSubject.setSubjectId(subjectId);

    if(isExists(questionnaireSubject)) return;

    questionnaireSubjectMapper.insert(questionnaireSubject);
  }

  @Override
  public void batchAssociateQuestionnaireWithSubject(SysQuestionnaire questionnaire, List<SysSubject> subjects) {
    if(isExists(questionnaire, subjects)) return;

    List<SysQuestionnaireSubject> questionnaireSubjects = new ArrayList<>();
    for (SysSubject subject : subjects) {
      SysQuestionnaireSubject questionnaireSubject = new SysQuestionnaireSubject();
      questionnaireSubject.setId(UUIDUtil.uuid());
      questionnaireSubject.setQuestionnaireId(questionnaire.getId());
      questionnaireSubject.setSubjectId(subject.getId());
      questionnaireSubjects.add(questionnaireSubject);
    }

    questionnaireSubjectMapper.insertAll(questionnaireSubjects);
  }

  @Override
  public void createSubjectWithOptions(SysQuestionnaireSubject subject, List<SysQuestionnaire> options) {
    String subjectId = subject.getSubjectId();

  }

  @Override
  public QuestionnaireDto getQuestionnaireByID(String questionnaireID) {

    SysQuestionnaire sysQuestionnaire = questionnaireMapper.selectByPrimaryKey(questionnaireID);

    List<SysQuestionnaireSubject> questionnaireSubjects = questionnaireSubjectMapper.selectByQuestionnaireId(sysQuestionnaire.getId());
    if(questionnaireSubjects.size() <= 0) {
      log.info("关联条数为0， 有可能未关联题目。即将退出");
      return null;
    }


    List<String> subjectIds = questionnaireSubjects.stream().map(SysQuestionnaireSubject::getSubjectId).collect(Collectors.toList());
    List<SysSubject> subjectList = subjectMapper.selectByPrimaryKeys(subjectIds);

    if(subjectList.size() <= 0) {
      log.info("题目条数为0， 数据可能已经损坏");
      return null;
    }

    List<SubjectDto> subjectDtoList = new ArrayList<>();
    for(SysSubject subject : subjectList) {
      SubjectDto subjectDto = new SubjectDto();
      List<SysSubjectOption> options = optionMapper.selectBySubjectId(subject.getId());
      subjectDto.setSysSubject(subject);
      subjectDto.setOptions(options);
      subjectDtoList.add(subjectDto);
    }

    return new QuestionnaireDto(sysQuestionnaire.getQuestionnaireName(), subjectDtoList);
  }

  private Boolean isExists(SysQuestionnaireSubject questionnaireSubject) {
    return questionnaireSubjectMapper.isExists(questionnaireSubject) != null;
  }

  private Boolean isExists(SysQuestionnaire questionnaire, List<SysSubject> subjects) {
    QuestionnaireParam questionnaireParam = new QuestionnaireParam(questionnaire.getId(), subjects);
    return questionnaireSubjectMapper.batchIsExists(questionnaireParam).size() > 0;
  }
}
