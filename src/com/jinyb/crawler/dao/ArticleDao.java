/**
 * 
 */
package com.jinyb.crawler.dao;

import java.util.List;

import com.jinyb.crawler.entity.Article;



/**
 * @author jinyb09017
 *
 */
public interface ArticleDao {
    public abstract void save(Article acticle);
	
	public abstract void delete(Integer id);
	
	public abstract Article queryById(Integer id);
	
	public abstract void update(Article acticle);
	
	public abstract List<Article> queryList();
	
	public abstract List<Article> queryByCondition(String condition);

}
