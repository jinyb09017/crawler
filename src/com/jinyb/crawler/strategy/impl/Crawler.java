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


//��һ�����������ӷ������������url�б����̴߳���
//�ڶ�������url��Ӧ��ҳ���н���������ı����ݣ����̴߳���
//�ֿ���Ŀ�����ڶ��̴߳�������������⡣
public class Crawler  {
	private ParserContext parserContext;
	private int thread;
	private List<String> seeds;
	private String time;//����ʱ��ͳ��
	private  List<String> crawlUrl;//���ڱ���ץȡ���е�url�б�
	private List crawledDatabaseUrl;//���ڱ���ץȡ����url�б���Ҫ��ȡ���ݿ��е��б�
	private List<String> crawledUrl;//�ո��������б�
	private int webSuccessed=0;//�ɹ���Ŀ
	private int webFailed=0;//ʧ��ץȡ��ҳ��Ŀ����Ҫ����ͳ����Ϣ��
	private int parserSuccess=0;//�����ɹ��ļ�¼
	private int parserFailed=0;//�����쳣�ļ�¼
	private Map<String ,String> urlMap;//���ڼ�¼�Ѻ����������������Ϣ
	private NewsService newService;
	private int repeatUrl;//�ظ���url
	private int totalUrl;
	
	
	/**
	 * @return the parserContext
	 */
//	public ParserContext getParserContext() {����ʧ����
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
	public synchronized void addWebSuccess()//ʹ��ͬ����������֤���߳�ʹ��ʱ���ݵ�׼ȷ��
	{
		webSuccessed++;
	}
	public synchronized void addRepeatUrl()//ʹ��ͬ����������֤���߳�ʹ��ʱ���ݵ�׼ȷ��
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
	public void getAllUrls(String url)//��һ������������url�����в���ѡ�񣬲����ж�Ӧ����ҳ��������ȡ���е�url�б�ò��Ҫע���¶��߳�ʱ��ͬ������ɡ���
	{
		String html="";
		if(url.contains("news.sina.com"))
		{
			parserContext.setParser(new SinaStrategy());
			html=getHtmlByUrl(url);
			List<String> l=parserContext.getCrawlUrl(html);
			crawlUrl.addAll(l);//������������������е�url����
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
					urlMap.put(ur, "���׹�������");
				}
				else
					for(String ur:l)
					{
						urlMap.put(ur, "�����������");
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
					urlMap.put(ur, "�Ѻ���������");
				}
				else
					for(String ur:l)
					{
						urlMap.put(ur, "�Ѻ��������");
					}	
			crawlUrl.addAll(l);
		}
	}
	public void getWebContent(String url)//����url�����в���ѡ�񣬲����ж�Ӧ����ҳ���������������ݿ⣨ò��Ҫע���¶��߳�ʱ��ͬ������ɡ���
	{
		if(crawledDatabaseUrl.contains(url)||crawledUrl.contains(url))
		{
			addRepeatUrl();//��¼�ظ�url��Ŀ
			return;
		}
			
		News news=null;
		if(url.contains("news.sina.com"))
		{
			parserContext.setParser(new SinaStrategy());
			news=parserContext.dealWeb(getHtmlByUrl(url));
			news.setNewsUrl(url);
			if(url.contains("com.cn/s"))
				news.setNewsCatagory("�����������");
			else
				news.setNewsCatagory("���˹�������");
			//ִ��insert����
		}
		if(url.contains("news.ifeng.com"))
		{
			parserContext.setParser(new IfengStrategy());
			news=parserContext.dealWeb(getHtmlByUrl(url));
			news.setNewsUrl(url);
			if(url.contains("com/society"))
				news.setNewsCatagory("����������");
			else
				news.setNewsCatagory("��˹�������");
			//ִ��insert����
		}
		if(url.contains("news.163.com"))
		{
			parserContext.setParser(new WangyiStrategy());
			news=parserContext.dealWeb(getHtmlByUrl(url));
			news.setNewsUrl(url);
			news.setNewsCatagory(urlMap.get(url));
			//ִ��insert����
		}
		if(url.contains("news.sohu.com"))
		{
			parserContext.setParser(new SohuStrategy());
			news=parserContext.dealWeb(getHtmlByUrl(url));
			news.setNewsUrl(url);
			//news.setNewsCatagory(urlMap.get(url));
			//ִ��insert����
		}
		System.out.println(news.toString());
		if(news!=null)
		{
			//ִ��insert����
			addParserSuccess();
			newService.addNews(news);
		}
		
	}
	public String getHtmlByUrl(String url)//����url�����ҳ�ļ�html
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
				bReader = new BufferedReader(new InputStreamReader(is,"utf-8"));//�����utf-8�⣬��������gb2312

			}
			else
			{
				bReader = new BufferedReader(new InputStreamReader(is,"gbk"));//�����utf-8�⣬��������gb2312,Ĭ����gbk�ķ�ʽ�����ж�ȡ

			}
			String rLine = null;
			String tmp_rLine = null;
			while ( (rLine = bReader.readLine()) != null)
			{
				tmp_rLine = rLine;
				int str_len = tmp_rLine.length();
				if (str_len > 0)
				{
					sb.append("\r\n" + tmp_rLine);//������������urlƥ�䣬Ч�ʻ��Щ�ɡ�
				}
				tmp_rLine = null;
			}
			addWebSuccess();//�����¼�Ķ���ץȡ�ɹ��ļ�¼
			crawledUrl.add(url);//����󣬶��м���
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			addWebFail();
			System.out.println("���������쳣");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			addWebFail();
			System.out.println("io�쳣");
			e.printStackTrace();
		}
		
		//��ȡ��ҳԴ����
		//System.out.println(sb.toString());
		return sb.toString();
		
		
	}
//	public void run()
//	{
//		
//		//Thread.sleep(5000);
//
//		while (!crawlUrl.isEmpty())//�߳���ֹ����
//		{
//			System.out.println("���뵽���߳�");
//			String tmp = getUnvisitUrl();
//			try
//			{
//				getWebContent(tmp);
//			}
//			catch(Exception e)
//			{
//				addWebFail();
//				System.out.println("�����쳣��"+e.getMessage());
//			}
//			
//		}
//	}
	public void startCrawl()
	{
		System.out.println(Thread.activeCount());
		for(int i=0;i<seeds.size();i++)//��ȡ���е�����ok
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
			
		for(int i=0;i<crawlUrl.size();i++)//�������
		{
			System.out.println(crawlUrl.get(i));
		}
		System.out.println(crawlUrl.size());
		Long timecost=(long) 0;
		Long start=System.currentTimeMillis();
		//CountDownLatch threadSignal = new CountDownLatch(this.thread);    
		for(int j=0;j<this.thread;j++)//���߳�������ץȡ
		{
			System.out.println("xxxxxxxxxx"+thread);
			//System.out.println("���߳̿�ʼ");
		    new Process(this).start();
			
		}
		System.out.println(Thread.activeCount());
		while(true)//��Ҫ�������������̵߳ģ����߳̽��벻������ġ�
		{
			if(crawlUrl.isEmpty())//����3���߳���1�����߳�2��һ��ʱ���ػ��߳�3��һ��mysql��ִ�н��̡�
			{
				Long end=System.currentTimeMillis();
				timecost=end-start;
				System.out.println("ץȡ����----------------------");
				System.out.println("����ʱ�䣺"+timeChange(timecost));
				System.out.println("�����ĵ�url:"+crawledUrl.size());
				System.out.println("�ɹ�����Ŀ:"+webSuccessed);
				System.out.println("ʧ�ܵĵ�url:"+webFailed);
				System.out.println("�ܵ�url��"+totalUrl);
				System.out.println("�ظ�url:"+repeatUrl);
				System.out.println("�����ɹ���"+parserSuccess);
				System.out.println("����ʧ��:"+parserFailed);
				System.out.println("���ݿ�:"+crawledDatabaseUrl.size());
				break;
			}
			else
			{
				System.out.println("��߳���Ŀ"+Thread.activeCount());
				System.out.println("------"+crawlUrl.size());
				
			}
			
		}
		time=timeChange(timecost);//��¼��ʱ��
		
		
		
	}
	public String timeChange(long time)
	{
		time=time/1000;
		long hour,min,second;
		hour=time/(60*60);//�õ�Сʱ
		if(hour!=0)
		{
			min=(time-60*60*hour)/(60);//�õ�����
			second=(time-60*60*hour)%(60);
		
		}
		else
		{
			min=time/(60);
			second=time%(60);
		}
		return (hour!=0?hour+"Сʱ":"")+(min!=0?min+"����":"")+second+"��";
		
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
			while (!crawlUrl.isEmpty())//�߳���ֹ����
			{
				System.out.println(Thread.currentThread().getName() + " ��ʼ..." );    
				String tmp = getUnvisitUrl();
				try
				{
					getWebContent(tmp);
				}
				catch(Exception e)
				{
					addParserFail();//��ʵ�����¼���ǽ����쳣�ļ�¼��
					System.out.println("�����쳣��"+e.getMessage());
				}
				//�߳̽���ʱ��������1
				//threadsSignal.countDown();   
				//System.out.println(Thread.currentThread().getName() + " ����... ����"  + threadsSignal.getCount() +  " ���߳�" );  
				
			}
			
		}
		
	}
	


}

