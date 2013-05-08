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

import com.jinyb.crawler.dao.WordDao;
import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.entity.Page;
import com.jinyb.crawler.entity.Word;

/**
 * @author jinyb09017
 *
 */
public class WordDaoImpl extends HibernateDaoSupport implements WordDao {

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.WordDao#save(com.hundsun.futures.entity.Word)
	 */
	public void save(Word word) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(word);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.WordDao#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(queryById(id));

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.WordDao#queryById(java.lang.Integer)
	 */
	public Word queryById(Integer id) {
		// TODO Auto-generated method stub
		return (Word) this.getHibernateTemplate().get(Word.class, id);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.WordDao#update(com.hundsun.futures.entity.Word)
	 */
	public void update(Word word) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(word);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.WordDao#queryList()
	 */
	@SuppressWarnings("unchecked")
	public List<Word> queryList() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from Word");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.WordDao#queryByCondition(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Word> queryByCondition(String condition,int id) {
		// TODO Auto-generated method stub
		 return this.getHibernateTemplate().find(condition,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Word> queryByConditions(String condition, Object[] o) {
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
	public List<Word> pageQuery(final Page page, final String hql) {
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
	public List<Word> queryList(final String con, final int num) {
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
