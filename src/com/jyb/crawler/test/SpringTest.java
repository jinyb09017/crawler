/**
 * 
 */
package com.jyb.crawler.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jinyb.crawler.dao.CrawlerConfigDao;
import com.jinyb.crawler.dao.NewsDao;
import com.jinyb.crawler.entity.CrawlerConfig;
import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.service.NewsService;
import com.jinyb.crawler.strategy.impl.Crawler;
import com.jinyb.crawler.strategy.impl.IfengStrategy;
import com.jinyb.crawler.strategy.impl.ParserContext;
import com.jinyb.crawler.strategy.impl.SinaStrategy;
import com.jinyb.crawler.strategy.impl.SohuStrategy;
import com.jinyb.crawler.strategy.impl.WangyiStrategy;

/**
 * @author jinyb09017
 *
 */
public class SpringTest {
	private static ApplicationContext context;
	@BeforeClass
	public static void init()
	{
		context= new ClassPathXmlApplicationContext(new String[]{"ssh-base.xml",
				"ssh-dao.xml","ssh-service.xml","ssh-transaction.xml"});
	}
	@Before
	public void start()
	{
		System.out.println("开始测试");
	}
	@After
	public void end()
	{
		System.out.println("本测试结束");
	}
	@Ignore
	public void sohuTest()
	{
		String url="http://news.sohu.com/20130412/n372427215.shtml";
		NewsService newsService=(NewsService) context.getBean("newsService");
		Crawler crawler=new Crawler();
		ParserContext p=new ParserContext();
		p.setParser(new SohuStrategy());
		String html=crawler.getHtmlByUrl(url);
		News news=p.dealWeb(html);
		news.setNewsUrl(url);
		news.setNewsCatagory("搜狐社会新闻");
		System.out.println(news);
		newsService.addNews(news);
		
	}
	@Ignore
	public void sinaTest()
	{
		String url="http://news.sina.com.cn/s/2013-04-30/023926989256.shtml";
		NewsService newsService=(NewsService) context.getBean("newsService");
		Crawler crawler=new Crawler();
		ParserContext p=new ParserContext();
		p.setParser(new SinaStrategy());
		String html=crawler.getHtmlByUrl(url);
		News news=p.dealWeb(html);
		news.setNewsUrl(url);
		news.setNewsCatagory("新浪社会新闻");
		System.out.println(news);
		newsService.addNews(news);
		
	}
	@Ignore
	public void ifengTest()
	{
		String url="http://news.ifeng.com/mainland/detail_2013_04/30/24812001_0.shtml";
		NewsService newsService=(NewsService) context.getBean("newsService");
		Crawler crawler=new Crawler();
		ParserContext p=new ParserContext();
		p.setParser(new IfengStrategy());
		String html=crawler.getHtmlByUrl(url);
		News news=p.dealWeb(html);
		news.setNewsUrl(url);
		news.setNewsPublishtime(new Date());
		news.setNewsCatagory("凤凰国内新闻");
		System.out.println(news);
		newsService.addNews(news);
		
	}
	@Ignore
	public void WangyiTest()
	{
		String url="http://news.163.com/13/0430/11/8TN2LMH500011229.html";
		NewsService newsService=(NewsService) context.getBean("newsService");
		Crawler crawler=new Crawler();
		ParserContext p=new ParserContext();
		p.setParser(new WangyiStrategy());
		String html=crawler.getHtmlByUrl(url);
		News news=p.dealWeb(html);
		news.setNewsUrl(url);
		news.setNewsPublishtime(new Date());
		news.setNewsCatagory("网易社会新闻");
		System.out.println(news);
		//newsService.addNews(news);
		
	}
	
	public void crawler()
	{
		context= new ClassPathXmlApplicationContext(new String[]{"ssh-base.xml",
				"ssh-dao.xml","ssh-service.xml","ssh-transaction.xml"});
		Crawler crawler = new Crawler();
		List<String> crawlUrl=new ArrayList<String>();
		crawler.setCrawlUrl(crawlUrl);
		List<String> crawledUrl=new ArrayList<String>();
		crawler.setCrawledUrl(crawledUrl);
		String[] timeConditon=getTimeConditon();
		String sql="select n.newsUrl from News n where n.newsPublishtime > '"+timeConditon[0]+"' and  n.newsPublishtime <'"+timeConditon[1]+"'";
		NewsService newsService=(NewsService) context.getBean("newsService");
		CrawlerConfigDao crawlerConfigDao=(CrawlerConfigDao) context.getBean("crawlerConfigDao");
		List<Object> urlList = newsService.ListNewsByConditions(sql);
		crawler.setCrawledDatabaseUrl(urlList);
		List<CrawlerConfig> crawlerConfigList=crawlerConfigDao.queryByConditions("from CrawlerConfig c where c.present=?", new Object[]{true});
		if(crawlerConfigList.size()!=0)
		{
		
			CrawlerConfig crawlerConfig = crawlerConfigList.get(0);
			System.out.println(crawlerConfig);
			crawler.setNewService(newsService);
			crawler.setThread(crawlerConfig.getCcThreads());
			crawler.setSeeds(crawlerConfig.getSeeds());
			System.out.println(Thread.currentThread().getName());
			System.out.println(Thread.activeCount());
			crawler.startCrawl();
			System.out.println(Thread.currentThread().getName());
			System.out.println("222");
		}
	}
	@Ignore
	public void testNews()
	{
		NewsService newsService=(NewsService) context.getBean("newsService");
		News news=newsService.queryById(1);
		String webContent=news.getNewsContent();
		if(webContent.contains("\r\n"))
		{
			System.out.println("%%%%%%%%%%%%%%55");
			String ab=webContent.replaceAll("\r\n", "TTTTTTTTTTTTT");
			System.out.println(ab);
		}
		
	}
	@Test
	public void testHql()
	{
		NewsDao newsDao=(NewsDao) context.getBean("newsDao");
		List<News> newsList=newsDao.queryList("from News",20);
		System.out.println(newsList.size());
	}
	@SuppressWarnings("deprecation")
	public String[] getTimeConditon()//默认设置为当前天数之前的十天的url
	{
		Date end=new Date();//当前时间
		long mil=end.getTime()-10*24*60*60*1000;
		Date start=new Date(mil);
		return new String[]{start.toLocaleString(),end.toLocaleString()};
	}
	public static void main(String[] args) {
		//new SpringTest().crawler();
	}
	
	

}
