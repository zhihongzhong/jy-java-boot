package com.example.questionnaire.dto;

import com.example.questionnaire.entity.SysSubject;
import com.example.questionnaire.entity.SysSubjectOption;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectDto {
  @JsonUnwrapped
  private SysSubject sysSubject;
  private List<SysSubjectOption> options;
}
