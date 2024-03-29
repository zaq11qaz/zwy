package cn.xdl.ydma.entity;

import java.io.Serializable;

public class Subject implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subject.id
     *
     * @mbg.generated Mon Sep 02 19:47:04 GMT+08:00 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subject.name
     *
     * @mbg.generated Mon Sep 02 19:47:04 GMT+08:00 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column subject.direction_id
     *
     * @mbg.generated Mon Sep 02 19:47:04 GMT+08:00 2019
     */
    private Integer directionId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subject.id
     *
     * @return the value of subject.id
     *
     * @mbg.generated Mon Sep 02 19:47:04 GMT+08:00 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subject.id
     *
     * @param id the value for subject.id
     *
     * @mbg.generated Mon Sep 02 19:47:04 GMT+08:00 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subject.name
     *
     * @return the value of subject.name
     *
     * @mbg.generated Mon Sep 02 19:47:04 GMT+08:00 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subject.name
     *
     * @param name the value for subject.name
     *
     * @mbg.generated Mon Sep 02 19:47:04 GMT+08:00 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column subject.direction_id
     *
     * @return the value of subject.direction_id
     *
     * @mbg.generated Mon Sep 02 19:47:04 GMT+08:00 2019
     */
    public Integer getDirectionId() {
        return directionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column subject.direction_id
     *
     * @param directionId the value for subject.direction_id
     *
     * @mbg.generated Mon Sep 02 19:47:04 GMT+08:00 2019
     */
    public void setDirectionId(Integer directionId) {
        this.directionId = directionId;
    }
}