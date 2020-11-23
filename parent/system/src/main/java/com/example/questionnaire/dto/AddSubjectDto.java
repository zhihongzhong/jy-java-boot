package com.example.questionnaire.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 新增题目Dto
 * */
@Data
@ToString
@ApiModel(
  value = "新增题目dto"
)
public class AddSubjectDto {

  @ApiModelProperty(
    value = "题目名称"
  )
  @NonNull
  private String subName;

  @ApiModelProperty(
    value = "题目类型"
  )
  @NonNull
  private int subType;

  @ApiModelProperty(
    value = "是否为空"
  )
  @NonNull
  private byte isNullable;

  @ApiModelProperty(
    value = "是否跳题"
  )
  @NonNull
  private byte isLeap;

  @ApiModelProperty(
    value = "选项列表"
  )
  @NonNull
  private List<String> options;

  @ApiModelProperty(
    value = "最小值"
  )
  @NotNull
  private int min;

  @ApiModelProperty(
    value = "最大值"
  )
  @NotNull
  private int max;
}
