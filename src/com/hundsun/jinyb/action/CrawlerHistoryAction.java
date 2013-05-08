package com.hundsun.jinyb.action;

import com.jinyb.crawler.dao.CrawlerHistoryDao;
import com.jinyb.crawler.entity.CrawlerHistory;
import java.util.List;
public class CrawlerHistoryAction extends BaseAction {
	private CrawlerHistoryDao crawlerHistoryDao;
	private CrawlerHistory crawlerHistory;
	public CrawlerHistory getCrawlerHistory() {
		return crawlerHistory;
	}
	public void setCrawlerHistory(CrawlerHistory crawlerHistory) {
		this.crawlerHistory = crawlerHistory;
	}
	public CrawlerHistoryDao getCrawlerHistoryDao() {
		return crawlerHistoryDao;
	}
	public void setCrawlerHistoryDao(CrawlerHistoryDao crawlerHistoryDao) {
		this.crawlerHistoryDao = crawlerHistoryDao;
	}
	public String list()
	{
		List<CrawlerHistory> crawlerHistoryList=crawlerHistoryDao.queryList();
		httpRequest.setAttribute("crawlerHistoryList", crawlerHistoryList);
		return "list";
	}

}
