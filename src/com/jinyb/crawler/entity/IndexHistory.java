/**
 * 
 */
package com.jinyb.crawler.entity;

import java.sql.Timestamp;

/**
 * @author jinyb09017
 *
 */
public class IndexHistory {
	private int id;
	private Timestamp indexTime;
	private String aid ;
	private Admin admin;
	private String timeCost;
	private boolean present;
	private int cid;
	public Timestamp getIndexTime() {
		return indexTime;
	}
	public void setIndexTime(Timestamp indexTime) {
		this.indexTime = indexTime;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public String getTimeCost() {
		return timeCost;
	}
	public void setTimeCost(String timeCost) {
		this.timeCost = timeCost;
	}
	public boolean isPresent() {
		return present;
	}
	public void setPresent(boolean present) {
		this.present = present;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	

}
