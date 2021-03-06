package com.example.system.entity;

import java.util.Date;

public class SysRole {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.id
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.role_name
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    private String role_name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.role_code
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    private String role_code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.description
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.create_by
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    private String create_by;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.created_at
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    private Date created_at;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_role.update_at
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    private Date update_at;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.id
     *
     * @return the value of sys_role.id
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.id
     *
     * @param id the value for sys_role.id
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.role_name
     *
     * @return the value of sys_role.role_name
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    public String getRole_name() {
        return role_name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.role_name
     *
     * @param role_name the value for sys_role.role_name
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    public void setRole_name(String role_name) {
        this.role_name = role_name == null ? null : role_name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.role_code
     *
     * @return the value of sys_role.role_code
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    public String getRole_code() {
        return role_code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.role_code
     *
     * @param role_code the value for sys_role.role_code
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    public void setRole_code(String role_code) {
        this.role_code = role_code == null ? null : role_code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.description
     *
     * @return the value of sys_role.description
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.description
     *
     * @param description the value for sys_role.description
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.create_by
     *
     * @return the value of sys_role.create_by
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    public String getCreate_by() {
        return create_by;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.create_by
     *
     * @param create_by the value for sys_role.create_by
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    public void setCreate_by(String create_by) {
        this.create_by = create_by == null ? null : create_by.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.created_at
     *
     * @return the value of sys_role.created_at
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    public Date getCreated_at() {
        return created_at;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.created_at
     *
     * @param created_at the value for sys_role.created_at
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_role.update_at
     *
     * @return the value of sys_role.update_at
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    public Date getUpdate_at() {
        return update_at;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_role.update_at
     *
     * @param update_at the value for sys_role.update_at
     *
     * @mbg.generated Mon Nov 09 17:13:50 CST 2020
     */
    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }
}