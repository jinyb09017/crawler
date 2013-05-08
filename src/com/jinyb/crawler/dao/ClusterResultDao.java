/**
 * 
 */
package com.jinyb.crawler.dao;

import java.util.List;

import com.jinyb.crawler.entity.ClusterResult;
import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.entity.Page;



/**
 * @author jinyb09017
 *
 */
public interface ClusterResultDao {
    public abstract void save(ClusterResult clusterResult);
	
	public abstract void delete(Integer id);
	
	public abstract ClusterResult queryById(Integer id);
	
	public abstract void update(ClusterResult clusterResult);
	
	public abstract List<ClusterResult> queryList();
	
	public abstract List<ClusterResult> queryByCondition(String condition,int id);
	
	public abstract List<ClusterResult> queryByConditions(String condition,Object[] o);
	
	public abstract Page getPage(String hql,Object[] obj);
	
	public abstract List<ClusterResult> ListByPage(Page page,String hql);

}
