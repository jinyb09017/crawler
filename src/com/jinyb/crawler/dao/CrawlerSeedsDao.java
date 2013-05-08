/**
 * 
 */
package com.jinyb.crawler.dao;

import java.util.List;

import com.jinyb.crawler.entity.CrawlerSeeds;

/**
 * @author jinyb09017
 *
 */
public interface  CrawlerSeedsDao {
	 public abstract void save(CrawlerSeeds crawlerSeeds);
		
		public abstract void delete(Integer id);
		
		public abstract CrawlerSeeds queryById(Integer id);
		
		public abstract void update(CrawlerSeeds crawlerSeeds);
		
		public abstract List<CrawlerSeeds> queryList();

}
