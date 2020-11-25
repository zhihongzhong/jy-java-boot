package com.example.questionnaire.controller.v1;

import com.example.common.constant.RESPONSE_STATUS;
import com.example.common.utils.ResultJSON;
import com.example.questionnaire.dto.*;
import com.example.questionnaire.entity.SysQuestionnaire;
import com.example.questionnaire.exception.*;
import com.example.questionnaire.service.ISysQuestionnaireService;
import com.example.system.exception.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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


  @ApiOperation(
    value = "新增结果",
    httpMethod = "POST"
  )
  @PostMapping("result")
  public ResultJSON<String> createResult(@Valid @RequestBody CreateResultDto resultDto) {
    questionnaireService.createResultByName(resultDto.getResultName());
    return ResultJSON.success();
  }


  @ApiOperation(
    value = "获取结果列表",
    httpMethod = "GET"
  )
  @GetMapping("result/list")
  public ResultJSON<List<ResultDto>> listResult() {
    return ResultJSON.<List<ResultDto>>success().addData(questionnaireService.getResultList());
  }

  @ApiOperation(
    value = "获取结果对应操作列表",
    httpMethod = "GET"
  )
  @GetMapping("result.option/list")
  public ResultJSON<List<ResOptionDto>> listResultOpts() {
    List<ResOptionDto> resOptionDtoList = questionnaireService.getResultOptionList();
    return ResultJSON.<List<ResOptionDto>>success().addData(resOptionDtoList);
  }

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
