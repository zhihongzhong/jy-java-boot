package com.example.common.utils;

public class UUIDUtil {
  public static String uuid() {
    return java.util.UUID.randomUUID().toString().replace("-","");
  }
}
