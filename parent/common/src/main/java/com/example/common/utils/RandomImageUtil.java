package com.example.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

/**
 * 生成图片验证码
 * @author ZzH
 * @since 2020.11.06
 * */
public class RandomImageUtil {

  /* 图片宽度 */
  private static final int WIDTH = 240;

  /* 图片高度 */
  private static final int HEIGHT = 120;

  /* 干扰线数量 */
  private static final int INTERFERENCE_COUNT = 50;

  /* base64 前缀 */
  private static final String BASE64_PREFIX = "data:image/jpg;base64,";

  /* 线宽 */
  private static final  int LINE_WIDTH = 2;

  /* 图片格式 */
  private static final String EXTENSION = "png";

  /* 字号 */
  private static final int FONT_SIZE = 62;

  /* 字符的向下偏移量 */
  private static final int Y_AXIS_OFFSET = (HEIGHT - FONT_SIZE) + FONT_SIZE / 2;

  /* 单个字符的间隔空间 */
  private static final int BLOCK_SIZE = WIDTH / 4;

  /* 字符的向左偏移 */
  private static final int X_AXIS_OFFSET = BLOCK_SIZE / 2 - 12;

  private static  BufferedImage bufferedImage(String randomCode) {

    final BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_BGR);
    final Graphics2D graphics2D =(Graphics2D) image.getGraphics();

    graphics2D.setColor(Color.white);
    graphics2D.fillRect(0, 0, WIDTH, HEIGHT);

    Color[] interferenceLineColors = {Color.BLACK, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.GREEN };

    final Random random = new Random();

    /* 绘制干扰线 */
    for ( int i = 0; i < INTERFERENCE_COUNT; i++ ) {
      final int randomColorIndex = random.nextInt(4);
      Color lineColor = interferenceLineColors[randomColorIndex];

      graphics2D.setColor(lineColor);

      final int x = random.nextInt(WIDTH - LINE_WIDTH - 1) + 1; // 保证画在边框之内
      final int y = random.nextInt(HEIGHT - LINE_WIDTH - 1) + 1;
      final int xl = random.nextInt(LINE_WIDTH);
      final int yl = random.nextInt(LINE_WIDTH);
      graphics2D.drawLine(x, y, x + xl, y + yl);

    }

    graphics2D.setColor(Color.BLACK);

    for( int i = 0; i < randomCode.length(); i++ ) {
      graphics2D.setFont(new Font("Times New Roman", Font.BOLD, FONT_SIZE));
      graphics2D.drawString(String.valueOf(randomCode.charAt(i)), (BLOCK_SIZE * i) + X_AXIS_OFFSET, Y_AXIS_OFFSET);
    }

    graphics2D.dispose();
    return image;
  }

  private static byte[] generate(String randomCode) throws IOException {
    BufferedImage image = bufferedImage(randomCode);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ImageIO.write(image, EXTENSION, byteArrayOutputStream);
    return byteArrayOutputStream.toByteArray();
  }

  public static String generateBase64(String randomCode) throws IOException{
    byte[] bytes = generate(randomCode);

    final String imageBase64 = Base64.getEncoder().encodeToString(bytes);
    return BASE64_PREFIX + imageBase64;
  }

}
