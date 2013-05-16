/**
 * 
 */
package com.hundsun.jinyb.searchIndex;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.jinyb.crawler.analyzer.ICTCLASAnalyzer;
import com.jinyb.crawler.dao.NewsDao;
import com.jinyb.crawler.entity.IndexConfig;
import com.jinyb.crawler.entity.News;

/**
 * @author Administrator
 *
 */
public class MyIndex {
	
	
	private List<News> newsList;//means the url;
	private Directory directory;
	
	
	
	@SuppressWarnings("deprecation")
	public void index()
	{
		
		
		
		IndexWriter iwriter =null;
		try {
			
			iwriter = new IndexWriter(directory, new ICTCLASAnalyzer(), true,
	                new IndexWriter.MaxFieldLength(25000));
			for(News news:newsList)
			{
				 Document doc = new Document();
        		 doc.add(new Field("content", news.getNewsContent(), Field.Store.NO,
        			        Field.Index.ANALYZED));//不存储，索引，分词
        		 doc.add(new Field("id", news.getNewsId()+"", Field.Store.YES,
     			        Field.Index.NO));//存储，不分词，不索引
        		 doc.add(new Field("title", news.getNewsTitle(), Field.Store.YES,
     			        Field.Index.ANALYZED));//存储，分词，索引
        		 doc.add(new Field("publishTime", news.getNewsPublishtime()+"", Field.Store.NO,
     			        Field.Index.NOT_ANALYZED));//索引但不分词
        		 doc.add(new Field("newsResource", news.getNewsResource(), Field.Store.NO,
     			        Field.Index.NOT_ANALYZED));//不存储 索引 不分词
        		 iwriter.addDocument(doc);
        		 
			}
			iwriter.close();//close the indexWrite
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<News> getNewsList() {
		return newsList;
	}
	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
	public Directory getDirectory() {
		return directory;
	}
	public void setDirectory(Directory directory) {
		this.directory = directory;
	}

}
