/**
 * 
 */
package com.jinyb.crawler.dao;

import java.util.List;

import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.entity.Page;
import com.jinyb.crawler.entity.Word;

/**
 * @author jinyb09017
 *
 */
public interface WordDao {
    public abstract void save(Word word);
	
	public abstract void delete(Integer id);
	
	public abstract Word queryById(Integer id);
	
	public abstract void update(Word word);
	
	public abstract List<Word> queryList();
	
	public abstract List<Word> queryList(String con,int num);
	
	public abstract List<Word> queryByCondition(String condition,int id);
	
	public abstract List<Word> queryByConditions(String condition,Object[] o);
	
	public abstract List<Object> queryColumn(String condition);//只查询一个字段的值
	
	public abstract List<Word> pageQuery(final Page page,final String hql);

}
