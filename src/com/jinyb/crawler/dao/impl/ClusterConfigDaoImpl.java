/**
 * 
 */
package com.jinyb.crawler.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jinyb.crawler.dao.ClusterConfigDao;
import com.jinyb.crawler.entity.ClusterConfig;

/**
 * @author jinyb09017
 *
 */
public class ClusterConfigDaoImpl extends HibernateDaoSupport implements ClusterConfigDao {

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterConfigDao#save(com.hundsun.futures.entity.ClusterConfig)
	 */
	public void save(ClusterConfig clusterConfig) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(clusterConfig);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterConfigDao#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(queryById(id));

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterConfigDao#queryById(java.lang.Integer)
	 */
	public ClusterConfig queryById(Integer id) {
		// TODO Auto-generated method stub
		return (ClusterConfig) this.getHibernateTemplate().get(ClusterConfig.class, id);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterConfigDao#update(com.hundsun.futures.entity.ClusterConfig)
	 */
	public void update(ClusterConfig clusterConfig) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(clusterConfig);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterConfigDao#queryList()
	 */
	@SuppressWarnings("unchecked")
	public List<ClusterConfig> queryList() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from ClusterConfig");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterConfigDao#queryByCondition(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ClusterConfig> queryByCondition(String condition,int id) {
		// TODO Auto-generated method stub
		 return this.getHibernateTemplate().find(condition,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClusterConfig> queryByConditions(String condition, Object[] o) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(condition, o);
	}

}
