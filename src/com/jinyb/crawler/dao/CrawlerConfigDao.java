/**
 * 
 */
package com.jinyb.crawler.dao;

import java.util.List;

import com.jinyb.crawler.entity.CrawlerConfig;

/**
 * @author jinyb09017
 *
 */
public interface CrawlerConfigDao {
    public abstract void save(CrawlerConfig crawlerConfig);
	
	public abstract void delete(Integer id);
	
	public abstract CrawlerConfig queryById(Integer id);
	
	public abstract void update(CrawlerConfig crawlerConfig);
	
	public abstract List<CrawlerConfig> queryList();
	
	public abstract List<CrawlerConfig> queryByCondition(String condition,int id);
	
	public abstract List<CrawlerConfig> queryByConditions(String condition,Object[] o);

}
