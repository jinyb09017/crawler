/**
 * 
 */
package com.jinyb.crawler.dao;

import java.util.List;

import com.jinyb.crawler.entity.IndexHistory;

/**
 * @author jinyb09017
 *
 */
public interface IndexHistoryDao {
    public abstract void save(IndexHistory indexHistory);
	
	public abstract void delete(Integer id);
	
	public abstract IndexHistory queryById(Integer id);
	
	public abstract void update(IndexHistory indexHistory);
	
	public abstract List<IndexHistory> queryList();
	
	public abstract List<IndexHistory> queryByCondition(String condition,int id);
	
	public abstract List<IndexHistory> queryByConditions(String condition,Object[] o);

}
