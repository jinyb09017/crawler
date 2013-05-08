
package com.jinyb.crawler.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jinyb.crawler.dao.WebConfigDao;
import com.jinyb.crawler.entity.WebConfig;

public class WebConfigDaoImpl extends HibernateDaoSupport implements WebConfigDao{
	public void save(WebConfig webConfig) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(webConfig);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.WebConfigDao#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(queryById(id));

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.WebConfigDao#queryById(java.lang.Integer)
	 */
	public WebConfig queryById(Integer id) {
		// TODO Auto-generated method stub
		return (WebConfig) this.getHibernateTemplate().get(WebConfig.class, id);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.WebConfigDao#update(com.hundsun.futures.entity.WebConfig)
	 */
	public void update(WebConfig webConfig) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(webConfig);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.WebConfigDao#queryList()
	 */
	@SuppressWarnings("unchecked")
	public List<WebConfig> queryList() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from WebConfig");
	}

}
