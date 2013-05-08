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

import com.jinyb.crawler.dao.ClusterResultDao;
import com.jinyb.crawler.entity.ClusterResult;
import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.entity.Page;

/**
 * @author jinyb09017
 *
 */
public class ClusterResultDaoImpl extends HibernateDaoSupport implements ClusterResultDao {

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterResultDao#save(com.hundsun.futures.entity.ClusterResult)
	 */
	public void save(ClusterResult clusterResult) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(clusterResult);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterResultDao#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(queryById(id));

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterResultDao#queryById(java.lang.Integer)
	 */
	public ClusterResult queryById(Integer id) {
		// TODO Auto-generated method stub
		return (ClusterResult) this.getHibernateTemplate().get(ClusterResult.class, id);
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterResultDao#update(com.hundsun.futures.entity.ClusterResult)
	 */
	public void update(ClusterResult clusterResult) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(clusterResult);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterResultDao#queryList()
	 */
	@SuppressWarnings("unchecked")
	public List<ClusterResult> queryList() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from ClusterResult");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ClusterResultDao#queryByCondition(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<ClusterResult> queryByCondition(String condition,int id) {
		// TODO Auto-generated method stub
		 return this.getHibernateTemplate().find(condition,id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClusterResult> queryByConditions(String condition, Object[] o) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(condition, o);
	}

	@Override
	public Page getPage(String hql, Object[] obj) {
		// TODO Auto-generated method stub
		List<ClusterResult> clusterResultList=queryByConditions(hql, obj);
		Page page=new Page();
		page.setAllRows(clusterResultList.size());
		page.setCurrentPage(1);
		int pageTotal=clusterResultList.size()%page.getPageSize()==0?clusterResultList.size()/page.getPageSize():clusterResultList.size()/page.getPageSize()+1;
		page.setTotalPage(pageTotal);
		page.init();
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClusterResult> ListByPage(final Page page, final String hql) {
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

}
