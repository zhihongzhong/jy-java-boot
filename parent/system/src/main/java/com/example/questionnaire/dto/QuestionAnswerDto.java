package com.example.questionnaire.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import reactor.util.annotation.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ToString
@ApiModel(
  value = "答卷dto",
  description = "答卷dto"
)
public class QuestionAnswerDto {

  @NotNull
  @ApiModelProperty(
    value = "问卷ID",
    allowEmptyValue = false
  )
  private String questionnaireId;

  @Valid
  @ApiModelProperty(
    value = "回答列表",
    allowEmptyValue = false
  )
  private List<Answer> answers;


  @Data
  @ToString
  public static class Answer {

    @NotNull
    @ApiModelProperty(
      value = "题目ID",
      allowEmptyValue = false
    )
    private String subjectId;

    @NotNull
    @ApiModelProperty(
      value = "选项ID",
      allowEmptyValue = false
    )
    private List<String> optionId;

    @Nullable
    @ApiModelProperty(
      value = "额外值",
      allowEmptyValue = true
    )
    private String value = "";
  }
}
