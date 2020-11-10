package com.example.system.dto.questionnaire;

import com.example.system.entity.SysSubject;
import com.example.system.entity.SysSubjectOption;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import java.util.List;
import lombok.Data;

@Data
public class SubjectDto {

  @JsonUnwrapped
  private SysSubject sysSubject;
  private List<SysSubjectOption> options;
}
