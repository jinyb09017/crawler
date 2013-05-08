/**
 * 
 */
package com.jinyb.crawler.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jinyb.crawler.dao.CrawlerConfigDao;
import com.jinyb.crawler.entity.CrawlerConfig;

/**
 * @author jinyb09017
 *
 */
public class CrawlerConfigDaoImpl extends HibernateDaoSupport implements CrawlerConfigDao {

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerConfigDao#save(com.hundsun.futures.entity.CrawlerConfig)
	 */
	public void save(CrawlerConfig crawlerConfig) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(crawlerConfig);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerConfigDao#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(queryById(id));

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerConfigDao#queryById(java.lang.Integer)
	 */
	public CrawlerConfig queryById(Integer id) {
		// TODO Auto-generated method stub
		return (CrawlerConfig) this.getHibernateTemplate().get(CrawlerConfig.class, id);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerConfigDao#update(com.hundsun.futures.entity.CrawlerConfig)
	 */
	public void update(CrawlerConfig crawlerConfig) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(crawlerConfig);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerConfigDao#queryList()
	 */
	@SuppressWarnings("unchecked")
	public List<CrawlerConfig> queryList() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from CrawlerConfig");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerConfigDao#queryByCondition(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<CrawlerConfig> queryByCondition(String condition,int id) {
		// TODO Auto-generated method stub
		 return this.getHibernateTemplate().find(condition,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrawlerConfig> queryByConditions(String condition, Object[] o) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(condition, o);
	}

}
