/**
 * 
 */
package com.jinyb.crawler.entity;

import java.sql.Timestamp;

/**
 * @author jinyb09017
 *
 */
public class IndexConfig {
	private int id;
	private Timestamp startTime;
	private Timestamp endTime;
	private String dir;//Ë÷Òý´æ·ÅÄ¿Â¼
	private boolean present;//whether if it is the current config
	private boolean overRide;//whether if it is overrided;
	private int aid;
	private Admin admin;
	private int webpagenum;
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public boolean isPresent() {
		return present;
	}
	public void setPresent(boolean present) {
		this.present = present;
	}

	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public int getWebpagenum() {
		return webpagenum;
	}
	public void setWebpagenum(int webpagenum) {
		this.webpagenum = webpagenum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isOverRide() {
		return overRide;
	}
	public void setOverRide(boolean overRide) {
		this.overRide = overRide;
	}
	

}
