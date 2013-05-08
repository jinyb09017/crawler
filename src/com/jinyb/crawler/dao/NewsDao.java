/**
 * 
 */
package com.jinyb.crawler.dao;

import java.util.List;


import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.entity.Page;

/**
 * @author jinyb09017
 *
 */
public interface NewsDao {
    public abstract void save(News news);
	
	public abstract void delete(Integer id);
	
	public abstract News queryById(Integer id);
	
	public abstract void update(News news);
	
	public abstract List<News> queryList();
	
	public abstract List<News> queryList(String con,int num);
	
	public abstract List<News> queryByCondition(String condition,int id);
	
	public abstract List<News> queryByConditions(String condition,Object[] o);
	
	public abstract List<News> pageQuery(final Page page,final String hql);
	
	public abstract List<Object> queryColumn(String condition);//只查询一个字段的值

}
