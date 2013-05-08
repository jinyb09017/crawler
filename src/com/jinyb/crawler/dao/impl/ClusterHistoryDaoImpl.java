/**
 * 
 */
package com.jinyb.crawler.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jinyb.crawler.dao.ClusterHistoryDao;
import com.jinyb.crawler.entity.ClusterHistory;

/**
 * @author jinyb09017
 *
 */
public class ClusterHistoryDaoImpl extends HibernateDaoSupport implements ClusterHistoryDao {

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterHistoryDao#save(com.hundsun.futures.entity.ClusterHistory)
	 */
	public void save(ClusterHistory clusterHistory) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(clusterHistory);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterHistoryDao#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(queryById(id));

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterHistoryDao#queryById(java.lang.Integer)
	 */
	public ClusterHistory queryById(Integer id) {
		// TODO Auto-generated method stub
		return (ClusterHistory) this.getHibernateTemplate().get(ClusterHistory.class, id);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterHistoryDao#update(com.hundsun.futures.entity.ClusterHistory)
	 */
	public void update(ClusterHistory clusterHistory) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(clusterHistory);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterHistoryDao#queryList()
	 */
	@SuppressWarnings("unchecked")
	public List<ClusterHistory> queryList() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from ClusterHistory");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterHistoryDao#queryByCondition(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ClusterHistory> queryByCondition(String condition,int id) {
		// TODO Auto-generated method stub
		 return this.getHibernateTemplate().find(condition,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClusterHistory> queryByConditions(String condition, Object[] o) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(condition, o);
	}

}
