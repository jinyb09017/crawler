/**
 * 
 */
package com.jinyb.crawler.entity;

import java.util.Date;
import java.util.Set;

/**
 * @author jinyb09017
 *
 */
public class Article {
	private Integer id;
    private String title;
    private String content;
    private Integer aid;
	 private String resource;
    private boolean recomend;
    private Integer hits;
    private Integer tid;
    private Date time;
    


   // Constructors

   /** default constructor */
   public Article() {
   }


	public Article(Integer id, String title, String content, Integer aid,
			String resource, boolean recomend, Integer hits, Integer tid,
			Date time) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.aid = aid;
		this.resource = resource;
		this.recomend = recomend;
		this.hits = hits;
		this.tid = tid;
		this.time = time;
	}


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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
   /**
	 * @return the aid
	 */
	public Integer getAid() {
		return aid;
	}


	/**
	 * @param aid the aid to set
	 */
	public void setAid(Integer aid) {
		this.aid = aid;
	}



	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}


	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}


	/**
	 * @return the resource
	 */
	public String getResource() {
		return resource;
	}


	/**
	 * @param resource the resource to set
	 */
	public void setResource(String resource) {
		this.resource = resource;
	}


	/**
	 * @return the recomend
	 */
	public boolean isRecomend() {
		return recomend;
	}


	/**
	 * @param recomend the recomend to set
	 */
	public void setRecomend(boolean recomend) {
		this.recomend = recomend;
	}


	/**
	 * @return the hits
	 */
	public Integer getHits() {
		return hits;
	}


	/**
	 * @param hits the hits to set
	 */
	public void setHits(Integer hits) {
		this.hits = hits;
	}


	/**
	 * @return the tid
	 */
	public Integer getTid() {
		return tid;
	}


	/**
	 * @param tid the tid to set
	 */
	public void setTid(Integer tid) {
		this.tid = tid;
	}


	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}


	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "News [id=" + id + ", title=" + title + ", content=" + content
				+ ", aid=" + aid + ", resource=" + resource + ", recomend="
				+ recomend + ", hits=" + hits + ", tid=" + tid + ", time="
				+ time + "]";
	}


	
   

}
