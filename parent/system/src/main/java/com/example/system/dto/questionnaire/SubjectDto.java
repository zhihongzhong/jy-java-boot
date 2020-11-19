package com.example.system.dto.questionnaire;

import com.example.system.entity.SysSubject;
import com.example.system.entity.SysSubjectOption;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
