/**
 * 
 */
package com.jinyb.crawler.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jinyb.crawler.dao.IndexHistoryDao;
import com.jinyb.crawler.entity.IndexHistory;

/**
 * @author jinyb09017
 *
 */
public class IndexHistoryDaoImpl extends HibernateDaoSupport implements IndexHistoryDao {

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.IndexHistoryDao#save(com.hundsun.futures.entity.IndexHistory)
	 */
	public void save(IndexHistory indexHistory) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(indexHistory);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.IndexHistoryDao#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(queryById(id));

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.IndexHistoryDao#queryById(java.lang.Integer)
	 */
	public IndexHistory queryById(Integer id) {
		// TODO Auto-generated method stub
		return (IndexHistory) this.getHibernateTemplate().get(IndexHistory.class, id);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.IndexHistoryDao#update(com.hundsun.futures.entity.IndexHistory)
	 */
	public void update(IndexHistory indexHistory) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(indexHistory);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.IndexHistoryDao#queryList()
	 */
	@SuppressWarnings("unchecked")
	public List<IndexHistory> queryList() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from IndexHistory");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.IndexHistoryDao#queryByCondition(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<IndexHistory> queryByCondition(String condition,int id) {
		// TODO Auto-generated method stub
		 return this.getHibernateTemplate().find(condition,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IndexHistory> queryByConditions(String condition, Object[] o) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(condition, o);
	}

}
