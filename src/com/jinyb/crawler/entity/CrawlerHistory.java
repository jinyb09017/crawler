package com.jinyb.crawler.entity;
// default package

import java.sql.Timestamp;
import java.util.Date;


/**
 * CrawlerHistory entity. @author MyEclipse Persistence Tools
 */

public class CrawlerHistory  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer crhWebpageNum;
     private Integer crhWebpageSuc;
     private Integer crhWebpagefail;
     private Integer crhAId;
     private Date crhTime;
     private String crhTimecost;
     private Integer crhCcId;
     private Integer parserSuccess;
     private Integer parserFailed;


    // Constructors

    /** default constructor */
    public CrawlerHistory() {
    }

    
    /** full constructor */
    public CrawlerHistory(Integer crhWebpageNum, Integer crhWebpageSuc, Integer crhAId, Integer crhCcId) {
        this.crhWebpageNum = crhWebpageNum;
        this.crhWebpageSuc = crhWebpageSuc;
        this.crhAId = crhAId;
       
        
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCrhWebpageNum() {
        return this.crhWebpageNum;
    }
    
    public void setCrhWebpageNum(Integer crhWebpageNum) {
        this.crhWebpageNum = crhWebpageNum;
    }

    public Integer getCrhWebpageSuc() {
        return this.crhWebpageSuc;
    }
    
    public void setCrhWebpageSuc(Integer crhWebpageSuc) {
        this.crhWebpageSuc = crhWebpageSuc;
    }

    public Integer getCrhAId() {
        return this.crhAId;
    }
    
    public void setCrhAId(Integer crhAId) {
        this.crhAId = crhAId;
    }

    public Date getCrhTime() {
        return this.crhTime;
    }
    
    public void setCrhTime(Date crhTime) {
        this.crhTime = crhTime;
    }

    public String getCrhTimecost() {
        return this.crhTimecost;
    }
    
    public void setCrhTimecost(String crhTimecost) {
        this.crhTimecost = crhTimecost;
    }

    public Integer getCrhCcId() {
        return this.crhCcId;
    }
    
    public void setCrhCcId(Integer crhCcId) {
        this.crhCcId = crhCcId;
    }


	public Integer getParserSuccess() {
		return parserSuccess;
	}


	public void setParserSuccess(Integer parserSuccess) {
		this.parserSuccess = parserSuccess;
	}


	public Integer getParserFailed() {
		return parserFailed;
	}


	public void setParserFailed(Integer parserFailed) {
		this.parserFailed = parserFailed;
	}


	public Integer getCrhWebpagefail() {
		return crhWebpagefail;
	}


	public void setCrhWebpagefail(Integer crhWebpagefail) {
		this.crhWebpagefail = crhWebpagefail;
	}







}