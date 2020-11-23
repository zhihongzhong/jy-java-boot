package com.example.questionnaire.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Data
@ApiModel(
  value = "开始答题接口返回数据"
)
public class AnswerDto {

  @ApiModelProperty(
    value = "题目ID"
  )
  private String subjectId;
  @ApiModelProperty(
    value = "题目名称"
  )
  private String subjectName;

  @ApiModelProperty(
    value = "选项列表"
  )
  private List<Option> options;

  @Data
  public static class Option {
    private String optionId;
    private String optionName;
  }
}
