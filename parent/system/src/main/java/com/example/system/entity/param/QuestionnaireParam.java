package com.example.system.entity.param;

import com.example.system.entity.SysSubject;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
/**
 * 批量查询
 * */
@Data
@AllArgsConstructor
public class QuestionnaireParam {
  private String questionnaireId;
  private List<SysSubject> subjects;
}
