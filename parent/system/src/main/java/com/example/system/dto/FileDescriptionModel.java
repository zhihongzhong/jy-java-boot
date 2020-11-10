package com.example.system.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@ApiModel(
  value = "文件上传对象",
  description = "描述文件的属性"
)
@ToString
@Data
public class FileDescriptionModel {

  @ApiModelProperty(value = "文件作用", allowEmptyValue = false)
  private String usage;

  @ApiModelProperty(value = "文件类型, 可选 IMAGE, VOICE, VIDEO ", allowEmptyValue = false)
  private String type;

  @ApiModelProperty(value = "文件", allowEmptyValue = false)
  private MultipartFile file;
}
