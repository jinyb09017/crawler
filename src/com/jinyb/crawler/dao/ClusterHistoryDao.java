/**
 * 
 */
package com.jinyb.crawler.dao;

import java.util.List;

import com.jinyb.crawler.entity.ClusterHistory;

/**
 * @author jinyb09017
 *
 */
public interface ClusterHistoryDao {
    public abstract void save(ClusterHistory clusterHistory);
	
	public abstract void delete(Integer id);
	
	public abstract ClusterHistory queryById(Integer id);
	
	public abstract void update(ClusterHistory clusterHistory);
	
	public abstract List<ClusterHistory> queryList();
	
	public abstract List<ClusterHistory> queryByCondition(String condition,int id);
	
	public abstract List<ClusterHistory> queryByConditions(String condition,Object[] o);

}
