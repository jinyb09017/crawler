/**
 * 
 */
package com.jinyb.crawler.dao;

import java.util.List;

import com.jinyb.crawler.entity.ClusterConfig;



/**
 * @author jinyb09017
 *
 */
public interface ClusterConfigDao {
    public abstract void save(ClusterConfig clusterConfig);
	
	public abstract void delete(Integer id);
	
	public abstract ClusterConfig queryById(Integer id);
	
	public abstract void update(ClusterConfig clusterConfig);
	
	public abstract List<ClusterConfig> queryList();
	
	public abstract List<ClusterConfig> queryByCondition(String condition,int id);
	
	public abstract List<ClusterConfig> queryByConditions(String condition,Object[] o);

}
