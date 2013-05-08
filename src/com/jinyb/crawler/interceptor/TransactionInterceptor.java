package com.jinyb.crawler.interceptor;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class TransactionInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 8224624556642546385L;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			//DbPoolUtil.getConnection().setAutoCommit(false);
			System.out.println("事物开始");
			invocation.invoke();
			//DbPoolUtil.getConnection().commit();
			//HibernateUtil.closeSession();
			System.out.println("事物提交");
		} catch (Exception e) {
			e.printStackTrace();
			//DbPoolUtil.getConnection().rollback();
			System.out.println("事物回滚");
		}finally{
			//DbPoolUtil.closeConnection();
		}
		return null;
	}

}
