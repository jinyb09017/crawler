/**
 * 
 */
package com.jinyb.crawler.dao;

import java.util.List;

import com.jinyb.crawler.entity.IndexConfig;



/**
 * @author jinyb09017
 *
 */
public interface IndexConfigDao {
    public abstract void save(IndexConfig indexConfig);
	
	public abstract void delete(Integer id);
	
	public abstract IndexConfig queryById(Integer id);
	
	public abstract void update(IndexConfig indexConfig);
	
	public abstract List<IndexConfig> queryList();
	
	public abstract List<IndexConfig> queryByCondition(String condition,int id);
	
	public abstract List<IndexConfig> queryByConditions(String condition,Object[] o);

}
