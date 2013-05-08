package com.jinyb.crawler.strategy.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;



import com.jinyb.crawler.dao.CrawlerConfigDao;
import com.jinyb.crawler.dao.NewsDao;
import com.jinyb.crawler.dao.impl.NewsDaoImpl;
import com.jinyb.crawler.entity.CrawlerConfig;
import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.service.NewsService;


//第一步：先做链接分析，获得所有url列表（单线程处理）
//第二步：对url对应网页进行解析，获得文本内容（多线程处理）
//分开的目的是在多线程处理上面出现问题。
public class Crawler  {
	private ParserContext parserContext;
	private int thread;
	private List<String> seeds;
	private String time;//所用时间统计
	private  List<String> crawlUrl;//用于保存抓取队列的url列表
	private List crawledDatabaseUrl;//用于保存抓取过的url列表（主要提取数据库中的列表）
	private List<String> crawledUrl;//刚刚爬过的列表
	private int webSuccessed=0;//成功数目
	private int webFailed=0;//失败抓取网页数目，主要用于统计信息。
	private int parserSuccess=0;//解析成功的记录
	private int parserFailed=0;//解析异常的记录
	private Map<String ,String> urlMap;//用于记录搜狐和网易新闻类别信息
	private NewsService newService;
	private int repeatUrl;//重复的url
	private int totalUrl;
	
	
	/**
	 * @return the parserContext
	 */
//	public ParserContext getParserContext() {好像失败了
//		return parserContext;
//	}
//	/**
//	 * @param parserContext the parserContext to set
//	 */
//	public void setParserContext(ParserContext parserContext) {
//		this.parserContext = parserContext;
//	}
	public Crawler()
	{
		parserContext=new ParserContext();
	}
	/**
	 * @return the thread
	 */
	public int getThread() {
		return thread;
	}
	/**
	 * @param thread the thread to set
	 */
	public void setThread(int thread) {
		this.thread = thread;
	}
	/**
	 * @return the seeds
	 */
	public List<String> getSeeds() {
		return seeds;
	}
	/**
	 * @param seeds the seeds to set
	 */
	public void setSeeds(List<String> seeds) {
		this.seeds = seeds;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the crawlUrl
	 */
	public List<String> getCrawlUrl() {
		return crawlUrl;
	}
	/**
	 * @param crawlUrl the crawlUrl to set
	 */
	public void setCrawlUrl(List<String> crawlUrl) {
		this.crawlUrl = crawlUrl;
	}
	/**
	 * @return the crawledDatabaseUrl
	 */
	public List getCrawledDatabaseUrl() {
		return crawledDatabaseUrl;
	}
	/**
	 * @param crawledDatabaseUrl the crawledDatabaseUrl to set
	 */
	public void setCrawledDatabaseUrl(List crawledDatabaseUrl) {
		this.crawledDatabaseUrl = crawledDatabaseUrl;
	}
	/**
	 * @return the crawledUrl
	 */
	public List<String> getCrawledUrl() {
		return crawledUrl;
	}
	/**
	 * @param crawledUrl the crawledUrl to set
	 */
	public void setCrawledUrl(List<String> crawledUrl) {
		this.crawledUrl = crawledUrl;
	}
	/**
	 * @return the webSuccessed
	 */
	public int getWebSuccessed() {
		return webSuccessed;
	}
	/**
	 * @param webSuccessed the webSuccessed to set
	 */
	public void setWebSuccessed(int webSuccessed) {
		this.webSuccessed = webSuccessed;
	}
	/**
	 * @return the webFailed
	 */
	public int getWebFailed() {
		return webFailed;
	}
	/**
	 * @param webFailed the webFailed to set
	 */
	public void setWebFailed(int webFailed) {
		this.webFailed = webFailed;
	}
	/**
	 * @return the urlMap
	 */
	public Map<String, String> getUrlMap() {
		return urlMap;
	}
	/**
	 * @param urlMap the urlMap to set
	 */
	public void setUrlMap(Map<String, String> urlMap) {
		this.urlMap = urlMap;
	}
	/**
	 * @return the newService
	 */
	public NewsService getNewService() {
		return newService;
	}
	/**
	 * @param newService the newService to set
	 */
	public void setNewService(NewsService newService) {
		this.newService = newService;
	}
	public synchronized void addWebSuccess()//使用同步方法，保证多线程使用时数据的准确性
	{
		webSuccessed++;
	}
	public synchronized void addRepeatUrl()//使用同步方法，保证多线程使用时数据的准确性
	{
		repeatUrl++;
	}
	public synchronized void addWebFail()
	{
		webFailed++;
	}
	public synchronized void addParserFail()
	{
		parserFailed++;
	}
	public synchronized void addParserSuccess()
	{
		parserSuccess++;
	}
	public synchronized String getUnvisitUrl()
	{
		String tmpCrawlUrl = crawlUrl.get(0);//it is used to get web page;
		crawlUrl.remove(0);
		return tmpCrawlUrl;
	}
	public void getAllUrls(String url)//第一步，根据种子url来进行策略选择，并进行对应的网页解析，提取所有的url列表（貌似要注意下多线程时的同步问题吧、）
	{
		String html="";
		if(url.contains("news.sina.com"))
		{
			parserContext.setParser(new SinaStrategy());
			html=getHtmlByUrl(url);
			List<String> l=parserContext.getCrawlUrl(html);
			crawlUrl.addAll(l);//获得新浪上所有上所有的url集合
		}
		if(url.contains("news.ifeng.com"))
		{
			parserContext.setParser(new IfengStrategy());
			html=getHtmlByUrl(url);
			List<String> l=parserContext.getCrawlUrl(html);
			
			crawlUrl.addAll(l);
		}
		if(url.contains("news.163.com"))
		{
			html=getHtmlByUrl(url);
			List<String> l=parserContext.getCrawlUrl(html);
			if(url.contains("news.163.com/domestic/"))
				for(String ur:l)
				{
					urlMap.put(ur, "网易国内新闻");
				}
				else
					for(String ur:l)
					{
						urlMap.put(ur, "网易社会新闻");
					}	
			crawlUrl.addAll(l);
		}
		if(url.contains("news.sohu.com"))
		{
			html=getHtmlByUrl(url);
			List<String> l=parserContext.getCrawlUrl(html);
			if(url.contains("news.sohu.com/guoneixinwen"))
				for(String ur:l)
				{
					urlMap.put(ur, "搜狐国内新闻");
				}
				else
					for(String ur:l)
					{
						urlMap.put(ur, "搜狐社会新闻");
					}	
			crawlUrl.addAll(l);
		}
	}
	public void getWebContent(String url)//根据url来进行策略选择，并进行对应的网页解析，并存入数据库（貌似要注意下多线程时的同步问题吧、）
	{
		if(crawledDatabaseUrl.contains(url)||crawledUrl.contains(url))
		{
			addRepeatUrl();//记录重复url数目
			return;
		}
			
		News news=null;
		if(url.contains("news.sina.com"))
		{
			parserContext.setParser(new SinaStrategy());
			news=parserContext.dealWeb(getHtmlByUrl(url));
			news.setNewsUrl(url);
			if(url.contains("com.cn/s"))
				news.setNewsCatagory("新浪社会新闻");
			else
				news.setNewsCatagory("新浪国内新闻");
			//执行insert操作
		}
		if(url.contains("news.ifeng.com"))
		{
			parserContext.setParser(new IfengStrategy());
			news=parserContext.dealWeb(getHtmlByUrl(url));
			news.setNewsUrl(url);
			if(url.contains("com/society"))
				news.setNewsCatagory("凤凰社会新闻");
			else
				news.setNewsCatagory("凤凰国内新闻");
			//执行insert操作
		}
		if(url.contains("news.163.com"))
		{
			parserContext.setParser(new WangyiStrategy());
			news=parserContext.dealWeb(getHtmlByUrl(url));
			news.setNewsUrl(url);
			news.setNewsCatagory(urlMap.get(url));
			//执行insert操作
		}
		if(url.contains("news.sohu.com"))
		{
			parserContext.setParser(new SohuStrategy());
			news=parserContext.dealWeb(getHtmlByUrl(url));
			news.setNewsUrl(url);
			//news.setNewsCatagory(urlMap.get(url));
			//执行insert操作
		}
		System.out.println(news.toString());
		if(news!=null)
		{
			//执行insert操作
			addParserSuccess();
			newService.addNews(news);
		}
		
	}
	public String getHtmlByUrl(String url)//根据url获得网页文件html
	{
		URL u;
		StringBuffer sb = new StringBuffer();
		try {
			u = new URL(url);
			URLConnection httpcon = (HttpURLConnection) u.openConnection(); 
			//URLConnection uc = (HttpURLConnection)url.openConnection();
			httpcon.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)");
			//httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");
			//URLConnection conn = u.openConnection();
			//conn.setDoOutput(true);
			//conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			InputStream is = null;
			is = u.openStream();
			BufferedReader bReader=null;
			if(url.contains("news.ifeng.com"))
			{
				bReader = new BufferedReader(new InputStreamReader(is,"utf-8"));//除凤凰utf-8外，其它都是gb2312

			}
			else
			{
				bReader = new BufferedReader(new InputStreamReader(is,"gbk"));//除凤凰utf-8外，其它都是gb2312,默认是gbk的方式来进行读取

			}
			String rLine = null;
			String tmp_rLine = null;
			while ( (rLine = bReader.readLine()) != null)
			{
				tmp_rLine = rLine;
				int str_len = tmp_rLine.length();
				if (str_len > 0)
				{
					sb.append("\r\n" + tmp_rLine);//如果在这儿进行url匹配，效率会高些吧。
				}
				tmp_rLine = null;
			}
			addWebSuccess();//这里记录的都是抓取成功的记录
			crawledUrl.add(url);//爬完后，队列加上
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addWebFail();
			System.out.println("网络连接异常");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			addWebFail();
			System.out.println("io异常");
			e.printStackTrace();
		}
		
		//获取网页源代码
		//System.out.println(sb.toString());
		return sb.toString();
		
		
	}
//	public void run()
//	{
//		
//		//Thread.sleep(5000);
//
//		while (!crawlUrl.isEmpty())//线程终止条件
//		{
//			System.out.println("进入到多线程");
//			String tmp = getUnvisitUrl();
//			try
//			{
//				getWebContent(tmp);
//			}
//			catch(Exception e)
//			{
//				addWebFail();
//				System.out.println("出现异常啦"+e.getMessage());
//			}
//			
//		}
//	}
	public void startCrawl()
	{
		System.out.println(Thread.activeCount());
		for(int i=0;i<seeds.size();i++)//获取所有的链接ok
		{
			getAllUrls(seeds.get(i).toString());
			totalUrl=crawlUrl.size();
		}
		List<String> u=new ArrayList<String>();
		
		for(int i=0;i<crawlUrl.size();i++)
			u.add(crawlUrl.get(i));
		crawlUrl.clear();
		for(int i=0;i<u.size();i++)
		{
			crawlUrl.add(u.get(i));
		}
			
		for(int i=0;i<crawlUrl.size();i++)//输出测试
		{
			System.out.println(crawlUrl.get(i));
		}
		System.out.println(crawlUrl.size());
		Long timecost=(long) 0;
		Long start=System.currentTimeMillis();
		//CountDownLatch threadSignal = new CountDownLatch(this.thread);    
		for(int j=0;j<this.thread;j++)//多线程来进行抓取
		{
			System.out.println("xxxxxxxxxx"+thread);
			//System.out.println("多线程开始");
		    new Process(this).start();
			
		}
		System.out.println(Thread.activeCount());
		while(true)//主要是用来阻塞主线程的，子线程进入不到这里的。
		{
			if(crawlUrl.isEmpty())//这里3个线程是1：主线程2：一个时间守护线程3：一个mysql的执行进程。
			{
				Long end=System.currentTimeMillis();
				timecost=end-start;
				System.out.println("抓取结束----------------------");
				System.out.println("所花时间："+timeChange(timecost));
				System.out.println("爬过的的url:"+crawledUrl.size());
				System.out.println("成功的数目:"+webSuccessed);
				System.out.println("失败的的url:"+webFailed);
				System.out.println("总的url："+totalUrl);
				System.out.println("重复url:"+repeatUrl);
				System.out.println("解析成功："+parserSuccess);
				System.out.println("解析失败:"+parserFailed);
				System.out.println("数据库:"+crawledDatabaseUrl.size());
				break;
			}
			else
			{
				System.out.println("活动线程数目"+Thread.activeCount());
				System.out.println("------"+crawlUrl.size());
				
			}
			
		}
		time=timeChange(timecost);//记录下时间
		
		
		
	}
	public String timeChange(long time)
	{
		time=time/1000;
		long hour,min,second;
		hour=time/(60*60);//得到小时
		if(hour!=0)
		{
			min=(time-60*60*hour)/(60);//得到分钟
			second=(time-60*60*hour)%(60);
		
		}
		else
		{
			min=time/(60);
			second=time%(60);
		}
		return (hour!=0?hour+"小时":"")+(min!=0?min+"分钟":"")+second+"秒";
		
	}
	public void test()
	{
		for(int i=0;i<seeds.size();i++)
		{
			System.out.println(seeds.get(i).toString());
		}
	}
	public static void main(String[] args) {
		new Crawler().getWebContent("http://news.ifeng.com/mainland/detail_2013_04/30/24812001_0.shtml");
		//Crawler c=new Crawler();
		//System.out.println(c.timeChange(3661*1000));
	}
	public int getRepeatUrl() {
		return repeatUrl;
	}
	public void setRepeatUrl(int repeatUrl) {
		this.repeatUrl = repeatUrl;
	}
	
	public int getParserSuccess() {
		return parserSuccess;
	}
	public void setParserSuccess(int parserSuccess) {
		this.parserSuccess = parserSuccess;
	}

	public int getParserFailed() {
		return parserFailed;
	}
	public void setParserFailed(int parserFailed) {
		this.parserFailed = parserFailed;
	}

	class Process extends Thread{
		private Crawler crawler;
		
		public Process(Crawler crawler)
		{
			this.crawler=crawler;
			
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!crawlUrl.isEmpty())//线程终止条件
			{
				System.out.println(Thread.currentThread().getName() + " 开始..." );    
				String tmp = getUnvisitUrl();
				try
				{
					getWebContent(tmp);
				}
				catch(Exception e)
				{
					addParserFail();//其实这里记录的是解析异常的记录啦
					System.out.println("出现异常啦"+e.getMessage());
				}
				//线程结束时计数器减1
				//threadsSignal.countDown();   
				//System.out.println(Thread.currentThread().getName() + " 结束... 还有"  + threadsSignal.getCount() +  " 个线程" );  
				
			}
			
		}
		
	}
	


}

