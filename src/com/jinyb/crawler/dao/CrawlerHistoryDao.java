/**
 * 
 */
package com.jinyb.crawler.dao;

import java.util.List;

import com.jinyb.crawler.entity.CrawlerHistory;

/**
 * @author jinyb09017
 *
 */
public interface CrawlerHistoryDao {
    public abstract void save(CrawlerHistory crawlerHistory);
	
	public abstract void delete(Integer id);
	
	public abstract CrawlerHistory queryById(Integer id);
	
	public abstract void update(CrawlerHistory crawlerHistory);
	
	public abstract List<CrawlerHistory> queryList();
	
	public abstract List<CrawlerHistory> queryByCondition(String condition,int id);
	
	public abstract List<CrawlerHistory> queryByConditions(String condition,Object[] o);

}
