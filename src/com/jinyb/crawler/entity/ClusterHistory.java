package com.jinyb.crawler.entity;
// default package

import java.sql.Timestamp;


/**
 * ClusterHistory entity. @author MyEclipse Persistence Tools
 */

public class ClusterHistory  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer chAId;
     private Timestamp chTime;
     private Integer chWebpageNum;
     private Integer chCclId;
     private boolean present;
     private Admin admin;


    // Constructors

    /** default constructor */
    public ClusterHistory() {
    }

    
    /** full constructor */
    public ClusterHistory(Integer chAId, Timestamp chTime, Integer chWebpageNum, Integer chCclId) {
        this.chAId = chAId;
        this.chTime = chTime;
        this.chWebpageNum = chWebpageNum;
        this.chCclId = chCclId;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChAId() {
        return this.chAId;
    }
    
    public void setChAId(Integer chAId) {
        this.chAId = chAId;
    }

    public Timestamp getChTime() {
        return this.chTime;
    }
    
    public void setChTime(Timestamp chTime) {
        this.chTime = chTime;
    }

    public Integer getChWebpageNum() {
        return this.chWebpageNum;
    }
    
    public void setChWebpageNum(Integer chWebpageNum) {
        this.chWebpageNum = chWebpageNum;
    }

    public Integer getChCclId() {
        return this.chCclId;
    }
    
    public void setChCclId(Integer chCclId) {
        this.chCclId = chCclId;
    }


	public boolean isPresent() {
		return present;
	}


	public void setPresent(boolean present) {
		this.present = present;
	}


	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
   








}