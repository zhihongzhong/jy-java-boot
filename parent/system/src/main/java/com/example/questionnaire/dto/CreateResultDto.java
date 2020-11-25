package com.example.questionnaire.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
@ApiModel(
  value = "创建结果DTO"
)
public class CreateResultDto {
  @NotNull
  @ApiModelProperty(
    value = "结果名称"
  )
  private String resultName;

  @NotNull
  @ApiModelProperty(
    value = "option id"
  )
  private String optionId;
}
