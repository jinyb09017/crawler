package com.jinyb.crawler.entity;

import java.util.Set;

// default package



/**
 * ClusterResult entity. @author MyEclipse Persistence Tools
 */

public class ClusterResult  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String cTitle;
     private Integer chId;
     private Set<News> news;


    // Constructors

    /** default constructor */
    public ClusterResult() {
    }

    


   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }


	/**
	 * @return the cTitle
	 */
	public String getcTitle() {
		return cTitle;
	}


	/**
	 * @param cTitle the cTitle to set
	 */
	public void setcTitle(String cTitle) {
		this.cTitle = cTitle;
	}



	/**
	 * @return the chId
	 */
	public Integer getChId() {
		return chId;
	}


	/**
	 * @param chId the chId to set
	 */
	public void setChId(Integer chId) {
		this.chId = chId;
	}


	/**
	 * @return the news
	 */
	public Set<News> getNews() {
		return news;
	}


	/**
	 * @param news the news to set
	 */
	public void setNews(Set<News> news) {
		this.news = news;
	}

   







}