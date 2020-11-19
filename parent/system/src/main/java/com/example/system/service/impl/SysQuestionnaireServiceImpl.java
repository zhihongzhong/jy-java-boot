package com.example.system.service.impl;

import com.example.common.utils.UUIDUtil;
import com.example.constant.QUESTIONNAIRE_AND_SUBJECT;
import com.example.system.dto.questionnaire.*;
import com.example.system.entity.*;
import com.example.system.entity.param.QuestionnaireAnswerParam;
import com.example.system.entity.param.QuestionnaireParam;
import com.example.system.mapper.*;
import com.example.system.service.ISysQuestionnaireService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
public class SysQuestionnaireServiceImpl implements ISysQuestionnaireService {

  private final SysQuestionnaireAnswerMapper answerMapper;
  private final SysSubjectMapper subjectMapper;
  private final SysQuestionnaireMapper questionnaireMapper;
  private final SysSubjectOptionMapper optionMapper;
  private final SysQuestionnaireSubjectMapper questionnaireSubjectMapper;
  private final SysQuestionnaireAnswerMapper questionnaireAnswerMapper;

  public SysQuestionnaireServiceImpl(
    SysQuestionnaireAnswerMapper answerMapper,
    SysSubjectMapper subjectMapper,
    SysQuestionnaireMapper questionnaireMapper,
    SysSubjectOptionMapper optionMapper,
    SysQuestionnaireSubjectMapper questionnaireSubjectMapper,
    SysQuestionnaireAnswerMapper questionnaireAnswerMapper) {

    this.answerMapper = answerMapper;
    this.subjectMapper = subjectMapper;
    this.questionnaireMapper = questionnaireMapper;
    this.optionMapper = optionMapper;
    this.questionnaireSubjectMapper = questionnaireSubjectMapper;
    this.questionnaireAnswerMapper = questionnaireAnswerMapper;
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
  public void associateQuestionnaireWithSubject(QuestionnaireAssociationDto dto) {
    String questionnaireId = dto.getQuestionnaireId();
    String subjectId = dto.getSubjectId();

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
  @Transactional
  public void createSubjectWithOptions(AddSubjectDto dto) {

    final Date now = new Date();

    SysSubject subject = new SysSubject();
    subject.setId(UUIDUtil.uuid());
    subject.setSubName(dto.getSubName());
    subject.setSubType(dto.getSubType());
    subject.setIsNullable(dto.getIsNullable());
    subject.setIsLeap(dto.getIsLeap());
    subject.setSubStatus(QUESTIONNAIRE_AND_SUBJECT.ACTIVE_STATUS);
    subject.setMin(dto.getMin());
    subject.setMax(dto.getMax());
    subject.setCreatedAt(now);
    subject.setLeapQues(null);

    List<SysSubjectOption> options = new ArrayList<>();

    for(String opt : dto.getOptions()) {
      SysSubjectOption option = new SysSubjectOption();
      option.setId(UUIDUtil.uuid());
      option.setOptionName(opt);
      option.setSubjectId(subject.getId());
      option.setCreatedAt(now);
      options.add(option);
    }

    subjectMapper.insert(subject);
    optionMapper.insertAll(options);
  }

  @Override
  public List<SubjectDto> getSubjectList() {

    List<SubjectDto> subjectDtoList = new ArrayList<>();

    List<SysSubject> subjects = subjectMapper.selectAll();

    for (SysSubject subject : subjects) {
      SubjectDto subjectDto = new SubjectDto();
      List<SysSubjectOption> options = optionMapper.selectBySubjectId(subject.getId());
      subjectDto.setOptions(options);
      subjectDto.setSysSubject(subject);
      subjectDtoList.add(subjectDto);
    }

    return subjectDtoList;
  }

  @Override
  public QuestionnaireDetailDto getQuestionnaireByID(String questionnaireID) {

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

    return new QuestionnaireDetailDto(sysQuestionnaire.getQuestionnaireName(), subjectDtoList);
  }

  /**
   * 查询所有问卷
   * */
  @Override
  public List<SysQuestionnaire> getAllQuestionnaires() {
    return questionnaireMapper.selectAll();
  }

  /**
   * 存储问卷内容
   * */
  @Override
  @Transactional
  public void save(QuestionAnswerDto answerDto, String userName) {
    String questionnaireId = answerDto.getQuestionnaireId();
    List<SysQuestionnaireSubject> questionnaireSubjects = questionnaireSubjectMapper.selectByQuestionnaireId(questionnaireId);
    Map<String,String> hashMap = questionnaireSubjects.stream().collect(Collectors.toMap(SysQuestionnaireSubject::getSubjectId, SysQuestionnaireSubject::getId));

    List<QuestionAnswerDto.Answer> answers = answerDto.getAnswers();
    List<SysQuestionnaireAnswer> questionnaireAnswers = new ArrayList<>();
    for (QuestionAnswerDto.Answer answer : answers) {
      for (String opt : answer.getOptionId()) {
        SysQuestionnaireAnswer questionnaireAnswer = new SysQuestionnaireAnswer();
        questionnaireAnswer.setId(UUIDUtil.uuid());
        questionnaireAnswer.setQuestionnaireSubjectId(hashMap.get(answer.getSubjectId()));
        questionnaireAnswer.setUserId(userName);
        questionnaireAnswer.setQuestionaireId(questionnaireId);
        questionnaireAnswer.setAnswer(opt);
        questionnaireAnswer.setCreatedAt(new Date());
        questionnaireAnswers.add(questionnaireAnswer);
      }
    }

    questionnaireAnswerMapper.insertAll(questionnaireAnswers);
  }

  @Override
  public Boolean isSubmitted(String questionnaireId, String userName) {
    try {
      QuestionnaireAnswerParam param = new QuestionnaireAnswerParam(questionnaireId, userName);
      if(questionnaireAnswerMapper.selectByQuestionnaireIdAndUserName(param) == null) {
        return false;
      }
      return true;
    }catch (Exception e) {
      log.info("查询是否提交发生错误，即将退出。 错误信息：[{}]", e.getMessage());
      return true;
    }
  }

  private Boolean isExists(SysQuestionnaireSubject questionnaireSubject) {
    return questionnaireSubjectMapper.isExists(questionnaireSubject) != null;
  }

  private Boolean isExists(SysQuestionnaire questionnaire, List<SysSubject> subjects) {
    QuestionnaireParam questionnaireParam = new QuestionnaireParam(questionnaire.getId(), subjects);
    return questionnaireSubjectMapper.batchIsExists(questionnaireParam).size() > 0;
  }
}
