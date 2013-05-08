package com.jinyb.crawler.entity;

import java.util.List;
import java.util.Set;

// default package



/**
 * Word entity. @author MyEclipse Persistence Tools
 */

public class Word  implements java.io.Serializable {


    // Fields    

     /* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */


	private Integer id;
     private String word;
     private Integer whId;
     private Set<News> news;
     private List<Integer> ids;//需要转换的id
     private double weight;//叠加权重
     private Integer newsNum;


    // Constructors

    /** default constructor */
    public Word() {
    }

    
    /** full constructor */
    public Word(String word, Integer WHId) {
        this.word = word;
        this.whId = WHId;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getWord() {
        return this.word;
    }
    
    public void setWord(String word) {
        this.word = word;
    }


	/**
	 * @return the whId
	 */
	public Integer getWhId() {
		return whId;
	}


	/**
	 * @param whId the whId to set
	 */
	public void setWhId(Integer whId) {
		this.whId = whId;
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


	public List<Integer> getIds() {
		return ids;
	}


	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}


	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}


	public Integer getNewsNum() {
		return newsNum;
	}


	public void setNewsNum(Integer newsNum) {
		this.newsNum = newsNum;
	}

	@Override
	public String toString() {
		return "Word [id=" + id + ", word=" + word + ", whId=" + whId
				+ ", news=" + news + ", ids=" + ids + ", weight=" + weight
				+ "]";
	}









}