/**
 * 
 */
package com.jinyb.crawler.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jinyb.crawler.dao.CrawlerHistoryDao;
import com.jinyb.crawler.entity.CrawlerHistory;

/**
 * @author jinyb09017
 *
 */
public class CrawlerHistoryDaoImpl extends HibernateDaoSupport implements CrawlerHistoryDao {

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerHistoryDao#save(com.hundsun.futures.entity.CrawlerHistory)
	 */
	public void save(CrawlerHistory crawlerHistory) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(crawlerHistory);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerHistoryDao#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(queryById(id));

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerHistoryDao#queryById(java.lang.Integer)
	 */
	public CrawlerHistory queryById(Integer id) {
		// TODO Auto-generated method stub
		return (CrawlerHistory) this.getHibernateTemplate().get(CrawlerHistory.class, id);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerHistoryDao#update(com.hundsun.futures.entity.CrawlerHistory)
	 */
	public void update(CrawlerHistory crawlerHistory) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(crawlerHistory);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerHistoryDao#queryList()
	 */
	@SuppressWarnings("unchecked")
	public List<CrawlerHistory> queryList() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from CrawlerHistory");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerHistoryDao#queryByCondition(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<CrawlerHistory> queryByCondition(String condition,int id) {
		// TODO Auto-generated method stub
		 return this.getHibernateTemplate().find(condition,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrawlerHistory> queryByConditions(String condition, Object[] o) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(condition, o);
	}

}
