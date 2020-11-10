package com.example.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import reactor.util.annotation.Nullable;

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

  @NotNull
  @ApiModelProperty(
    value = "回答列表",
    allowEmptyValue = false
  )
  private List<Answer> answers;


  @Data
  @ToString
  private static class Answer {

    @ApiModelProperty(
      value = "题目ID",
      allowEmptyValue = false
    )
    private String subjectId;

    @ApiModelProperty(
      value = "选项ID",
      allowEmptyValue = false
    )
    private String optionId;

    @Nullable
    @ApiModelProperty(
      value = "额外值",
      allowEmptyValue = true
    )
    private String value;
  }
}
