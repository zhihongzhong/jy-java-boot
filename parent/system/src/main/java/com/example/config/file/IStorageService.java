package com.example.config.file;

import com.example.config.file.dto.FileInformationDto;
import com.example.config.file.exception.FileTypeNotSupportException;
import com.example.system.dto.FileDescriptionModel;

import java.io.IOException;
import java.util.List;
/**
 * 文件服务接口
 * */
public interface IStorageService {

  FileInformationDto storeOne(FileDescriptionModel fileDescriptionModel) throws FileTypeNotSupportException, IOException;

  List<FileInformationDto> listAll();
}
