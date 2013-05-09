/**
 * 
 */
package com.jinyb.crawler.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jinyb.crawler.dao.ColumnDao;
import com.jinyb.crawler.entity.Column;


/**
 * @author jinyb09017
 *
 */
public class ColumnDaoImpl extends HibernateDaoSupport implements ColumnDao {

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ColumnDao#save(com.hundsun.futures.entity.Column)
	 */
	public void save(Column column) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(column);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ColumnDao#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		if(queryById(id)!=null)
		this.getHibernateTemplate().delete(queryById(id));

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ColumnDao#queryById(java.lang.Integer)
	 */
	public Column queryById(Integer id) {
		// TODO Auto-generated method stub
		return (Column) this.getHibernateTemplate().get(Column.class, id);//找不到，则返回空，而load则会抛出一个异常
		//,另外load先在session级缓存查找，如果没有，是在sessionfactory缓存查找，最后则执行sql查找，而get只在一级查找后，不经过二级查找，找不到，则执行sql语句。
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ColumnDao#update(com.hundsun.futures.entity.Column)
	 */
	public void update(Column column) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(column);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ColumnDao#queryList()
	 */
	@SuppressWarnings("unchecked")
	public List<Column> queryList() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from Column");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ColumnDao#queryByCondition(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Column> queryByCondition(String condition) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(condition);
	}

}
