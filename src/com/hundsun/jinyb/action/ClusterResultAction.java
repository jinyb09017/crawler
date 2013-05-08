package com.hundsun.jinyb.action;


import com.jinyb.crawler.constants.Constant;
import com.jinyb.crawler.dao.ClusterResultDao;
import com.jinyb.crawler.entity.ClusterResult;
import com.jinyb.crawler.entity.Page;
import com.jinyb.crawler.entity.Word;
import com.jinyb.crawler.service.NewsService;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class ClusterResultAction extends BaseAction {
	private ClusterResultDao clusterResultDao;
	private ClusterResult clusterResult;
	private NewsService newsService;
	private Page page;


	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public ClusterResultDao getClusterResultDao() {
		return clusterResultDao;
	}

	public void setClusterResultDao(ClusterResultDao clusterResultDao) {
		this.clusterResultDao = clusterResultDao;
	}
	public String list()
	{
        String hql="from ClusterResult w where w.chId="+Constant.CLUSTER_HISTORY_VESION;
		
		Page p=clusterResultDao.getPage(hql,new Object[]{});// this two hql are different
		p.setPageSize(5);
		if(page!=null)//first to list page when page is null.and initiate the page;
		{
			p.setCurrentPage(page.getCurrentPage());
			
		}

		List<ClusterResult> clusterResultList=clusterResultDao.ListByPage(p, hql);
		httpRequest.setAttribute("page", p);
	    httpRequest.setAttribute("clusterResultList", clusterResultList);
	    return "list";
		
	}
	public String detail()
	{
		System.out.println("得到chid"+clusterResult.getChId());
		List<ClusterResult> clusterResultList=clusterResultDao.queryByConditions("from ClusterResult c where c.chId=?", new Object[]{clusterResult.getChId()});
		httpRequest.setAttribute("clusterResultList", clusterResultList);
		return "detail";
	}
	

	public Timestamp timeChange(String str)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yy-mm-dd HH:mm:ss");
		Date d=null;
		Timestamp t=null;
		try {
			d = sdf.parse(str);
			t=new Timestamp(d.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("parser fail");
		}
		
		return t;
	}

	public String present()
	{
		ClusterResult clusterConf=clusterResultDao.queryByConditions("from ClusterResult c where c.present=?", new Object[]{true}).get(0);
		
		clusterResultDao.update(clusterConf);//取消之前当前的

		clusterResult=clusterResultDao.queryById(clusterResult.getId());
		
		clusterResultDao.update(clusterResult);//设置新的当前
		
		return "present";
		
	}
	public String toPresent()
	{
		ClusterResult clusterResult=clusterResultDao.queryByConditions("from ClusterResult c where c.present=?", new Object[]{true}).get(0);
		httpRequest.setAttribute("clusterResult", clusterResult);
		
		return "toPresent";
		
	}

	public ClusterResult getClusterResult() {
		return clusterResult;
	}

	public void setClusterResult(ClusterResult clusterResult) {
		this.clusterResult = clusterResult;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

}
