/**
 * 
 */
package com.hundsun.jinyb.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import com.jinyb.crawler.dao.CrawlerConfigDao;
import com.jinyb.crawler.dao.CrawlerHistoryDao;
import com.jinyb.crawler.entity.CrawlerConfig;
import com.jinyb.crawler.entity.CrawlerHistory;
import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.service.NewsService;
import com.jinyb.crawler.strategy.impl.Crawler;



/**
 * @author jinyb09017
 *
 */
public class CrawlerAction extends BaseAction{
	private CrawlerConfig crawlerConfig;
	private CrawlerConfigDao crawlerConfigDao;
	private NewsService newsService;
	private Crawler crawler;//注入是失败的
	private CrawlerHistoryDao crawlerHistoryDao;
	//private CrawlerHistory crawlerHistory;
	private CrawlerHistory moniterCrawlerHistory;
	private News news;
	private boolean ok=false;//json格式，必须要有对应的set和get方法才能取到对应的数据。
	private String msg;
	
	
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	/**
	 * @return the crawlerConfig
	 */
	public CrawlerConfig getCrawlerConfig() {
		return crawlerConfig;
	}
	/**
	 * @param crawlerConfig the crawlerConfig to set
	 */
	public void setCrawlerConfig(CrawlerConfig crawlerConfig) {
		this.crawlerConfig = crawlerConfig;
	}
	/**
	 * @return the cralwerConfigDao
	 */

	/**
	 * @return the newsService
	 */
	public NewsService getNewsService() {
		return newsService;
	}
	/**
	 * @param newsService the newsService to set
	 */
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	public CrawlerHistoryDao getCrawlerHistoryDao() {
		return crawlerHistoryDao;
	}
	public void setCrawlerHistoryDao(CrawlerHistoryDao crawlerHistoryDao) {
		this.crawlerHistoryDao = crawlerHistoryDao;
	}

	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public String startCrawler()
	{
		initCrawler();
		
		List<CrawlerConfig> crawlerConfigList=crawlerConfigDao.queryByConditions("from CrawlerConfig c where c.present=?", new Object[]{true});
		if(crawlerConfigList.size()!=0)
		{
		
			crawlerConfig = crawlerConfigList.get(0);
			this.crawler.setNewService(newsService);
			crawler.setThread(crawlerConfig.getCcThreads());
			crawler.setSeeds(crawlerConfig.getSeeds());
			System.out.println(Thread.activeCount());
			crawler.startCrawl();//抓取完毕后
			
			CrawlerHistory crawlerHistory=new CrawlerHistory();
			crawlerHistory.setCrhAId(1);//增加历史配置信息
			crawlerHistory.setCrhCcId(crawlerConfig.getId());//配置信息
			crawlerHistory.setCrhTime(new Date());
			crawlerHistory.setCrhTimecost(crawler.getTime());
			crawlerHistory.setCrhWebpageNum(crawler.getCrawledUrl().size());
			crawlerHistory.setCrhWebpageSuc(crawler.getWebSuccessed());
			crawlerHistory.setCrhWebpagefail(crawler.getWebFailed());
			crawlerHistory.setParserSuccess(crawler.getParserSuccess());
			crawlerHistory.setParserFailed(crawler.getParserFailed());
			
			crawlerHistoryDao.save(crawlerHistory);
			
			
			this.ok=true;
			msg="抓取成功";
			
		}
		else
		{
			this.ok=false;
			msg="对不起，你还没有设置当前配置";
		}
		httpRequest.setAttribute("msg", msg);
		System.out.println("爬取成功");
		return "success";
	}
	public String moniter()
	{
		CrawlerHistory moniterCrawlerHistory=new CrawlerHistory();
		if(crawler!=null)
		{
			moniterCrawlerHistory.setCrhTimecost(crawler.getTime());
			moniterCrawlerHistory.setCrhWebpageNum(crawler.getCrawledUrl().size());
			moniterCrawlerHistory.setCrhWebpageSuc(crawler.getWebSuccessed());
			moniterCrawlerHistory.setCrhWebpagefail(crawler.getWebFailed());
			moniterCrawlerHistory.setParserSuccess(crawler.getParserSuccess());
			moniterCrawlerHistory.setParserFailed(crawler.getWebFailed());
		}
		else
		{   int i=0;
			moniterCrawlerHistory.setParserSuccess(i++);
		}
		
		return "moniter";
	}
	public void initCrawler()
	{
		crawler = new Crawler();
		List<String> crawlUrl=new ArrayList<String>();
		crawler.setCrawlUrl(crawlUrl);
		List<String> crawledUrl=new ArrayList<String>();
		crawler.setCrawledUrl(crawledUrl);
		String[] timeConditon=getTimeConditon();
		String sql="select n.newsUrl from News n where n.newsPublishtime > '"+timeConditon[0]+"' and  n.newsPublishtime <'"+timeConditon[1]+"'";
		List<Object> urlList = newsService.ListNewsByConditions(sql);
		crawler.setCrawledDatabaseUrl(urlList);
	}
	@SuppressWarnings("deprecation")
	public String[] getTimeConditon()//默认设置为当前天数之前的十天的url
	{
		Date end=new Date();//当前时间
		long mil=end.getTime()-10*24*60*60*1000;
		Date start=new Date(mil);
		return new String[]{start.toLocaleString(),end.toLocaleString()};
	}
	public CrawlerConfigDao getCrawlerConfigDao() {
		return crawlerConfigDao;
	}
	public void setCrawlerConfigDao(CrawlerConfigDao crawlerConfigDao) {
		this.crawlerConfigDao = crawlerConfigDao;
	}
	public CrawlerHistory getMoniterCrawlerHistory() {
		return moniterCrawlerHistory;
	}
	public void setMoniterCrawlerHistory(CrawlerHistory moniterCrawlerHistory) {
		this.moniterCrawlerHistory = moniterCrawlerHistory;
	}


	

}
