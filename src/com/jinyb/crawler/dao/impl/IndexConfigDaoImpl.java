/**
 * 
 */
package com.jinyb.crawler.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jinyb.crawler.dao.IndexConfigDao;
import com.jinyb.crawler.entity.IndexConfig;

/**
 * @author jinyb09017
 *
 */
public class IndexConfigDaoImpl extends HibernateDaoSupport implements IndexConfigDao {

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.IndexConfigDao#save(com.hundsun.futures.entity.IndexConfig)
	 */
	public void save(IndexConfig indexConfig) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(indexConfig);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.IndexConfigDao#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(queryById(id));

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.IndexConfigDao#queryById(java.lang.Integer)
	 */
	public IndexConfig queryById(Integer id) {
		// TODO Auto-generated method stub
		return (IndexConfig) this.getHibernateTemplate().get(IndexConfig.class, id);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.IndexConfigDao#update(com.hundsun.futures.entity.IndexConfig)
	 */
	public void update(IndexConfig indexConfig) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(indexConfig);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.IndexConfigDao#queryList()
	 */
	@SuppressWarnings("unchecked")
	public List<IndexConfig> queryList() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from IndexConfig");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.IndexConfigDao#queryByCondition(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<IndexConfig> queryByCondition(String condition,int id) {
		// TODO Auto-generated method stub
		 return this.getHibernateTemplate().find(condition,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IndexConfig> queryByConditions(String condition, Object[] o) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(condition, o);
	}

}
