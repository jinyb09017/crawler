/**
 * 
 */
package com.jinyb.crawler.service;

import java.util.List;


import com.jinyb.crawler.dao.NewsDao;
import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.entity.Page;

/**
 * @author jinyb09017
 *
 */
public interface NewsService {
   public abstract void addNews(News news);
	
	public abstract void deleteNews(int id);
	
	public abstract void updateNews(News news);
	
	public abstract News queryById(int id);
	
	public abstract List<News> ListNews();
	
	public abstract List<News> ListNewsByColumnId(int id);
	
	public abstract List<News> ListNewsByConditon(String con,Object[] o);
	
	public abstract List<Object> ListNewsByConditions(String con);
	
    public abstract List<News> ListByPage(Page page,String hql);
	
	public abstract Page getPage(String hql,Object[] obj);
	
}
