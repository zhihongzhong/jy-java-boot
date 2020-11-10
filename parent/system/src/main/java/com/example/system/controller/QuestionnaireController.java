package com.example.system.controller;

import com.example.common.utils.ResultJSON;
import com.example.system.dto.QuestionAnswerDto;
import com.example.system.dto.questionnaire.QuestionnaireDto;
import com.example.system.service.ISysQuestionnaireService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

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
   * 获取
   * */
  @GetMapping("list/{id}")
  public ResultJSON<QuestionnaireDto> listById(@PathVariable("id") String questionnaireId) {
    return ResultJSON.<QuestionnaireDto>success().addData(questionnaireService.getQuestionnaireByID(questionnaireId));
  }

  @PostMapping("create")
  public ResultJSON<String> createQuestionnaire(String questionnaireName) {
    questionnaireService.createQuestionnaireByName(questionnaireName);
    return ResultJSON.<String>success().addData("");
  }

  @PostMapping("answer")
  public ResultJSON<String> answerQuestionnaire(@RequestBody QuestionAnswerDto questionAnswerDto) {
    return ResultJSON.success();
  }
}
