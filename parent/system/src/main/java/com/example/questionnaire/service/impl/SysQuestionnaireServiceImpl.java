package com.example.questionnaire.service.impl;

import cn.hutool.core.date.DateTime;
import com.example.common.utils.UUIDUtil;
import com.example.constant.QUESTIONNAIRE_AND_SUBJECT;
import com.example.questionnaire.dto.*;
import com.example.questionnaire.entity.*;
import com.example.questionnaire.entity.param.QuestionnaireAnswerParam;
import com.example.questionnaire.entity.param.QuestionnaireParam;
import com.example.questionnaire.exception.AlreadySubmittedException;
import com.example.questionnaire.exception.QuestionnaireNotFoundException;
import com.example.questionnaire.mapper.*;
import com.example.questionnaire.service.ISysQuestionnaireService;
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
  private final SysResultMapper resultMapper;
  private final SysResOptionMapper resOptionMapper;

  public SysQuestionnaireServiceImpl(
    SysQuestionnaireAnswerMapper answerMapper,
    SysSubjectMapper subjectMapper,
    SysQuestionnaireMapper questionnaireMapper,
    SysSubjectOptionMapper optionMapper,
    SysQuestionnaireSubjectMapper questionnaireSubjectMapper,
    SysQuestionnaireAnswerMapper questionnaireAnswerMapper,
    SysResultMapper resultMapper,
    SysResOptionMapper resOptionMapper
    ) {

    this.answerMapper = answerMapper;
    this.subjectMapper = subjectMapper;
    this.questionnaireMapper = questionnaireMapper;
    this.optionMapper = optionMapper;
    this.questionnaireSubjectMapper = questionnaireSubjectMapper;
    this.questionnaireAnswerMapper = questionnaireAnswerMapper;
    this.resultMapper = resultMapper;
    this.resOptionMapper = resOptionMapper;
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
  public void associateQuestionnaireWithSubject(AssociationDto dto) throws AlreadySubmittedException {

    final String questionnaireId = dto.getQuestionnaireId();

    if(isExists(questionnaireId)) {
      throw new AlreadySubmittedException();
    }

    List<SysQuestionnaireSubject> questionnaireSubjects = new ArrayList<>();

    for (AssociationDto.QuestionDto questionDto : dto.getQuestionDtoList()) {

      SysQuestionnaireSubject questionnaireSubject = new SysQuestionnaireSubject();
      questionnaireSubject.setId(UUIDUtil.uuid());
      questionnaireSubject.setQuestionnaireId(dto.getQuestionnaireId());
      questionnaireSubject.setSubjectId(questionDto.getQuestionId());
      questionnaireSubject.setToSubjectId(questionDto.getToQuestionId());
      questionnaireSubject.setOptionId(questionDto.getOptionId());
      questionnaireSubject.setIsRoot(questionDto.getIsRoot());
      questionnaireSubject.setIsLeaf(questionDto.getToQuestionisLeaf());

      questionnaireSubjects.add(questionnaireSubject);
    }
    questionnaireSubjectMapper.insertAll(questionnaireSubjects);
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
  public void createResultByName(String name) {
    SysResult result = new SysResult();
    result.setId(UUIDUtil.uuid());
    result.setResultName(name);
    result.setStatus(QUESTIONNAIRE_AND_SUBJECT.ACTIVE_STATUS);
    result.setCreatedAt(DateTime.now());
    result.setUpdateAt(DateTime.now());
    resultMapper.insert(result);
  }

  @Override
  public void createResultByResultDto(CreateResultDto resultDto) {

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
  public List<ResultDto> getResultList() {
    List<SysResult> results = resultMapper.selectAll();
    List<ResultDto> resultDtos = new ArrayList<>();
    for(SysResult result: results) {
      ResultDto resultDto = new ResultDto();
      resultDto.setId(result.getId());
      resultDto.setResultName(result.getResultName());
      resultDtos.add(resultDto);
    }
    return resultDtos;
  }

  @Override
  public List<ResOptionDto> getResultOptionList() {
    List<SysResOption> sysResOptions = resOptionMapper.selectAll();

    List<ResOptionDto> resOptionDtoList = new ArrayList<>();

    for(SysResOption option : sysResOptions) {
      ResOptionDto optionDto = new ResOptionDto();
      optionDto.setId(option.getId());
      optionDto.setOptionName(option.getResOptionName());
      resOptionDtoList.add(optionDto);
    }

    return resOptionDtoList;
  }

  @Override
  public QuestionnaireDetailDto getQuestionnaireByID(String questionnaireID) {

    SysQuestionnaire sysQuestionnaire = questionnaireMapper.selectByPrimaryKey(questionnaireID);

    return new QuestionnaireDetailDto(sysQuestionnaire.getQuestionnaireName(), null);
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

  /**
   *  开始答题之前通过问卷ID 拿到第一道题目
   **/
  @Override
  public AnswerDto getBeforeAnswerDto(String questionnaireId) throws QuestionnaireNotFoundException {
    if(questionnaireSubjectMapper.isExists(questionnaireId) == null) {
      throw new QuestionnaireNotFoundException();
    }

    SysQuestionnaireSubject questionnaireSubject = questionnaireSubjectMapper.selectOneByQuestionnaireIdAndIsRoot(questionnaireId);
    return getAnswerDtoBySubjectId(questionnaireSubject.getSubjectId());
  }

  @Override
  public AnswerDto getNextQuestion(AnswerModel answerModel) throws QuestionnaireNotFoundException {
    if(questionnaireSubjectMapper.isExists(answerModel.getQuestionnaireId()) == null) {
      throw new QuestionnaireNotFoundException();
    }

    SysQuestionnaireSubject questionnaireSubject = questionnaireSubjectMapper.selectOneByAnswerModel(answerModel);
    log.info(questionnaireSubject);

    // 说明下一道题目是叶节点
    if(questionnaireSubject.getIsLeaf() == QUESTIONNAIRE_AND_SUBJECT.IS_LEAF) {
      SysResult result =  resultMapper.selectByPrimaryKey(questionnaireSubject.getToSubjectId());
      AnswerDto answerDto = new AnswerDto();
      answerDto.setSubjectId(result.getId());
      answerDto.setSubjectName(result.getResultName());
      answerDto.setHasNext(Boolean.FALSE);
      answerDto.setOptions(new ArrayList<>());
      return answerDto;
    }

    return getAnswerDtoBySubjectId(questionnaireSubject.getToSubjectId());
  }

  /**
   * 根据 subject id 返回 AnswerDTO
   * */
  /* 这个方法被上面两个方法调用，所以将公共部分抽出形成新的方法 */
  private AnswerDto getAnswerDtoBySubjectId(String subjectId) throws QuestionnaireNotFoundException  {
    SysSubject subject = subjectMapper.selectByPrimaryKey(subjectId);
    if(subject == null) {
      throw new  QuestionnaireNotFoundException();
    }
    List<AnswerDto.Option> options = optionMapper.selectBySubjectId(subject.getId())
      .stream()
      .map(sysSubjectOption -> {
        AnswerDto.Option opt = new AnswerDto.Option();
        opt.setOptionId(sysSubjectOption.getId());
        opt.setOptionName(sysSubjectOption.getOptionName());
        return opt;
      })
      .collect(Collectors.toList());

    AnswerDto beforeAnswerDto = new AnswerDto();
    beforeAnswerDto.setSubjectId(subject.getId());
    beforeAnswerDto.setOptions(options);
    beforeAnswerDto.setHasNext(Boolean.TRUE);
    beforeAnswerDto.setSubjectName(subject.getSubName());
    return beforeAnswerDto;
  }

  private Boolean isExists(String questionnaireId) {
    return questionnaireSubjectMapper.isExists(questionnaireId).size() > 0;
  }

  private Boolean isExists(SysQuestionnaire questionnaire, List<SysSubject> subjects) {
    QuestionnaireParam questionnaireParam = new QuestionnaireParam(questionnaire.getId(), subjects);
    return questionnaireSubjectMapper.batchIsExists(questionnaireParam).size() > 0;
  }
}
