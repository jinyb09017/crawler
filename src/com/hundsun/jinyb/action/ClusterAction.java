package com.hundsun.jinyb.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jinyb.crawler.cluster.Cluster;
import com.jinyb.crawler.cluster.WawaCluster;
import com.jinyb.crawler.dao.ClusterConfigDao;
import com.jinyb.crawler.dao.ClusterResultDao;
import com.jinyb.crawler.dao.WordDao;
import com.jinyb.crawler.entity.ClusterConfig;
import com.jinyb.crawler.entity.ClusterHistory;
import com.jinyb.crawler.entity.ClusterResult;
import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.entity.Word;
import com.jinyb.crawler.service.NewsService;
import com.jinyb.crawler.dao.ClusterHistoryDao;

public class ClusterAction extends BaseAction {

	private ClusterConfigDao clusterConfigDao;
	private ClusterHistoryDao clusterHistoryDao;
	private WordDao wordDao;
	private NewsService newsService;
	private ClusterResultDao clusterResultDao;
	/**
	 * @return the clusterConfigDao
	 */
	public ClusterConfigDao getClusterConfigDao() {
		return clusterConfigDao;
	}
	/**
	 * @param clusterConfigDao the clusterConfigDao to set
	 */
	public void setClusterConfigDao(ClusterConfigDao clusterConfigDao) {
		this.clusterConfigDao = clusterConfigDao;
	}
	/**
	 * @return the clusterHistoryDao
	 */
	public ClusterHistoryDao getClusterHistoryDao() {
		return clusterHistoryDao;
	}
	/**
	 * @param clusterHistoryDao the clusterHistoryDao to set
	 */
	public void setClusterHistoryDao(ClusterHistoryDao clusterHistoryDao) {
		this.clusterHistoryDao = clusterHistoryDao;
	}
	/**
	 * @return the wordDao
	 */
	public WordDao getWordDao() {
		return wordDao;
	}
	/**
	 * @param wordDao the wordDao to set
	 */
	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}
	/**
	 * @return the newsService
	 */
	public NewsService getNewsService() {
		return newsService;
	}
	/**
	 * @param newsService the newsService to set
	 */
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	/**
	 * @return the clusterResultDao
	 */
	public ClusterResultDao getClusterResultDao() {
		return clusterResultDao;
	}
	/**
	 * @param clusterResultDao the clusterResultDao to set
	 */
	public void setClusterResultDao(ClusterResultDao clusterResultDao) {
		this.clusterResultDao = clusterResultDao;
	}
	public String startCluster()
	{
		String msg="";
		List<ClusterConfig> clusterConfigList=clusterConfigDao.queryByConditions("from ClusterConfig c where c.present=?", new Object[]{true});
		if(clusterConfigList.size()!=1)
		{
			msg="û���ҵ��������û��߾������ò�Ψһ";
			return "error";
		}
		ClusterConfig clusterConfig=clusterConfigList.get(0);//get the current config
		
		Cluster cluster=new Cluster();
		cluster.setClusterConfig(clusterConfig);
		
		List<News> newsList=newsService.ListNewsByConditon("from News n where n.newsPublishtime>? and n.newsPublishtime<?", new Object[]{clusterConfig.getClcStartTime(),clusterConfig.getClcEndTime()});
		//��������ڵ�����
		cluster.setNewsList(newsList);
		
		cluster.start();//ִ�о������
		
		Map<Integer,Integer> idsMap=cluster.getIdsMap();//��ö�Ӧidmap
		WawaCluster[] clusters=cluster.getCluster();//get the clusters
		Word[] word=cluster.getWord();//get the word
		
		//����һ��������ʷ
		List<ClusterHistory> currentHistoryList=clusterHistoryDao.queryByConditions("from ClusterHistory c where c.present=?", new Object[]{true});
		if(currentHistoryList.size()>1)
		{
			msg="������ʷ��Ψһ";
			return "error";
		}
		else if(currentHistoryList.size()==1)
		{
			ClusterHistory currentHistory=currentHistoryList.get(0);
			currentHistory.setPresent(false);
			clusterHistoryDao.update(currentHistory);
		}
		
		
		
		ClusterHistory clusterHistory=new ClusterHistory();
		clusterHistory.setChAId(1);
		clusterHistory.setChTime(new Timestamp(new Date().getTime()));
		clusterHistory.setChWebpageNum(clusterConfig.getClcwebPageNum());
		clusterHistory.setChCclId(clusterConfig.getId());
		clusterHistory.setPresent(true);
		
		
		
		clusterHistoryDao.save(clusterHistory);//���������ʷ
		
		//��õ�ǰ���õ�id
		ClusterHistory presentHistory=clusterHistoryDao.queryByConditions("from ClusterHistory c where c.present=?", new Object[]{true}).get(0);
		int clusterVersion=presentHistory.getId();
		
		
		
		//ִ��clusterResult�����
		for(WawaCluster clu:clusters)
		{
			ClusterResult clusterResult=new ClusterResult();
			clusterResult.setcTitle(clu.getThemeWord());
			clusterResult.setChId(clusterVersion);//�趨������ʷ��¼
			List<Integer> member=clu.getCurrentMembership();
			Set<News> newsSet=new HashSet<News>();
			for(Integer m:member)
			{
				News n=new News();
				n.setNewsId(idsMap.get(m));
				newsSet.add(n);
			}
			clusterResult.setNews(newsSet);
			clusterResultDao.save(clusterResult);//�������Ľ��
		}
		//ִ��word������Ӳ���
		for(Word w:word)
		{
			List<Integer> member=w.getIds();
			Set<News> newsSet=new HashSet<News>();
			for(Integer m:member)
			{
				News n=new News();
				n.setNewsId(idsMap.get(m));
				newsSet.add(n);
			}
			w.setWhId(clusterVersion);
			w.setNews(newsSet);
			w.setNewsNum(newsSet.size());
			wordDao.save(w);//�����
		}

		return "clusterOver";
	}

}
