package com.example.questionnaire.entity;

public class sysResOptionResult {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_res_option_result.id
     *
     * @mbg.generated Mon Nov 23 16:36:26 CST 2020
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_res_option_result.option_id
     *
     * @mbg.generated Mon Nov 23 16:36:26 CST 2020
     */
    private String optionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_res_option_result.result_id
     *
     * @mbg.generated Mon Nov 23 16:36:26 CST 2020
     */
    private String resultId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_res_option_result.id
     *
     * @return the value of sys_res_option_result.id
     *
     * @mbg.generated Mon Nov 23 16:36:26 CST 2020
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_res_option_result.id
     *
     * @param id the value for sys_res_option_result.id
     *
     * @mbg.generated Mon Nov 23 16:36:26 CST 2020
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_res_option_result.option_id
     *
     * @return the value of sys_res_option_result.option_id
     *
     * @mbg.generated Mon Nov 23 16:36:26 CST 2020
     */
    public String getOptionId() {
        return optionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_res_option_result.option_id
     *
     * @param optionId the value for sys_res_option_result.option_id
     *
     * @mbg.generated Mon Nov 23 16:36:26 CST 2020
     */
    public void setOptionId(String optionId) {
        this.optionId = optionId == null ? null : optionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_res_option_result.result_id
     *
     * @return the value of sys_res_option_result.result_id
     *
     * @mbg.generated Mon Nov 23 16:36:26 CST 2020
     */
    public String getResultId() {
        return resultId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_res_option_result.result_id
     *
     * @param resultId the value for sys_res_option_result.result_id
     *
     * @mbg.generated Mon Nov 23 16:36:26 CST 2020
     */
    public void setResultId(String resultId) {
        this.resultId = resultId == null ? null : resultId.trim();
    }
}