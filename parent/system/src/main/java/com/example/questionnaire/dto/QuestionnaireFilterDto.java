package com.example.questionnaire.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(
  value = "查询问卷DTO"
)
public class QuestionnaireFilterDto {
  private Boolean isBind;
}
