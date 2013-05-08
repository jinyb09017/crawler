package com.jinyb.crawler.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.jmx.snmp.Timestamp;

// default package
/**
 * CrawlerConfig entity. @author MyEclipse Persistence Tools
 */

public class CrawlerConfig  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String ccWebSeeds;
     private Integer ccThreads;
     private Integer ccAId;
     private Admin admin;
     private List<String> seeds;
     private boolean present;
     private Date ccTime;


    // Constructors

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CrawlerConfig [id=" + id + ", ccWebSeeds=" + ccWebSeeds
				+ ", ccThreads=" + ccThreads + ", ccAId=" + ccAId + ", admin="
				+ admin + "]";
	}


	/** default constructor */
    public CrawlerConfig() {
    }

    
    /** full constructor */
    public CrawlerConfig(String ccWebSeeds, Integer ccThreads, Integer ccAId) {
        this.ccWebSeeds = ccWebSeeds;
        this.ccThreads = ccThreads;
        this.ccAId = ccAId;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCcWebSeeds() {
        return this.ccWebSeeds;
    }
    
    public void setCcWebSeeds(String ccWebSeeds) {
        this.ccWebSeeds = ccWebSeeds;
    }

    public Integer getCcThreads() {
        return this.ccThreads;
    }
    
    public void setCcThreads(Integer ccThreads) {
        this.ccThreads = ccThreads;
    }

    public Integer getCcAId() {
        return this.ccAId;
    }
    
    public void setCcAId(Integer ccAId) {
        this.ccAId = ccAId;
    }





	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	public List<String> getSeeds() {
		List<String> seeds=new ArrayList<String>();
		String s[]=this.getCcWebSeeds().split("\\,");
		for(String str:s)
			seeds.add(str);
		return seeds;
	}


	public void setSeeds(List<String> seeds) {
		this.seeds = seeds;
		
		
			
	}


	public Date getCcTime() {
		return ccTime;
	}


	public void setCcTime(Date date) {
		this.ccTime = date;
	}


	public boolean isPresent() {
		return present;
	}


	public void setPresent(boolean present) {
		this.present = present;
	}
   








}