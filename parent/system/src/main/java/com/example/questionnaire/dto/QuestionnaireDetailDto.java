package com.example.questionnaire.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
@AllArgsConstructor
public class QuestionnaireDetailDto {
  private String questionnaireName;

  private List<SubjectDto> subjectDtoList;
}
