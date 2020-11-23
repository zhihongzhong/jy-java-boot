package com.example.questionnaire.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionnaireAnswerParam {
  private String questionnaireId;

  private String userName;

}
