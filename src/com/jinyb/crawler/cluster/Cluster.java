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
	private ClusterConfig clusterConfig;//�����������Ϣ
	private List<News> newsList;//���������ĵ�����
	private WawaCluster[] cluster;//������ 
	private Word[] word;//����ʵĽ��
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

	
   // System.out.println(tf.get_numTerms());//������������ĸ���
    public void start() {
    	
    	String[] docs=new String[newsList.size()];
    	idsMap=new HashMap<Integer,Integer>();
    	for(int i=0;i<newsList.size();i++)
    	{
    		idsMap.put(i, newsList.get(i).getNewsId());
    		docs[i]=newsList.get(i).getNewsContent();
    	}
    	//2����ʼ��TFIDF����������������ÿ���ĵ���TFIDFȨ��
       TFIDFMeasure tf = new TFIDFMeasure(docs, new Tokeniser());
       word=AgrithUtil.getHotWord(tf);//�γ�word��¼
	// TODO Auto-generated method stub
	   int K = clusterConfig.getClcClusterNum(); //�۳�3������

	    //3������k-means���������ݣ���һ���������飬��һά��ʾ�ĵ�������
	    //�ڶ�ά��ʾ�����ĵ��ֳ��������д�
	    double[][] data = new double[docs.length][];
	    int docCount = docs.length; //�ĵ�����
	    int dimension = tf.get_numTerms();//���дʵ���Ŀ
	    for (int i = 0; i < docCount; i++)
	    {
	       // for (int j = 0; j < dimension; j++)//�����ѭ����ȫû��������ѽ��()
	       // {
	            data[i] = tf.GetTermVector2(i); //��ȡ��i���ĵ���TFIDFȨ������
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
	    //4����ʼ��k-means�㷨����һ��������ʾ�������ݣ��ڶ���������ʾҪ�۳ɼ�����
	    WawaKMeans kmeans = new WawaKMeans(data, K);
	    //5����ʼ����
	    kmeans.Start();
	    
	    

	    //6����ȡ�����������
	    cluster = kmeans.getClusters();
	    for(WawaCluster clu : cluster){
	    	AgrithUtil.getThreeWord(tf, clu);//���������
	    
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
