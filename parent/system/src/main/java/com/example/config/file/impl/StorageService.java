package com.example.config.file.impl;

import cn.hutool.core.date.DateUtil;
import com.example.common.utils.MD5Util;
import com.example.common.utils.UUIDUtil;
import com.example.config.file.IStorageService;
import com.example.config.file.config.FileConfigProperties;
import com.example.config.file.dto.FileInformationDto;
import com.example.config.file.exception.FileTypeNotSupportException;
import com.example.constant.IMAGE_EXTENSIONS;
import com.example.system.dto.FileDescriptionModel;
import com.example.system.entity.LocalFile;
import com.example.system.mapper.LocalFileMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class StorageService implements IStorageService {

  private final FileConfigProperties fileConfigProperties;
  private final LocalFileMapper localFileMapper;

  public StorageService(FileConfigProperties fileConfigProperties, LocalFileMapper localFileMapper) {
    this.fileConfigProperties = fileConfigProperties;
    this.localFileMapper = localFileMapper;
  }

  /**
   *  根据日期创建文件夹, 并返回文件夹实例
   **/
  private Path genWorkDirByDate(String fileType, String fileName) throws IOException {
    String now = DateUtil.today();

    Path path = Paths.get(this.fileConfigProperties.getRootLocation(), fileType.toLowerCase());
    path = path.resolve(now);

    log.info("生成目录: [{}]", path.toAbsolutePath().toString());
    if(Files.notExists(path.toAbsolutePath())) {
      log.info("当前目录不存在，即将创建目录: [{}]", path.toString());
      Files.createDirectories(path.toAbsolutePath());
    }

     return path.resolve(fileName);
  }

  private String genFileName(String originalFileName, String extension) {
    return MD5Util.MD5Encode(originalFileName + DateUtil.now(), "") + "." + extension;
  }

  /**
   * 保存文件方法
   * */
  @Override
  @Transactional
  public FileInformationDto storeOne(FileDescriptionModel fileDescriptionModel)
    throws FileTypeNotSupportException, IOException {
    String fileType = fileDescriptionModel.getType();

    log.info("进入文件存储方法");
    if(!Arrays.asList(fileConfigProperties.getFileTypes()).contains(fileType)) {
      throw new FileTypeNotSupportException();
    }

    MultipartFile multipartFile = fileDescriptionModel.getFile();

    if(multipartFile.isEmpty()) {
      log.info("文件为空， 即将退出");
      throw new FileTypeNotSupportException();
    }

    final String originalFilename = multipartFile.getOriginalFilename();
    final String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());

    if(validate(fileType, extension)) {
      throw new FileTypeNotSupportException();
    }

    String serverFileName = genFileName(originalFilename, extension);


    Path path = genWorkDirByDate(fileType, serverFileName);

    Path absolutePath = path.toAbsolutePath();
    String fileId = UUIDUtil.uuid();
    multipartFile.transferTo(absolutePath.toFile());

    LocalFile localFile = new LocalFile();

    String uri = "/" + path.toString().replace("\\", "/");

    localFile.setId(fileId);
    localFile.setFileName(path.toString());
    localFile.setUrl(uri);
    localFileMapper.insert(localFile);
    return new FileInformationDto(uri);
  }

  @Override
  public List<FileInformationDto> listAll() {
    return localFileMapper
      .selectAll()
      .stream()
      .map(StorageService::localFileToFileInformation)
      .collect(Collectors.toList());
  }

  private static FileInformationDto localFileToFileInformation(LocalFile localFile) {
    return new FileInformationDto(localFile.getUrl());
  }

  private Boolean validate(String fileType, String extension) {
    if(fileType.equals("IMAGE")) {
      return IMAGE_EXTENSIONS.contains(extension);
    }
    return false;
  }
}
