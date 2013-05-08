package com.hundsun.jinyb.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.jinyb.crawler.dao.ClusterResultDao;
import com.jinyb.crawler.dao.WordDao;
import com.jinyb.crawler.entity.ClusterResult;
import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.entity.Page;
import com.jinyb.crawler.entity.Word;
import com.jinyb.crawler.service.NewsService;

public class NewsAction extends BaseAction {
	private News news;
	private NewsService newsService;
	private Word word;
	private ClusterResult clusterResult;
	private ClusterResultDao clusterResultDao;
	private Page page;
	private WordDao wordDao;
	
	public WordDao getWordDao() {
		return wordDao;
	}
	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}
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
	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}
	public String list()
	{
		String hql="from News";
		if(page==null)//first to list page when page is null.and initiate the page;
		{
			page=newsService.getPage(hql,new Object[]{});// this two hql are different
		}
		
		
		List<News> newsList=newsService.ListByPage(page, hql);
		httpRequest.setAttribute("page", page);
	    httpRequest.setAttribute("newsList", newsList);
	    return "list";
	}
	public String detail()
	{
		News n=newsService.queryById(news.getNewsId());
		String content=n.getNewsContent();
		String webContent=content.replace("\r\n", "<br>");
		n.setWebContent(webContent);
		httpRequest.setAttribute("news", n);
		return "detail";
	}
	public String listByResultId()
	{
		ClusterResult cr=clusterResultDao.queryById(clusterResult.getId());
		Set<News> nList=cr.getNews();
		News[] newsArray=new News[nList.size()];
		nList.toArray(newsArray);
		if(page==null)
		{
			page=new Page();
			page.setAllRows(nList.size());
			page.setCurrentPage(0);
			page.init();//初始化page信息
		}
		List<News> newsList=new ArrayList<News>();
		if(nList.size()<page.getPageSize())
		{
			for(int i=0;i<nList.size();i++)
			{
				newsList.add(newsArray[i]);
			}
		}
		else
		{
			for(int i=page.getOffsetPage();i<page.getPageSize()+page.getOffsetPage();i++)
			{
				newsList.add(newsArray[i]);
			}
		}
		
		
		httpRequest.setAttribute("newsList", newsList);
		httpRequest.setAttribute("page", page);
		httpRequest.setAttribute("clusterResult", clusterResult);
		return "listByResultId";
	}
	public String listByWordId()
	{
		Word cr=wordDao.queryById(word.getId());
		Set<News> nList=cr.getNews();
		News[] newsArray=new News[nList.size()];
		nList.toArray(newsArray);
		if(page==null)
		{
			page=new Page();
			page.setAllRows(nList.size());
			page.setCurrentPage(0);
			page.init();//初始化page信息
		}
		List<News> newsList=new ArrayList<News>();
		if(nList.size()<page.getPageSize())
		{
			for(int i=0;i<nList.size();i++)
			{
				newsList.add(newsArray[i]);
			}
		}
		else
		{
			for(int i=page.getOffsetPage();i<page.getPageSize()+page.getOffsetPage();i++)
			{
				newsList.add(newsArray[i]);
			}
		}
		
		httpRequest.setAttribute("newsList", newsList);
		httpRequest.setAttribute("page", page);
		httpRequest.setAttribute("word", word);//用于传递id
		return "listByWordId";
	}
	/**
	 * @return the news
	 */
	public News getNews() {
		return news;
	}
	/**
	 * @param news the news to set
	 */
	public void setNews(News news) {
		this.news = news;
	}
	/**
	 * @return the newService
	 */
	public ClusterResult getClusterResult() {
		return clusterResult;
	}
	public void setClusterResult(ClusterResult clusterResult) {
		this.clusterResult = clusterResult;
	}
	public Word getWord() {
		return word;
	}
	public void setWord(Word word) {
		this.word = word;
	}
	public ClusterResultDao getClusterResultDao() {
		return clusterResultDao;
	}
	public void setClusterResultDao(ClusterResultDao clusterResultDao) {
		this.clusterResultDao = clusterResultDao;
	}

	

}
