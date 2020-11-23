package com.example.questionnaire.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
@Data
@ApiModel(
  value = "创建数据库对象"
)
public class CreateQuestionnaireDto {
  @NotNull
  private String questionnaireName;
}
