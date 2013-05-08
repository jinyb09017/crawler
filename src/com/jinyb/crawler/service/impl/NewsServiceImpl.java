/**
 * 
 */
package com.jinyb.crawler.service.impl;

import java.util.List;

import com.jinyb.crawler.dao.NewsDao;
import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.entity.Page;
import com.jinyb.crawler.service.NewsService;

/**
 * @author jinyb09017
 *
 */
public class NewsServiceImpl implements NewsService {
	private NewsDao newsDao;
	
	public void setNewsDao(NewsDao newsDao)
	{
		this.newsDao=newsDao;
	}


	/* (non-Javadoc)
	 * @see com.hundsun.futures.service.NewsService#addNews(com.hundsun.futures.entity.News)
	 */
	public void addNews(News news) {
		// TODO Auto-generated method stub
		newsDao.save(news);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.service.NewsService#deleteNews(int)
	 */
	public void deleteNews(int id) {
		// TODO Auto-generated method stub
		newsDao.delete(id);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.service.NewsService#updateNews(com.hundsun.futures.entity.News)
	 */
	public void updateNews(News news) {
		// TODO Auto-generated method stub
		newsDao.update(news);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.service.NewsService#queryById(int)
	 */
	public News queryById(int id) {
		// TODO Auto-generated method stub
		return newsDao.queryById(id);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.service.NewsService#ListNews()
	 */
	public List<News> ListNews() {
		// TODO Auto-generated method stub
		return newsDao.queryList();
	}

	public List<News> ListNewsByColumnId(int id) {
		// TODO Auto-generated method stub
		return newsDao.queryByCondition("from News news where news.tid=?",id);
	}


	@Override
	public List<News> ListNewsByConditon(String con, Object[] o) {
		// TODO Auto-generated method stub
		return newsDao.queryByConditions(con, o);
	}


	@Override
	public List<Object> ListNewsByConditions(String con) {
		// TODO Auto-generated method stub
		return newsDao.queryColumn(con);
	}


	@Override
	public List<News> ListByPage(Page page, String hql) {
		// TODO Auto-generated method stub
		return newsDao.pageQuery(page, hql);
	}


	@Override
	public Page getPage(String hql, Object[] obj) {
		// TODO Auto-generated method stub
		List<News> newsList=newsDao.queryByConditions(hql, obj);
		Page page=new Page();
		page.setAllRows(newsList.size());
		page.setCurrentPage(1);
		int pageTotal=newsList.size()%page.getPageSize()==0?newsList.size()/page.getPageSize():newsList.size()/page.getPageSize()+1;
		page.setTotalPage(pageTotal);
		page.init();
		return page;
	}

}
