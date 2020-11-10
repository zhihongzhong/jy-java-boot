package com.example.constant;


/**
 * 图片后缀枚举
 * @author ZzH
 * @since 2020.11.09
 * */
public enum  IMAGE_EXTENSIONS {
  IMAGE_JPG("jpg"),
  IMAGE_JPEG("jpeg"),
  IMAGE_PNG("png");
  
  private String extension;

  IMAGE_EXTENSIONS(String extension) {
    this.extension = extension;
  }

  public static Boolean contains(String extension) {
    for(IMAGE_EXTENSIONS isContain : IMAGE_EXTENSIONS.values()) {
      if(isContain.extension.equals(extension)) return true;
    }
    return false;
  }
}
