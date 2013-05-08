package com.jinyb.crawler.entity;
// default package

import java.sql.Timestamp;


/**
 * ClusterConfig entity. @author MyEclipse Persistence Tools
 */

public class ClusterConfig  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Timestamp clcStartTime;
     private Timestamp clcEndTime;
     private Integer clcClusterNum;
     private Integer clcWordNum;
     private Integer clcAId;
     private boolean present;
     private Admin admin;
     private Integer clcwebPageNum;
     private Integer realClusterNum;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the clcStartTime
	 */
	public Timestamp getClcStartTime() {
		return clcStartTime;
	}
	/**
	 * @param clcStartTime the clcStartTime to set
	 */
	public void setClcStartTime(Timestamp clcStartTime) {
		this.clcStartTime = clcStartTime;
	}
	/**
	 * @return the clcEndTime
	 */
	public Timestamp getClcEndTime() {
		return clcEndTime;
	}
	/**
	 * @param clcEndTime the clcEndTime to set
	 */
	public void setClcEndTime(Timestamp clcEndTime) {
		this.clcEndTime = clcEndTime;
	}
	/**
	 * @return the clcClusterNum
	 */
	public Integer getClcClusterNum() {
		return clcClusterNum;
	}
	/**
	 * @param clcClusterNum the clcClusterNum to set
	 */
	public void setClcClusterNum(Integer clcClusterNum) {
		this.clcClusterNum = clcClusterNum;
	}
	/**
	 * @return the clcWordNum
	 */
	public Integer getClcWordNum() {
		return clcWordNum;
	}
	/**
	 * @param clcWordNum the clcWordNum to set
	 */
	public void setClcWordNum(Integer clcWordNum) {
		this.clcWordNum = clcWordNum;
	}
	/**
	 * @return the clcAId
	 */
	public Integer getClcAId() {
		return clcAId;
	}
	/**
	 * @param clcAId the clcAId to set
	 */
	public void setClcAId(Integer clcAId) {
		this.clcAId = clcAId;
	}
	/**
	 * @return the present
	 */
	public boolean isPresent() {
		return present;
	}
	/**
	 * @param present the present to set
	 */
	public void setPresent(boolean present) {
		this.present = present;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Integer getClcwebPageNum() {
		return clcwebPageNum;
	}
	public void setClcwebPageNum(Integer clcwebPageNum) {
		this.clcwebPageNum = clcwebPageNum;
	}
	public Integer getRealClusterNum() {
		return realClusterNum;
	}
	public void setRealClusterNum(Integer realClusterNum) {
		this.realClusterNum = realClusterNum;
	}


   





}