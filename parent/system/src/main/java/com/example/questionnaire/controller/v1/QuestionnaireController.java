package com.example.questionnaire.controller.v1;

import com.example.common.constant.RESPONSE_STATUS;
import com.example.common.utils.ResultJSON;
import com.example.constant.QUESTIONNAIRE_AND_SUBJECT;
import com.example.questionnaire.dto.*;
import com.example.questionnaire.entity.SysQuestionnaire;
import com.example.questionnaire.entity.SysSubjectOption;
import com.example.questionnaire.exception.*;
import com.example.system.exception.ApiException;
import com.example.questionnaire.service.ISysQuestionnaireService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sys/admin/questionnaire")
@Api(
  tags = "问卷接口"
)
public class QuestionnaireController {

  private final ISysQuestionnaireService questionnaireService;
  public QuestionnaireController(ISysQuestionnaireService questionnaireService) {
    this.questionnaireService = questionnaireService;
  }

  /**
   * 获取问卷0
   * */
  @ApiOperation(
    value = "获取问卷详情",
    httpMethod = "GET"
  )
  @GetMapping("list/{id}")
  public ResultJSON<QuestionnaireDetailDto> listById(@PathVariable("id") String questionnaireId) {
    return ResultJSON.<QuestionnaireDetailDto>success().addData(questionnaireService.getQuestionnaireByID(questionnaireId));
  }

  @ApiOperation(
    value = "获取问卷列表",
    httpMethod = "GET"
  )
  @GetMapping("list")
  public ResultJSON<List<SysQuestionnaire>> listAllQuestionnaire() {
    List<SysQuestionnaire> questionnaires = questionnaireService.getAllQuestionnaires();
    return ResultJSON.<List<SysQuestionnaire>>success().addData(questionnaires);
  }

  /**
   * 创建问卷
   * */
  @ApiOperation(
    value = "创建一个问卷",
    httpMethod = "POST"
  )
  @PostMapping("create")
  public ResultJSON<String> createQuestionnaire(@RequestBody CreateQuestionnaireDto createQuestionnaireDto) {
    final String questionnaireName = createQuestionnaireDto.getQuestionnaireName();
    questionnaireService.createQuestionnaireByName(questionnaireName);
    return ResultJSON.<String>success().addData("");
  }

  /**
   * 回答问卷
   * */
//  @ApiOperation(
//    value = "提交问卷",
//    httpMethod = "POST"
//  )
//  @PostMapping("answer")
//  public ResultJSON<String> answerQuestionnaire(
//    @RequestBody @Valid QuestionAnswerDto questionAnswerDto, BindingResult errors
//  ) throws QuestionnaireNotFoundException, SubjectNotFoundException, OptionNotFoundException,
//    QuestionnaireBrokenFormatException, AlreadySubmittedException {
//
//    if(errors.hasErrors()) {
//      throw new QuestionnaireBrokenFormatException();
//    }
//
//
//    String questionnaireId = questionAnswerDto.getQuestionnaireId();
//    String username = SecurityContextHolder.getContext().getAuthentication().getName();
//
//    if(questionnaireService.isSubmitted(questionnaireId, username)) {
//      throw new AlreadySubmittedException();
//    }
//
//    QuestionnaireDetailDto detailDto =
//      questionnaireService.getQuestionnaireByID(questionnaireId);
//
//    /* 问卷未找到， 抛出异常 */
//    if(detailDto == null) {
//      throw new QuestionnaireNotFoundException();
//    }
//
//    /* 进入数据验证模块 */
//    Map<String, List<String>> hashMap = questionAnswerDto
//      .getAnswers()
//      .stream()
//      .collect(Collectors.toMap(QuestionAnswerDto.Answer::getSubjectId, QuestionAnswerDto.Answer::getOptionId));
//
//    @SuppressWarnings(value = "all")
//    Map<String, String> extraValueMap = questionAnswerDto
//      .getAnswers()
//      .stream()
//      .collect(Collectors.toMap(QuestionAnswerDto.Answer::getSubjectId, QuestionAnswerDto.Answer::getValue));
//
//
//    List<SubjectDto> subjectDtoList = detailDto.getSubjectDtoList();
//
//    /* 判断答题列表是否完整 */
//    for (SubjectDto subjectDto : subjectDtoList) {
//      if (
//        !hashMap.containsKey(subjectDto.getSysSubject().getId()) &&
//        !subjectDto.getSysSubject().getIsNullable().equals(QUESTIONNAIRE_AND_SUBJECT.NULLABLE) // 如果每查到当前题目且该题不可跳过
//      ) {
//        throw new SubjectNotFoundException(); // 抛出异常
//      }
//    }
//
//
//    /* 判断选项是否在问题中 */
//    for (SubjectDto subjectDto : subjectDtoList ) {
//      List<String> subjectOptions = subjectDto.getOptions().stream().map(SysSubjectOption::getId).collect(Collectors.toList());
//      List<String> options = hashMap.get(subjectDto.getSysSubject().getId());
//      String value = extraValueMap.get(subjectDto.getSysSubject().getId());
//
//      if (options == null && !subjectDto.getSysSubject().getIsNullable().equals(QUESTIONNAIRE_AND_SUBJECT.NULLABLE)) { // 如果当前题目不可跳过且选项为空
//        throw new OptionNotFoundException();
//      }
//
//      if (options != null) {  // 如果 options 不为空
//        for (String option : options) {
//          if(!subjectOptions.contains(option)) {
//            throw new OptionNotFoundException(); // 如果选项不在题目列表中
//          }
//        }
//
//        /* 针对不同类型的题目进行判断 */
//        /* 目前主要校验单选题 */
//        switch (subjectDto.getSysSubject().getSubType()) {
//          case QUESTIONNAIRE_AND_SUBJECT.QUESTION_OPTION: // 单选题
//            if(options.size() != 1) throw new OptionNotFoundException();
//            break;
//          case QUESTIONNAIRE_AND_SUBJECT.QUESTION_CHECKOUT: // 多选题
//            if(options.size() < 1) throw new OptionNotFoundException();
//            break;
//          case QUESTIONNAIRE_AND_SUBJECT.QUESTION_RATE: // 打分题
//            final Integer rate = Integer.getInteger(value);
//            final Integer min = subjectDto.getSysSubject().getMin();
//            final Integer max = subjectDto.getSysSubject().getMax();
//
//            if(rate < min || rate > max) {
//              throw new OptionNotFoundException();
//            }
//            break;
//          default:
//            break;
//        } // SWITCH
//      } // IF
//
//    } // FOR
//
//    /* 结束数据验证模块 */
//    /* 保存问答报文 */
//    questionnaireService.save(questionAnswerDto, username);
//    return ResultJSON.<String>success().addData("");
//  }


  @ApiOperation(
    value = "获取回答的问卷",
    httpMethod = "GET"
  )
  @GetMapping("answer/{questionnaireId}")
  public ResultJSON<String> getAnswers(@PathVariable("questionnaireId") String questionnaireId) {
    return ResultJSON.<String>success();
  }

  @ApiOperation(
    value = "获取题目池",
    httpMethod = "GET"
  )
  @GetMapping("list/subject/pool")
  public ResultJSON<List<SubjectDto>> getSubjectPool() {
    List<SubjectDto> subjectDtoList = questionnaireService.getSubjectList();
    return ResultJSON.<List<SubjectDto>>success().addData(subjectDtoList);
  }


  @ApiOperation(
    value = "关联题库",
    httpMethod = "POST"
  )
  @PostMapping("/association")
  public ResultJSON<String> association(@Valid @RequestBody AssociationDto dto) throws AlreadySubmittedException {
    questionnaireService.associateQuestionnaireWithSubject(dto);
    return ResultJSON.success();
  }

  @ApiOperation(
    value = "新增题目",
    httpMethod = "POST"
  )
  @PostMapping("subject")
  public ResultJSON<String> createSubject(@Valid @RequestBody AddSubjectDto dto)
  throws ApiException {
    if(dto.getOptions().size() <= 0) {
      throw new ApiException(RESPONSE_STATUS.QUESTIONNAIRE_EMPTY_OPTIONS);
    }

    questionnaireService.createSubjectWithOptions(dto);
    return ResultJSON.success();
  }

  @ApiOperation(
    value = "开始答题",
    httpMethod = "GET"
  )
  @GetMapping("beforeAnswer/{id}")
  public ResultJSON<AnswerDto> beforeAnswer(@PathVariable("id") String questionnaireId ) throws QuestionnaireNotFoundException {
    AnswerDto answerDto = questionnaireService.getBeforeAnswerDto(questionnaireId);
    return ResultJSON.<AnswerDto>success().addData(answerDto);
  }

  @ApiOperation(
    value = "提交回答并且获取下一题",
    httpMethod = "POST"
  )
  @PostMapping("answer")
  public ResultJSON<AnswerDto> answerAndGetNext(@RequestBody AnswerModel answerModel) throws QuestionnaireNotFoundException {
    AnswerDto answerDto = questionnaireService.getNextQuestion(answerModel);
    return ResultJSON.<AnswerDto>success().addData(answerDto);
  }

  @ExceptionHandler(QuestionnaireNotFoundException.class)
  public ResultJSON<String> handleQuestionnaireNotFoundException() {
    return ResultJSON.fail(RESPONSE_STATUS.QUESTIONNAIRE_NOT_FOUND);
  }

  @ExceptionHandler(SubjectNotFoundException.class)
  public ResultJSON<String> handleSubjectNotFoundException() {
    return ResultJSON.fail(RESPONSE_STATUS.SUBJECT_NOT_FOUND);
  }

  @ExceptionHandler(QuestionnaireBrokenFormatException.class)
  public ResultJSON<String> handleQuestionnaireBrokenFormatException() {
    return ResultJSON.fail(RESPONSE_STATUS.QUESTIONNAIRE_FORMAT_BROKEN);
  }

  @ExceptionHandler(OptionNotFoundException.class)
  public ResultJSON<String> handleOptionNotFoundException() {
    return ResultJSON.fail(RESPONSE_STATUS.QUESTIONNAIRE_EMPTY_OPTION);
  }

  @ExceptionHandler(AlreadySubmittedException.class)
  public ResultJSON<String> handleAlreadySubmittedException() {
    return ResultJSON.fail(RESPONSE_STATUS.QUESTIONNAIRE_ALREADY_SUBMITTED);
  }

  @ExceptionHandler(ApiException.class)
  public ResultJSON<String> handleApiException(ApiException e) {
    return ResultJSON.fail(e.getStatus());
  }
}
