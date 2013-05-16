/**
 * 
 */
package com.hundsun.jinyb.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.hundsun.jinyb.searchIndex.MyIndex;
import com.hundsun.jinyb.searchIndex.MySearch;
import com.jinyb.crawler.dao.IndexConfigDao;
import com.jinyb.crawler.dao.NewsDao;
import com.jinyb.crawler.entity.IndexConfig;
import com.jinyb.crawler.entity.News;

/**
 * @author Administrator
 *
 */
public class MyIndexAction extends BaseAction {
	private IndexConfigDao indexConfigDao;
	private NewsDao newsDao;

	public IndexConfigDao getIndexConfigDao() {
		return indexConfigDao;
	}

	public void setIndexConfigDao(IndexConfigDao indexConfigDao) {
		this.indexConfigDao = indexConfigDao;
	}
	public String startIndex()
	{
		String msg="";
		IndexConfig indexConfig=null;
		List<IndexConfig> indexConfigList=indexConfigDao.queryByConditions("from IndexConfig i where i.present=?", new Object[]{true});
		if(indexConfigList.size()!=1)
		{
			msg="配置不正确";
			httpRequest.setAttribute("msg", msg);
			return "error";
		}
		else
		{
			indexConfig=indexConfigList.get(0);
		}
		File f=new File(indexConfig.getDir());
		Directory directory=null;
		try {
			directory=FSDirectory.open(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<News> newsList=newsDao.queryByConditions("from News n where n.newsPublishtime>? and n.newsPublishtime<?", new Object[]{indexConfig.getStartTime(),indexConfig.getEndTime()});
		
		MyIndex myIndex=new MyIndex();
		myIndex.setNewsList(newsList);
		myIndex.setDirectory(directory);
		myIndex.index();
		
		MySearch mySearch=new MySearch();
		mySearch.setDirectory(directory);
		mySearch.searchFile();
		
		try {
			directory.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//关闭字典
		
		return "myIndexOver";
		
	}

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

}
