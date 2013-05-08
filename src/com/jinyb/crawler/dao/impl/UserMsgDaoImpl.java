package com.jinyb.crawler.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jinyb.crawler.dao.UserMsgDao;
import com.jinyb.crawler.entity.Admin;
import com.jinyb.crawler.entity.User;

public class UserMsgDaoImpl extends HibernateDaoSupport implements UserMsgDao {

	public List<User> findAllUser(final int page ,final int pageSize) {
		return (List<User>) this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql="from  User";
				Query query=session.createQuery(hql);
				int begin=(page-1)*pageSize;
				query.setFirstResult(begin);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}

	public List<Admin> findAllAdmin(final int page,final int pageSize) {
return (List<Admin>) this.getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql="from  Admin";
				Query query=session.createQuery(hql);
				int begin=(page-1)*pageSize;
				query.setFirstResult(begin);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
	}

	public boolean updateUserById(int id) {
		String hql="from User where id=?";
		List<User> users=this.getHibernateTemplate().find(hql, new Object[]{id});
		if(!users.isEmpty()){
			users.get(0).setState(0);
			this.getHibernateTemplate().update(users.get(0));
			this.getHibernateTemplate().flush();
			return true;
		}else{
			return false;
		}
	}

	public boolean updateUserByUser(User user) {
		String hql="from User where id=?";
		List<User> users=this.getHibernateTemplate().find(hql, new Object[]{user.getId()});
		if(!users.isEmpty()){
			users.get(0).setName(user.getName());
			users.get(0).setEmail(user.getEmail());
			users.get(0).setActive(user.getActive());
			users.get(0).setLevel(user.getLevel());
			users.get(0).setType(user.getType());
			users.get(0).setState(user.getState());
			this.getHibernateTemplate().update(users.get(0));
			this.getHibernateTemplate().flush();
			return true;
		}else{
		   return false;
		}
		
		
	}

	public boolean addAdmin(Admin admin) {
		try {
			this.getHibernateTemplate().save(admin);
			this.getHibernateTemplate().flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delAdminById(int id) {
		String hql="from Admin where id=?";
		List<Admin> admins=this.getHibernateTemplate().find(hql, new Object[]{id});
		if(!admins.isEmpty()){
			admins.get(0).setState(0);
			this.getHibernateTemplate().update(admins.get(0));
			this.getHibernateTemplate().flush();
			return true;
		}else{
			return false;
		}
	}

	public boolean updateAdminByAdmin(Admin admin) {
		String hql="from Admin where id=?";
		List<Admin> admins=this.getHibernateTemplate().find(hql, new Object[]{admin.getId()});
		if(!admins.isEmpty()){
			admins.get(0).setName(admin.getName());
			admins.get(0).setLevel(admin.getLevel());
			admins.get(0).setState(admin.getState());
			this.getHibernateTemplate().update(admins.get(0));
			this.getHibernateTemplate().flush();
			return true;
		}else{
		   return false;
		}
	}

	public int findTotalAdmin(int pageSize) {
		 List<BigInteger> list=(List)getHibernateTemplate().execute(new HibernateCallback() {
				
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					String sql="select count(*) from  f_admin";
					SQLQuery  query=session.createSQLQuery(sql);
					return query.list();
				}
			});
	    int countsql=0;
			int count=0;
			if(!list.isEmpty()){
				countsql=list.get(0).intValue();//将BigInteger转换成int类型
			}
			count=countsql%pageSize==0?countsql/pageSize:countsql/pageSize+1;
			return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> queryByCondition(String con, Object[] o) {
		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find(con, o);
	}

}
