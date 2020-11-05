package com.example.utils;
import java.security.MessageDigest;

/**
 * MD5 签名工具类
 * @author ZzH
 * @since 2020.11.05
 * */
public class MD5Util {

  private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5",
    "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


  public static String byteArrayToHexString(byte b[]) {
    StringBuilder resultSb = new StringBuilder();
    for (byte value : b) {
      resultSb.append(byteToHexString(value));
    }
    return resultSb.toString();
  }

  private static String byteToHexString(byte b) {
    int n = b;
    if (n < 0) {
      n += 256;
    }
    int d1 = n / 16;
    int d2 = n % 16;
    return hexDigits[d1] + hexDigits[d2];
  }

  /**
   * 公共方法： 对字符串进行签名
   * @param origin 待签名数据
   * @param charsetname 字符集
   * @return 签名
   * */
  public static String MD5Encode(String origin, String charsetname) {

    String resultString = null;
    try {
      resultString = new String(origin);
      MessageDigest md = MessageDigest.getInstance("MD5");
      if (charsetname == null || "".equals(charsetname)) {
        resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
      } else {
        resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
      }
    } catch (Exception exception) {
    }
    return resultString;
  }



}
