package com.hundsun.jinyb.action;

import com.jinyb.crawler.cluster.Tokeniser;
import com.jinyb.crawler.dao.ClusterHistoryDao;
import com.jinyb.crawler.entity.ClusterHistory;
import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.service.NewsService;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;



public class ClusterHistoryAction extends BaseAction {
	private ClusterHistoryDao clusterHistoryDao;
	private ClusterHistory clusterHistory;
	


	public ClusterHistoryDao getClusterHistoryDao() {
		return clusterHistoryDao;
	}

	public void setClusterHistoryDao(ClusterHistoryDao clusterHistoryDao) {
		this.clusterHistoryDao = clusterHistoryDao;
	}
	public String List()
	{
		List<ClusterHistory> clusterHistoryList=clusterHistoryDao.queryList();
		httpRequest.setAttribute("clusterHistoryList", clusterHistoryList);
		return "list";
	}
	
	
	
	public String list()
	{
		List<ClusterHistory> clusterHistoryList=clusterHistoryDao.queryList();
		httpRequest.setAttribute("clusterHistoryList", clusterHistoryList);
		return "list";
	}
	public String present()
	{
		String msg="";
		List<ClusterHistory> currentHistoryList=clusterHistoryDao.queryByConditions("from ClusterHistory c where c.present=?", new Object[]{true});
		if(currentHistoryList.size()>1)
		{
			msg="聚类历史不唯一";
			return "error";
		}
		else if(currentHistoryList.size()==1)
		{
			ClusterHistory currentHistory=currentHistoryList.get(0);
			currentHistory.setPresent(false);
			clusterHistoryDao.update(currentHistory);
		}
		ClusterHistory ch=clusterHistoryDao.queryById(clusterHistory.getId());
		ch.setPresent(true);
		
		clusterHistoryDao.update(ch);
		
		return "present";
		
	}
	public String detail()
	{
		ClusterHistory clusterHis=new ClusterHistory();
		clusterHis=clusterHistoryDao.queryById(clusterHistory.getId());
		httpRequest.setAttribute("clusterHistory", clusterHis);
		return "detail";
	}


	public ClusterHistory getClusterHistory() {
		return clusterHistory;
	}

	public void setClusterHistory(ClusterHistory clusterHistory) {
		this.clusterHistory = clusterHistory;
	}



}
