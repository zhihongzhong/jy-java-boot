package com.example.questionnaire.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class SysSubject {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject.id
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject.sub_name
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    private String subName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject.sub_type
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    private Integer subType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject.sub_status
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    private Byte subStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject.is_nullable
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    private Byte isNullable;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject.is_leap
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    private Byte isLeap;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject.leap_ques
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    private String leapQues;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject.min
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    private Integer min;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject.max
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    private Integer max;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject.created_at
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date createdAt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_subject.update_at
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Date updateAt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject.id
     *
     * @return the value of sys_subject.id
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject.id
     *
     * @param id the value for sys_subject.id
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject.sub_name
     *
     * @return the value of sys_subject.sub_name
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public String getSubName() {
        return subName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject.sub_name
     *
     * @param subName the value for sys_subject.sub_name
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public void setSubName(String subName) {
        this.subName = subName == null ? null : subName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject.sub_type
     *
     * @return the value of sys_subject.sub_type
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public Integer getSubType() {
        return subType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject.sub_type
     *
     * @param subType the value for sys_subject.sub_type
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject.sub_status
     *
     * @return the value of sys_subject.sub_status
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public Byte getSubStatus() {
        return subStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject.sub_status
     *
     * @param subStatus the value for sys_subject.sub_status
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public void setSubStatus(Byte subStatus) {
        this.subStatus = subStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject.is_nullable
     *
     * @return the value of sys_subject.is_nullable
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public Byte getIsNullable() {
        return isNullable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject.is_nullable
     *
     * @param isNullable the value for sys_subject.is_nullable
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public void setIsNullable(Byte isNullable) {
        this.isNullable = isNullable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject.is_leap
     *
     * @return the value of sys_subject.is_leap
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public Byte getIsLeap() {
        return isLeap;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject.is_leap
     *
     * @param isLeap the value for sys_subject.is_leap
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public void setIsLeap(Byte isLeap) {
        this.isLeap = isLeap;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject.leap_ques
     *
     * @return the value of sys_subject.leap_ques
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public String getLeapQues() {
        return leapQues;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject.leap_ques
     *
     * @param leapQues the value for sys_subject.leap_ques
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public void setLeapQues(String leapQues) {
        this.leapQues = leapQues == null ? null : leapQues.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject.min
     *
     * @return the value of sys_subject.min
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public Integer getMin() {
        return min;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject.min
     *
     * @param min the value for sys_subject.min
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public void setMin(Integer min) {
        this.min = min;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject.max
     *
     * @return the value of sys_subject.max
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public Integer getMax() {
        return max;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject.max
     *
     * @param max the value for sys_subject.max
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public void setMax(Integer max) {
        this.max = max;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject.created_at
     *
     * @return the value of sys_subject.created_at
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject.created_at
     *
     * @param createdAt the value for sys_subject.created_at
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_subject.update_at
     *
     * @return the value of sys_subject.update_at
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_subject.update_at
     *
     * @param updateAt the value for sys_subject.update_at
     *
     * @mbg.generated Tue Nov 10 14:48:21 CST 2020
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}