package com.example.questionnaire.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;


/**
 * 关联问卷dto
 * @author ZzH
 * @since 2020.11.13
 * */
@ToString
@Data
@ApiModel(
  value = "关联问卷DTO"
)
public class QuestionnaireAssociationDto {

  @ApiModelProperty(
    value = "问卷ID",
    allowEmptyValue = false
  )
  @NotNull
  private String questionnaireId;

  @ApiModelProperty(
    value = "主题ID",
    allowEmptyValue = false
  )
  @NotNull
  private String subjectId;
}
