package com.example.system.dto.questionnaire;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(
  value = "查询问卷DTO"
)
public class QuestionnaireFilterDto {
  private Boolean isBind;
}
