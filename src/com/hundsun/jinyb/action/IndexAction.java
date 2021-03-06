/**
 * 
 */
package com.hundsun.jinyb.action;

import java.util.List;

import com.jinyb.crawler.constants.Constant;
import com.jinyb.crawler.dao.ArticleDao;
import com.jinyb.crawler.dao.ClusterConfigDao;
import com.jinyb.crawler.dao.ClusterHistoryDao;
import com.jinyb.crawler.dao.ClusterResultDao;
import com.jinyb.crawler.dao.ColumnDao;
import com.jinyb.crawler.dao.WebConfigDao;
import com.jinyb.crawler.dao.WordDao;
import com.jinyb.crawler.entity.Article;
import com.jinyb.crawler.entity.ClusterConfig;
import com.jinyb.crawler.entity.ClusterHistory;
import com.jinyb.crawler.entity.ClusterResult;
import com.jinyb.crawler.entity.Column;
import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.entity.WebConfig;
import com.jinyb.crawler.entity.Word;
import com.jinyb.crawler.service.NewsService;

/**
 * @author jinyb09017
 *
 */
public class IndexAction extends BaseAction{
	private NewsService newsService;
	private WordDao wordDao;
	private ClusterResultDao clusterResultDao;
	private ClusterHistoryDao clusterHistoryDao;
	private ClusterConfigDao clusterConfigDao;
	private WebConfigDao webConfigDao;
	private ArticleDao articleDao;
	private ColumnDao columnDao;
	private Article article;
	
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public ArticleDao getArticleDao() {
		return articleDao;
	}
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	public ColumnDao getColumnDao() {
		return columnDao;
	}
	public void setColumnDao(ColumnDao columnDao) {
		this.columnDao = columnDao;
	}
	public ClusterConfigDao getClusterConfigDao() {
		return clusterConfigDao;
	}
	public void setClusterConfigDao(ClusterConfigDao clusterConfigDao) {
		this.clusterConfigDao = clusterConfigDao;
	}
	public String index()
	{
		List<ClusterHistory> clusterHistoryList=clusterHistoryDao.queryByConditions("from ClusterHistory c where c.present=?", new Object[]{true});
		if(clusterHistoryList.size()!=0)
		{
			Constant.CLUSTER_HISTORY_VESION=clusterHistoryList.get(0).getId();//设置当前历史
		}
		
		
		
		
		//get the cluster result
		List<ClusterResult> clusterResultList=clusterResultDao.queryByConditions("from ClusterResult c where c.chId=?",new Object[]{Constant.CLUSTER_HISTORY_VESION});
		httpRequest.setAttribute("clusterResultList", clusterResultList);
		
		List<Word> wordList=wordDao.queryByConditions("from Word w where w.whId=?", new Object[]{Constant.CLUSTER_HISTORY_VESION});
		httpRequest.setAttribute("wordList", wordList);
		
		if(!session.containsKey("webConfig")&&webConfigDao.queryList().size()!=0)
		{
			WebConfig webConfig=webConfigDao.queryList().get(0);
			session.put("webConfig", webConfig);//add to the session as a global var
		}
		
		
		return "index";
	}
	public String listHot()
	{
		ClusterConfig clusterConfig=clusterConfigDao.queryByConditions("from ClusterConfig c where c.present=?", new Object[]{true}).get(0);
		List<Word> wordList=wordDao.queryList("from Word w where w.whId="+Constant.CLUSTER_HISTORY_VESION+" order by w.weight desc",clusterConfig.getClcWordNum() );
		httpRequest.setAttribute("wordList", wordList);
		
		
		List<ClusterResult> clusterResultList=clusterResultDao.queryByConditions("from ClusterResult c where c.chId="+Constant.CLUSTER_HISTORY_VESION, new Object[]{});
		httpRequest.setAttribute("clusterResultList", clusterResultList);
		
		return "listHot";
	}
	
	public NewsService getNewsService() {
		return newsService;
	}
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	public WordDao getWordDao() {
		return wordDao;
	}
	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}
	public ClusterResultDao getClusterResultDao() {
		return clusterResultDao;
	}
	public void setClusterResultDao(ClusterResultDao clusterResultDao) {
		this.clusterResultDao = clusterResultDao;
	}
	public ClusterHistoryDao getClusterHistoryDao() {
		return clusterHistoryDao;
	}
	public void setClusterHistoryDao(ClusterHistoryDao clusterHistoryDao) {
		this.clusterHistoryDao = clusterHistoryDao;
	}
	public WebConfigDao getWebConfigDao() {
		return webConfigDao;
	}
	public void setWebConfigDao(WebConfigDao webConfigDao) {
		this.webConfigDao = webConfigDao;
	}
	

}
