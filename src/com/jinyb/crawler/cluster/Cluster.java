/**
 * 
 */
package com.jinyb.crawler.cluster;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jinyb.crawler.entity.ClusterConfig;
import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.entity.Word;
/**
 * @author jinyb09017
 *
 */
public class Cluster {
	private ClusterConfig clusterConfig;//聚类的配置信息
	private List<News> newsList;//聚类输入文档集合
	private WawaCluster[] cluster;//聚类结果 
	private Word[] word;//聚类词的结果
	/**
	 * @return the clusterConfig
	 */
	public ClusterConfig getClusterConfig() {
		return clusterConfig;
	}
	/**
	 * @param clusterConfig the clusterConfig to set
	 */
	public void setClusterConfig(ClusterConfig clusterConfig) {
		this.clusterConfig = clusterConfig;
	}
	/**
	 * @return the newsList
	 */
	public List<News> getNewsList() {
		return newsList;
	}
	/**
	 * @param newsList the newsList to set
	 */
	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
	/**
	 * @return the idsMap
	 */
	public Map<Integer, Integer> getIdsMap() {
		return idsMap;
	}
	/**
	 * @param idsMap the idsMap to set
	 */
	public void setIdsMap(Map<Integer, Integer> idsMap) {
		this.idsMap = idsMap;
	}
	private Map<Integer,Integer> idsMap;


    /**
	 * @return the docs
	 */

	
   // System.out.println(tf.get_numTerms());//输出有特征集的个数
    public void start() {
    	
    	String[] docs=new String[newsList.size()];
    	idsMap=new HashMap<Integer,Integer>();
    	for(int i=0;i<newsList.size();i++)
    	{
    		idsMap.put(i, newsList.get(i).getNewsId());
    		docs[i]=newsList.get(i).getNewsContent();
    	}
    	//2、初始化TFIDF测量器，用来生产每个文档的TFIDF权重
       TFIDFMeasure tf = new TFIDFMeasure(docs, new Tokeniser());
       word=AgrithUtil.getHotWord(tf);//形成word记录
	// TODO Auto-generated method stub
	   int K = clusterConfig.getClcClusterNum(); //聚成3个聚类

	    //3、生成k-means的输入数据，是一个联合数组，第一维表示文档个数，
	    //第二维表示所有文档分出来的所有词
	    double[][] data = new double[docs.length][];
	    int docCount = docs.length; //文档个数
	    int dimension = tf.get_numTerms();//所有词的数目
	    for (int i = 0; i < docCount; i++)
	    {
	       // for (int j = 0; j < dimension; j++)//这个内循环完全没有起作用呀。()
	       // {
	            data[i] = tf.GetTermVector2(i); //获取第i个文档的TFIDF权重向量
	       // }
	    }
//	    for(int i=0;i<docCount;i++)
//	    {
//	    	for(int j=0;j<dimension;j++)
//	    	{
//	    		System.out.print(data[i][j]+" ");
//	    	}
//	    	System.out.println();
//	    }
	    //4、初始化k-means算法，第一个参数表示输入数据，第二个参数表示要聚成几个类
	    WawaKMeans kmeans = new WawaKMeans(data, K);
	    //5、开始迭代
	    kmeans.Start();
	    
	    

	    //6、获取聚类结果并输出
	    cluster = kmeans.getClusters();
	    for(WawaCluster clu : cluster){
	    	AgrithUtil.getThreeWord(tf, clu);//生成主题词
	    
	    }
	  

}
	public WawaCluster[] getCluster() {
		return cluster;
	}
	public void setCluster(WawaCluster[] cluster) {
		this.cluster = cluster;
	}
	public Word[] getWord() {
		return word;
	}
	public void setWord(Word[] word) {
		this.word = word;
	}

	

}
