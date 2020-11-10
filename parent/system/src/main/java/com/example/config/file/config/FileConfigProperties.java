package com.example.config.file.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "jy.file")
public class FileConfigProperties {

  /* 文件的 root 目录 */
  private String rootLocation = "files";

  /* 文件的基本 URL 地址 */
  private String basicUrl = "/file/";

  /* 文件类型枚举 */
  private String[] fileTypes = { "IMAGE", "VOICE", "VIDEO" };
}
