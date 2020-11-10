package com.example.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;

@Data
@ToString
@ApiModel(
  value = "问卷题目",
  description = "提交问卷题目"
)
public class SubjectModel {

  @NotNull
  private String subjectName;

  @NotNull
  @DecimalMin(value = "1", message = "数值必须为1到4的数字")
  @DecimalMax(value = "4", message = "数值必须为1到4的数字")
  @ApiModelProperty(value = "题目类型", allowEmptyValue = false)
  private int subjectType;

  @DecimalMin(value = "1", message = "数值必须为1和2的数字")
  @DecimalMax(value = "2", message = "数值必须为1和2的数字")
  @ApiModelProperty(value = "答案能否为空, 默认为1（不为空", allowEmptyValue = true)
  private int isNullable = 1;

  @DecimalMin(value = "1", message = "数值必须为1和2的数字")
  @DecimalMax(value = "2", message = "数值必须为1和2的数字")
  @ApiModelProperty(value = "是否跳题， 默认为2（不跳）", allowEmptyValue = true)
  private int isLeap = 2;


}
