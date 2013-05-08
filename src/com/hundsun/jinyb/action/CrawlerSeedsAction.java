/**
 * 
 */
package com.hundsun.jinyb.action;


import java.util.List;

import com.jinyb.crawler.dao.CrawlerSeedsDao;
import com.jinyb.crawler.entity.CrawlerSeeds;

/**
 * @author jinyb09017
 *
 */
public class CrawlerSeedsAction extends BaseAction {
	private CrawlerSeeds crawlerSeeds;
	private CrawlerSeedsDao crawlerSeedsDao;
	
	/**
	 * @return the crawlerSeeds
	 */
	public CrawlerSeeds getCrawlerSeeds() {
		return crawlerSeeds;
	}
	/**
	 * @param crawlerSeeds the crawlerSeeds to set
	 */
	public void setCrawlerSeeds(CrawlerSeeds crawlerSeeds) {
		this.crawlerSeeds = crawlerSeeds;
	}
	/**
	 * @return the crawlerSeedsDao
	 */
	public CrawlerSeedsDao getCrawlerSeedsDao() {
		return crawlerSeedsDao;
	}
	/**
	 * @param crawlerSeedsDao the crawlerSeedsDao to set
	 */
	public void setCrawlerSeedsDao(CrawlerSeedsDao crawlerSeedsDao) {
		this.crawlerSeedsDao = crawlerSeedsDao;
	}
	public String list()
	{
		List<CrawlerSeeds> crawlerSeedsList=crawlerSeedsDao.queryList();
		httpRequest.setAttribute("crawlerSeedsList", crawlerSeedsList);
		return "list";
	}

}
