package com.example.questionnaire.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(
  value = "关联问卷题目DTO",
  description = "后台提交关联的时候使用"
)
public class AssociationDto {

  @ApiModelProperty(
    value = "问卷ID"
  )
  @NotNull
  private String questionnaireId;

  @Valid
  List<QuestionDto> questionDtoList;

  @Data
  public static class QuestionDto {
    @NotNull
    private String questionId;
    @NotNull
    private String optionId;
    @NotNull
    private String toQuestionId = "";
    @NotNull
    private Integer isRoot;
  }

}
