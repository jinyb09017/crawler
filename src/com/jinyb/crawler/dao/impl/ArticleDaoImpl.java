/**
 * 
 */
package com.jinyb.crawler.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jinyb.crawler.dao.ArticleDao;
import com.jinyb.crawler.entity.Article;


/**
 * @author jinyb09017
 *
 */
public class ArticleDaoImpl extends HibernateDaoSupport implements ArticleDao {

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ArticleDao#save(com.hundsun.futures.entity.Article)
	 */
	public void save(Article arctile) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(arctile);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ArticleDao#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		if(queryById(id)!=null)
		this.getHibernateTemplate().delete(queryById(id));

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ArticleDao#queryById(java.lang.Integer)
	 */
	public Article queryById(Integer id) {
		// TODO Auto-generated method stub
		return (Article) this.getHibernateTemplate().get(Article.class, id);//�Ҳ������򷵻ؿգ���load����׳�һ���쳣
		//,����load����session��������ң����û�У�����sessionfactory������ң������ִ��sql���ң���getֻ��һ�����Һ󣬲������������ң��Ҳ�������ִ��sql��䡣
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ArticleDao#update(com.hundsun.futures.entity.Article)
	 */
	public void update(Article arctile) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(arctile);

	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ArticleDao#queryList()
	 */
	@SuppressWarnings("unchecked")
	public List<Article> queryList() {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from Article");
	}

	/* (non-Javadoc)
	 * @see com.hundsun.futures.dao.ArticleDao#queryByCondition(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Article> queryByCondition(String condition) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(condition);
	}

	
	

}
