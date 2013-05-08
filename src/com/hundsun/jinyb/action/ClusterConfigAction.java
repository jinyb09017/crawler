package com.hundsun.jinyb.action;

import com.jinyb.crawler.cluster.Tokeniser;
import com.jinyb.crawler.dao.ClusterConfigDao;
import com.jinyb.crawler.entity.ClusterConfig;
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



public class ClusterConfigAction extends BaseAction {
	private ClusterConfigDao clusterConfigDao;
	private ClusterConfig clusterConfig;
	private NewsService newsService;
	


	public ClusterConfigDao getClusterConfigDao() {
		return clusterConfigDao;
	}

	public void setClusterConfigDao(ClusterConfigDao clusterConfigDao) {
		this.clusterConfigDao = clusterConfigDao;
	}
	public String List()
	{
		List<ClusterConfig> clusterConfigList=clusterConfigDao.queryList();
		httpRequest.setAttribute("clusterConfigList", clusterConfigList);
		return "list";
	}
	
	public String add()
	{
		String msg="";
		System.out.println(this.clusterConfig);
		
		List<ClusterConfig> clusterConfigList=clusterConfigDao.queryByConditions("from ClusterConfig c where c.present=?", new Object[]{true});
		if(clusterConfigList.size()>1)
		{
			msg="当前配置不唯一";
			return "error";
			
		}else if(clusterConfigList.size()==1)
		{
			ClusterConfig clusterConf=clusterConfigList.get(0);
			clusterConf.setPresent(false);
			clusterConfigDao.update(clusterConf);//取消之前当前的
		}
		List<News> newsList=newsService.ListNewsByConditon("from News n where n.newsPublishtime>? and n.newsPublishtime<?", new Object[]{this.clusterConfig.getClcStartTime(),clusterConfig.getClcEndTime()});
		this.clusterConfig.setClcwebPageNum(newsList.size());
		clusterConfig.setClcAId(1);
		clusterConfig.setPresent(true);
		clusterConfig.setRealClusterNum(0);
			
		clusterConfigDao.save(clusterConfig);//设置新的当前
		msg="增加配置成功";
		httpRequest.setAttribute("msg", msg);
		httpRequest.setAttribute("clusterNum", newsList.size());

		return "add";
	}
	public String detail()
	{
		ClusterConfig clusterConf=clusterConfigDao.queryById(clusterConfig.getId());
		httpRequest.setAttribute("clusterConfig", clusterConf);
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

	public String list()
	{
		List<ClusterConfig> clusterConfigList=clusterConfigDao.queryList();
		httpRequest.setAttribute("clusterConfigList", clusterConfigList);
		return "list";
	}
	public String present()
	{
		ClusterConfig clusterConf=clusterConfigDao.queryByConditions("from ClusterConfig c where c.present=?", new Object[]{true}).get(0);
		clusterConf.setPresent(false);
		clusterConfigDao.update(clusterConf);//取消之前当前的

		clusterConfig=clusterConfigDao.queryById(clusterConfig.getId());
		clusterConfig.setPresent(true);
		clusterConfigDao.update(clusterConfig);//设置新的当前
		
		return "present";
		
	}
	public String moniter()
	{
		ClusterConfig clusterConfig=clusterConfigDao.queryByConditions("from ClusterConfig c where c.present=?", new Object[]{true}).get(0);
		httpRequest.setAttribute("clusterConfig", clusterConfig);
		
		return "moniter";
		
	}
	public String startCluster()
	{
		System.out.println(System.getProperty("java.library.path"));
		Tokeniser token=new Tokeniser();
		List<String> word=token.partition("以前觉得，能和外国人流利地聊天是件十分兴奋又难得的事情，甚至想主动索取联系方式成为朋友，后来，有机会聊天了，却没想过要刻意去保持联系，反而自然而然地成为了朋友。以前，好想争取机会成为某位喜欢的明星记忆里的粉丝，后来，只想要张签名照，端正地摆在视线内就好。以前，很想在路过的每个有趣的地方留下身影，后来，每个值得留影的地方轻轻卡擦一下就好");
		for(String i:word)
			System.out.print(i+" ");
				token.close();
		return "cluster";
	}


	public ClusterConfig getClusterConfig() {
		return clusterConfig;
	}

	public void setClusterConfig(ClusterConfig clusterConfig) {
		this.clusterConfig = clusterConfig;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

}
