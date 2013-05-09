package com.hundsun.jinyb.action;


import com.jinyb.crawler.dao.IndexConfigDao;
import com.jinyb.crawler.entity.IndexConfig;
import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.service.NewsService;


import java.io.File;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;



public class IndexConfigAction extends BaseAction {
	private IndexConfigDao indexConfigDao;
	private IndexConfig indexConfig;
	private NewsService newsService;
	


	public IndexConfigDao getIndexConfigDao() {
		return indexConfigDao;
	}

	public void setIndexConfigDao(IndexConfigDao indexConfigDao) {
		this.indexConfigDao = indexConfigDao;
	}
	public String List()
	{
		List<IndexConfig> indexConfigList=indexConfigDao.queryList();
		httpRequest.setAttribute("indexConfigList", indexConfigList);
		return "list";
	}
	
	public String add()
	{
		String msg="";
		System.out.println(this.indexConfig);
		
		String dir=this.indexConfig.getDir();
		if(dir.equals(""))
		{
			
			dir=this.getClass().getClassLoader().getResource("").getPath();
			System.out.println(dir);
			this.indexConfig.setDir(dir);
		}
		File f=new File(dir);
		if(!f.exists())
		{
			msg="目录不存在";
			httpRequest.setAttribute("msg", msg);
			return "error";
		}
		
		List<IndexConfig> indexConfigList=indexConfigDao.queryByConditions("from IndexConfig c where c.present=?", new Object[]{true});
		System.err.println("================"+indexConfigList.size());
		if(indexConfigList.size()>1)
		{
			msg="当前配置不唯一";
			httpRequest.setAttribute("msg", msg);
			return "error";
			
		}else if(indexConfigList.size()==1)
		{
			IndexConfig indexConf=indexConfigList.get(0);
			indexConf.setPresent(false);
			indexConfigDao.update(indexConf);//取消之前当前的
		}
		List<News> newsList=newsService.ListNewsByConditon("from News n where n.newsPublishtime>? and n.newsPublishtime<?", new Object[]{this.indexConfig.getStartTime(),indexConfig.getEndTime()});
		this.indexConfig.setWebpagenum(newsList.size());
		indexConfig.setAid(1);
		indexConfig.setPresent(true);
		indexConfig.setOverRide(false);
		
			
		indexConfigDao.save(indexConfig);//设置新的当前
		msg="增加配置成功";
		httpRequest.setAttribute("msg", msg);
		httpRequest.setAttribute("indexNum", newsList.size());

		return "add";
	}
	public String detail()
	{
		IndexConfig indexConf=indexConfigDao.queryById(indexConfig.getId());
		httpRequest.setAttribute("indexConfig", indexConf);
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
		List<IndexConfig> indexConfigList=indexConfigDao.queryList();
		httpRequest.setAttribute("indexConfigList", indexConfigList);
		return "list";
	}
	public String present()
	{
		IndexConfig indexConf=indexConfigDao.queryByConditions("from IndexConfig c where c.present=?", new Object[]{true}).get(0);
		indexConf.setPresent(false);
		indexConfigDao.update(indexConf);//取消之前当前的

		indexConfig=indexConfigDao.queryById(indexConfig.getId());
		indexConfig.setPresent(true);
		indexConfigDao.update(indexConfig);//设置新的当前
		
		return "present";
		
	}
	public String moniter()
	{
		IndexConfig indexConfig=indexConfigDao.queryByConditions("from IndexConfig c where c.present=?", new Object[]{true}).get(0);
		httpRequest.setAttribute("indexConfig", indexConfig);
		
		return "moniter";
		
	}
	


	public IndexConfig getIndexConfig() {
		return indexConfig;
	}

	public void setIndexConfig(IndexConfig indexConfig) {
		this.indexConfig = indexConfig;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

}
