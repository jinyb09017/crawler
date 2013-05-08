package com.jinyb.crawler.entity;
// default package



/**
 * CrawlerSeeds entity. @author MyEclipse Persistence Tools
 */

public class CrawlerSeeds  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String website;
     private String description;


    // Constructors

    /** default constructor */
    public CrawlerSeeds() {
    }

    
    /** full constructor */
    public CrawlerSeeds(String website, String description) {
        this.website = website;
        this.description = description;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getWebsite() {
        return this.website;
    }
    
    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
   








}