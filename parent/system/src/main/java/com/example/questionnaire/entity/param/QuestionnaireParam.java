package com.example.questionnaire.entity.param;

import com.example.questionnaire.entity.SysSubject;
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
