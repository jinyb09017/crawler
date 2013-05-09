package com.hundsun.jinyb.action;


import com.jinyb.crawler.dao.IndexHistoryDao;
import com.jinyb.crawler.entity.IndexHistory;
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



public class IndexHistoryAction extends BaseAction {
	private IndexHistoryDao indexHistoryDao;
	private IndexHistory indexHistory;
	


	public IndexHistoryDao getIndexHistoryDao() {
		return indexHistoryDao;
	}

	public void setIndexHistoryDao(IndexHistoryDao indexHistoryDao) {
		this.indexHistoryDao = indexHistoryDao;
	}
	public String List()
	{
		List<IndexHistory> indexHistoryList=indexHistoryDao.queryList();
		httpRequest.setAttribute("indexHistoryList", indexHistoryList);
		return "list";
	}
	
	
	
	public String list()
	{
		List<IndexHistory> indexHistoryList=indexHistoryDao.queryList();
		httpRequest.setAttribute("indexHistoryList", indexHistoryList);
		return "list";
	}
	public String present()
	{
		String msg="";
		List<IndexHistory> currentHistoryList=indexHistoryDao.queryByConditions("from IndexHistory c where c.present=?", new Object[]{true});
		if(currentHistoryList.size()>1)
		{
			msg="聚类历史不唯一";
			return "error";
		}
		else if(currentHistoryList.size()==1)
		{
			IndexHistory currentHistory=currentHistoryList.get(0);
			currentHistory.setPresent(false);
			indexHistoryDao.update(currentHistory);
		}
		IndexHistory ch=indexHistoryDao.queryById(indexHistory.getId());
		ch.setPresent(true);
		
		indexHistoryDao.update(ch);
		
		return "present";
		
	}
	public String detail()
	{
		IndexHistory indexHis=new IndexHistory();
		indexHis=indexHistoryDao.queryById(indexHistory.getId());
		httpRequest.setAttribute("indexHistory", indexHis);
		return "detail";
	}


	public IndexHistory getIndexHistory() {
		return indexHistory;
	}

	public void setIndexHistory(IndexHistory indexHistory) {
		this.indexHistory = indexHistory;
	}



}
