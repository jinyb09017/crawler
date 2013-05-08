/**
 * 
 */
package com.jinyb.crawler.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jinyb.crawler.dao.NewsDao;
import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.entity.Page;

/**
 * @author jinyb09017
 *
 */
public class NewsDaoImpl extends HibernateDaoSupport implements NewsDao {

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.NewsDao#save(com.hundsun.futures.entity.News)
	 */
	public void save(News news) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(news);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.NewsDao#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(queryById(id));

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.NewsDao#queryById(java.lang.Integer)
	 */
	public News queryById(Integer id) {
		// TODO Auto-generated method stub
		return (News) this.getHibernateTemplate().get(News.class, id);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.NewsDao#update(com.hundsun.futures.entity.News)
	 */
	public void update(News news) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(news);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.NewsDao#queryList()
	 */
	@SuppressWarnings("unchecked")
	public List<News> queryList() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from News");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.NewsDao#queryByCondition(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<News> queryByCondition(String condition,int id) {
		// TODO Auto-generated method stub
		 return this.getHibernateTemplate().find(condition,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> queryByConditions(String condition, Object[] o) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(condition,o);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryColumn(final String condition) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(condition);
				return query.list();
			}
		});
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> pageQuery(final Page page, final String hql) {
		// TODO Auto-generated method stub
		
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(hql);
				query.setFirstResult(page.getOffsetPage());
				query.setMaxResults(page.getPageSize());
				return query.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> queryList(final String con,final int num) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(con);
				query.setFirstResult(0);
				query.setMaxResults(num);
				
				return query.list();
			}
		});
	}

}
