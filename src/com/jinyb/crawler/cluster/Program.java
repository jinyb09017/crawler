package com.jinyb.crawler.cluster;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public  class Program
    {
       public static void main(String[] args) throws IOException
        {
            //1、获取文档输入
            String[] docs = getInputDocs("G:/Cluster/src/textcluster/input.txt");
           // String[] docs=getInputDocsFromFloder();//定义一个获取文件夹下的文本文档，来进行聚类。
            
            if (docs.length < 1)
            {
                //System.out.println("没有文档输入");
                System.in.read();
              //  System.exit(0);
                return;
            }
            /*else{
            	for(String s:docs){
            		System.out.println(s);
            	}
            }*/

            //2、初始化TFIDF测量器，用来生产每个文档的TFIDF权重
            TFIDFMeasure tf = new TFIDFMeasure(docs, new Tokeniser());
            System.out.println(tf.get_numTerms());//输出有特征集的个数
          

            int K = 4; //聚成3个聚类

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
            for(int i=0;i<docCount;i++)
            {
            	for(int j=0;j<dimension;j++)
            	{
            		System.out.print(data[i][j]+" ");
            	}
            	System.out.println();
            }
            //4、初始化k-means算法，第一个参数表示输入数据，第二个参数表示要聚成几个类
            WawaKMeans kmeans = new WawaKMeans(data, K);
            //5、开始迭代
            kmeans.Start();
            
            

            //6、获取聚类结果并输出
            WawaCluster[] clusters = kmeans.getClusters();
            for(WawaCluster cluster : clusters){
            //	AgrithUtil.getThreeTopWords(tf, cluster);

                List<Integer> members =  cluster.getCurrentMembership();
                System.out.println("-----------------"+cluster.themeWord);
//                for (int i : members)
//                {
//                	System.out.println(                            docs[i]);
//                }

            
            }
            /*foreach (WawaCluster cluster in clusters)
            {
                List<int> members = cluster.CurrentMembership;
                Console.WriteLine("-----------------");
                foreach (int i in members)
                {
                    Console.WriteLine(docs[i]);
                }

            }*/
           // System.in.read();
           // Console.Read();
        }

        /// <summary>
        /// 获取文档输入
        /// </summary>
        /// <returns></returns>
        private static String[] getInputDocs(String file)
        {//将文档按行转成数组
            List<String> ret = new ArrayList<String>();
            
            try
            {
                BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                {
                    String temp;
                    while ((temp = br.readLine()) != null)
                    {
                        ret.add(temp);
                    }
                }
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            String[] fileString=new String[ret.size()];
            return (String[]) ret.toArray(fileString);
        }
        private static String[] getInputDocsFromFolder()
        {
        	List<String> ret=new ArrayList<String>();
        	return (String[])ret.toArray();
        	        	       	
        }
    }
