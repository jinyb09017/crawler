


package com.jinyb.crawler.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jinyb.crawler.dao.CrawlerSeedsDao;
import com.jinyb.crawler.entity.CrawlerSeeds;

public class CrawlerSeedsDaoImpl extends HibernateDaoSupport implements CrawlerSeedsDao{
	public void save(CrawlerSeeds crawlerSeeds) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(crawlerSeeds);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerSeedsDao#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(queryById(id));

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerSeedsDao#queryById(java.lang.Integer)
	 */
	public CrawlerSeeds queryById(Integer id) {
		// TODO Auto-generated method stub
		return (CrawlerSeeds) this.getHibernateTemplate().get(CrawlerSeeds.class, id);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerSeedsDao#update(com.hundsun.futures.entity.CrawlerSeeds)
	 */
	public void update(CrawlerSeeds crawlerSeeds) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(crawlerSeeds);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.CrawlerSeedsDao#queryList()
	 */
	@SuppressWarnings("unchecked")
	public List<CrawlerSeeds> queryList() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from CrawlerSeeds");
	}

}
