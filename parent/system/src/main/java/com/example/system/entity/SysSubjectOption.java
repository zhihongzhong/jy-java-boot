package com.example.system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class SysSubjectOption {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject_option.id
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject_option.subject_id
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String subjectId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject_option.option_name
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    private String optionName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject_option.created_at
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject_option.update_at
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date updateAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject_option.id
     *
     * @return the value of sys_subject_option.id
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject_option.id
     *
     * @param id the value for sys_subject_option.id
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject_option.subject_id
     *
     * @return the value of sys_subject_option.subject_id
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject_option.subject_id
     *
     * @param subjectId the value for sys_subject_option.subject_id
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject_option.option_name
     *
     * @return the value of sys_subject_option.option_name
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    public String getOptionName() {
        return optionName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject_option.option_name
     *
     * @param optionName the value for sys_subject_option.option_name
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    public void setOptionName(String optionName) {
        this.optionName = optionName == null ? null : optionName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject_option.created_at
     *
     * @return the value of sys_subject_option.created_at
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject_option.created_at
     *
     * @param createdAt the value for sys_subject_option.created_at
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject_option.update_at
     *
     * @return the value of sys_subject_option.update_at
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject_option.update_at
     *
     * @param updateAt the value for sys_subject_option.update_at
     *
     * @mbg.generated Tue Nov 10 11:52:51 CST 2020
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}