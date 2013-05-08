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
			msg="��ǰ���ò�Ψһ";
			return "error";
			
		}else if(clusterConfigList.size()==1)
		{
			ClusterConfig clusterConf=clusterConfigList.get(0);
			clusterConf.setPresent(false);
			clusterConfigDao.update(clusterConf);//ȡ��֮ǰ��ǰ��
		}
		List<News> newsList=newsService.ListNewsByConditon("from News n where n.newsPublishtime>? and n.newsPublishtime<?", new Object[]{this.clusterConfig.getClcStartTime(),clusterConfig.getClcEndTime()});
		this.clusterConfig.setClcwebPageNum(newsList.size());
		clusterConfig.setClcAId(1);
		clusterConfig.setPresent(true);
		clusterConfig.setRealClusterNum(0);
			
		clusterConfigDao.save(clusterConfig);//�����µĵ�ǰ
		msg="�������óɹ�";
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
		clusterConfigDao.update(clusterConf);//ȡ��֮ǰ��ǰ��

		clusterConfig=clusterConfigDao.queryById(clusterConfig.getId());
		clusterConfig.setPresent(true);
		clusterConfigDao.update(clusterConfig);//�����µĵ�ǰ
		
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
		List<String> word=token.partition("��ǰ���ã��ܺ�����������������Ǽ�ʮ���˷����ѵõ����飬������������ȡ��ϵ��ʽ��Ϊ���ѣ��������л��������ˣ�ȴû���Ҫ����ȥ������ϵ��������Ȼ��Ȼ�س�Ϊ�����ѡ���ǰ��������ȡ�����Ϊĳλϲ�������Ǽ�����ķ�˿��������ֻ��Ҫ��ǩ���գ������ذ��������ھͺá���ǰ��������·����ÿ����Ȥ�ĵط�������Ӱ��������ÿ��ֵ����Ӱ�ĵط����Ῠ��һ�¾ͺ�");
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
