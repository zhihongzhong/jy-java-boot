package com.example.system.entity;

import lombok.Data;

import java.util.Date;

public class SysQuestionnaireAnswer {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_questionnaire_answer.id
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_questionnaire_answer.questionaire_id
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    private String questionaireId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_questionnaire_answer.questionnaire_subject_id
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    private String questionnaireSubjectId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_questionnaire_answer.user_id
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    private String userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_questionnaire_answer.answer
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    private String answer;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_questionnaire_answer.created_at
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_questionnaire_answer.update_at
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    private Date updateAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_questionnaire_answer.id
     *
     * @return the value of sys_questionnaire_answer.id
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_questionnaire_answer.id
     *
     * @param id the value for sys_questionnaire_answer.id
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_questionnaire_answer.questionaire_id
     *
     * @return the value of sys_questionnaire_answer.questionaire_id
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    public String getQuestionaireId() {
        return questionaireId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_questionnaire_answer.questionaire_id
     *
     * @param questionaireId the value for sys_questionnaire_answer.questionaire_id
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    public void setQuestionaireId(String questionaireId) {
        this.questionaireId = questionaireId == null ? null : questionaireId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_questionnaire_answer.questionnaire_subject_id
     *
     * @return the value of sys_questionnaire_answer.questionnaire_subject_id
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    public String getQuestionnaireSubjectId() {
        return questionnaireSubjectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_questionnaire_answer.questionnaire_subject_id
     *
     * @param questionnaireSubjectId the value for sys_questionnaire_answer.questionnaire_subject_id
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    public void setQuestionnaireSubjectId(String questionnaireSubjectId) {
        this.questionnaireSubjectId = questionnaireSubjectId == null ? null : questionnaireSubjectId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_questionnaire_answer.user_id
     *
     * @return the value of sys_questionnaire_answer.user_id
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_questionnaire_answer.user_id
     *
     * @param userId the value for sys_questionnaire_answer.user_id
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_questionnaire_answer.answer
     *
     * @return the value of sys_questionnaire_answer.answer
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_questionnaire_answer.answer
     *
     * @param answer the value for sys_questionnaire_answer.answer
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_questionnaire_answer.created_at
     *
     * @return the value of sys_questionnaire_answer.created_at
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_questionnaire_answer.created_at
     *
     * @param createdAt the value for sys_questionnaire_answer.created_at
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_questionnaire_answer.update_at
     *
     * @return the value of sys_questionnaire_answer.update_at
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_questionnaire_answer.update_at
     *
     * @param updateAt the value for sys_questionnaire_answer.update_at
     *
     * @mbg.generated Wed Nov 11 16:52:33 CST 2020
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}