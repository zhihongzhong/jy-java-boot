package com.example.questionnaire.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(
  value = "预设操作"
)
public class ResOptionDto {
  @ApiModelProperty(value = "id")
  private String id;
  @ApiModelProperty(value = "选项名称")
  private String optionName;
}
