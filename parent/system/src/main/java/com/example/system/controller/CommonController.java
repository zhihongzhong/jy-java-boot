package com.example.system.controller;


import com.example.common.constant.RESPONSE_STATUS;
import com.example.common.utils.ResultJSON;
import com.example.config.file.IStorageService;
import com.example.config.file.dto.FileInformationDto;
import com.example.config.file.exception.FileTypeNotSupportException;
import com.example.constant.IMAGE_EXTENSIONS;
import com.example.system.dto.FileDescriptionModel;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/sys/common")
@Api(
  tags = "通用控制器"
)
public class CommonController {

  @Autowired
  private IStorageService storageService;

  private Boolean validateExtension(String extension) {
    return IMAGE_EXTENSIONS.contains(extension);
  }

  /**
   * 上传图片
   * */
  @PostMapping("upload/image")
  public ResultJSON<FileInformationDto> uploadFile(FileDescriptionModel fileDescriptionModel)
    throws IOException, FileTypeNotSupportException {
    FileInformationDto fileInformationDto = storageService.storeOne(fileDescriptionModel);
    return ResultJSON.<FileInformationDto>success().addData(fileInformationDto);
  }

  /**
   * 列出图片
   * */
  @GetMapping("upload/image")
  public ResultJSON<List<FileInformationDto>> listFile() {
    List<FileInformationDto> fileInformationDtoList =
      storageService.listAll();

    return ResultJSON.<List<FileInformationDto>>success().addData(fileInformationDtoList);
  }
  /**
   * 处理 IO 异常
   */
  @ExceptionHandler(IOException.class)
  public ResultJSON<String> handleIOException(IOException e) {
    log.info(e.getMessage());
    return ResultJSON.fail(RESPONSE_STATUS.IO_EXCEPTION);
  }

  /**
   * 文件类型不支持异常
   * */
  @ExceptionHandler(FileTypeNotSupportException.class)
  public ResultJSON<String> handleFileTypeNotSupportException() {
    return ResultJSON.fail(RESPONSE_STATUS.FILE_TYPE_NOT_SUPPORT);
  }
}
