package com.example.system.dto.questionnaire;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
@AllArgsConstructor
public class QuestionnaireDto {
  private String questionnaireName;

  private List<SubjectDto> subjectDtoList;
}
