package com.hundsun.jinyb.action;

import com.jinyb.crawler.dao.CrawlerConfigDao;
import com.jinyb.crawler.dao.CrawlerSeedsDao;
import com.jinyb.crawler.entity.CrawlerConfig;
import com.jinyb.crawler.entity.CrawlerSeeds;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

public class CrawlerConfigAction extends BaseAction {
	private CrawlerConfigDao crawlerConfigDao;
	private CrawlerConfig crawlerConfig;
	private CrawlerSeedsDao crawlerSeedsDao;

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

	public CrawlerConfigDao getCrawlerConfigDao() {
		return crawlerConfigDao;
	}

	public void setCrawlerConfigDao(CrawlerConfigDao crawlerConfigDao) {
		this.crawlerConfigDao = crawlerConfigDao;
	}
	public String List()
	{
		List<CrawlerConfig> crawlerConfigList=crawlerConfigDao.queryList();
		httpRequest.setAttribute("crawlerConfigList", crawlerConfigList);
		return "list";
	}
	public String toAdd()
	{
		List<CrawlerSeeds> crawlerSeedsList=crawlerSeedsDao.queryList();
		Map<String,String> crawlerSeedsMap=new HashMap<String,String>();
		for(CrawlerSeeds seed:crawlerSeedsList)
			crawlerSeedsMap.put(seed.getWebsite(), seed.getDescription());
		httpRequest.setAttribute("crawlerSeedsMap", crawlerSeedsMap);
		return "toAdd";
	}
	public String add()
	{
		
		System.out.println(this.crawlerConfig);
		
		List<CrawlerConfig> crawlerConfigList=crawlerConfigDao.queryByConditions("from CrawlerConfig c where c.present=?", new Object[]{true});
		if(crawlerConfigList.size()!=0)
		{
			CrawlerConfig crawlerConf=crawlerConfigList.get(0);
			crawlerConf.setPresent(false);
			crawlerConfigDao.update(crawlerConf);//取消之前当前的
		}
		

		this.crawlerConfig.setCcAId(1);
		this.crawlerConfig.setCcTime(new Date());
		crawlerConfig.setPresent(true);
		crawlerConfigDao.save(crawlerConfig);//设置新的当前

		return "add";
	}
	public String detail()
	{
		crawlerConfig=crawlerConfigDao.queryById(crawlerConfig.getId());
		httpRequest.setAttribute("crawlerConfig", crawlerConfig);

		return "detail";
	}
	public String list()
	{
		List<CrawlerConfig> crawlerConfigList=crawlerConfigDao.queryList();
		httpRequest.setAttribute("crawlerConfigList", crawlerConfigList);
		return "list";
	}
	public String present()
	{
		CrawlerConfig crawlerConf=crawlerConfigDao.queryByConditions("from CrawlerConfig c where c.present=?", new Object[]{true}).get(0);
		crawlerConf.setPresent(false);
		crawlerConfigDao.update(crawlerConf);//取消之前当前的

		crawlerConfig=crawlerConfigDao.queryById(crawlerConfig.getId());
		crawlerConfig.setPresent(true);
		crawlerConfigDao.update(crawlerConfig);//设置新的当前
		
		return "present";
		
	}
	public String toPresent()
	{
		CrawlerConfig crawlerConfig=crawlerConfigDao.queryByConditions("from CrawlerConfig c where c.present=?", new Object[]{true}).get(0);
		httpRequest.setAttribute("crawlerConfig", crawlerConfig);
		
		return "toPresent";
		
	}


	public CrawlerConfig getCrawlerConfig() {
		return crawlerConfig;
	}

	public void setCrawlerConfig(CrawlerConfig crawlerConfig) {
		this.crawlerConfig = crawlerConfig;
	}

}
