package com.example.questionnaire.dto;


import lombok.Data;

@Data
public class AnswerModel {
  private String questionnaireId;
  private String subjectId;
  private String optionId;

}
