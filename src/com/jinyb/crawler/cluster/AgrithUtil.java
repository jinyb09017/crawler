package com.jinyb.crawler.cluster;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.entity.Word;

public class AgrithUtil {
//	public static void getThreeTopWords(TFIDFMeasure tf,WawaCluster cls)//这个算法存在问题。
//	{
//		List<Integer> member=cls.getCurrentMembership();
//		String hotThreeWord="";
//		@SuppressWarnings("unchecked")
//		List<String> term=tf.get_terms();
//		float weight[]={0,0,0};
//		int num[]={0,0,0};
//		float[][] termWeight=tf.get_termWeight();
//	//	System.out.println("kkk");
//		for(Integer i:member)//获取每个取类中的权重最高的词三个
//		{
//			
//			//System.out.println(i);
//			
//			for(int j=0;j<tf.get_numTerms();j++)
//			{
//				//System.out.print(termWeight[j][i]+" ");
//				if(termWeight[j][i]>weight[0])
//				{
//					float temp1=weight[0];//定义一个中间变量
//					float temp2=weight[1];
//					weight[0]=termWeight[j][i];
//					if(temp1>weight[1])
//					{
//						weight[1]=temp1;
//						if(temp2>weight[2])
//						{
//							weight[2]=temp2;
//							num[2]=num[1];
//						}
//						
//						
//						num[1]=num[0];
//					}
//					
//				//	System.out.println("get a weight big:"+weight[0]);
//					num[0]=j;
//					
//				}
//				else if(termWeight[j][i]>weight[1])
//				{
//					float temp1=weight[1];
//					weight[1]=termWeight[j][i];
//					if(temp1>weight[2])
//					{
//						weight[2]=temp1;
//						num[2]=num[1];
//					}
//					
//				//	System.out.println("get a second big:"+weight[1]);
//					num[1]=j;
//				}
//				else if(termWeight[j][i]>weight[2])
//				{
//					weight[2]=termWeight[j][i];
//				//	System.out.println("get a three big:"+weight[2]);
//					num[2]=j;
//				}
//			}
//			//System.out.println();
//		}
//		System.out.println();
//	//	System.out.println("kkk");
//		for(int i=0;i<num.length;i++)
//		{
//			if(i!=num.length-1)
//			hotThreeWord+=term.get(num[i])+" ";
//			else
//				hotThreeWord+=term.get(num[i]);
//		}
//		cls.themeWord=hotThreeWord;
////		for(int i=0;i<num.length;i++)//重新还原数组。记得因为这两个是静态变量
////		{
////			num[i]=0;
////			weight[i]=0;
////		}
//	//	System.out.println(hotThreeWord);
//		
//	}
	public static Word[] getHotWord(TFIDFMeasure tf)
	{
		float[][] termWeight=tf.get_termWeight();
		List<String> term=tf.get_terms();
		int termNum=tf.get_numTerms();//词的数目
		int docNum=termWeight[0].length;//文档的数目
		float[] meanWeight=new float[termNum];
		Word[] wordArray=new Word[termNum];
		for(int i=0;i<termNum;i++)
		{
			Word w=new Word();//每一个词对应一个word
			List<Integer> newsId=new ArrayList<Integer>();
			w.setIds(newsId);
			w.setWord(term.get(i));
			for(int j=0;j<docNum;j++)
			{
				if(termWeight[i][j]!=0)
				{
					w.getIds().add(j);//记录下对应的id
					meanWeight[i]+=termWeight[i][j];//词的每一列相加
				}
				
				
				
			}
			w.setWeight(meanWeight[i]);//加上权重值
			wordArray[i]=w;//增加到数组中
		}
		for(int i=0;i<termNum;i++)//按word的weight给word进行排序,这里使用的交换排序算法
		{
			for(int j=i+1;j<termNum;j++)//好像是冒泡的思路
			{
				Word tem;
				if(wordArray[j].getWeight()>wordArray[i].getWeight())
				{
					tem=wordArray[i];
					wordArray[i]=wordArray[j];
					wordArray[j]=tem;
				}
			}
		}//得到排完序的wordArray
		for(int i=0;i<wordArray.length;i++)
			System.out.println(wordArray[i]);
		System.out.println("word num:"+wordArray.length);
		return wordArray;
		
	}
	public static void getThreeWord(TFIDFMeasure tf,WawaCluster cluster)
	{
		float[][] termWeight=tf.get_termWeight();
		List<String> term=tf.get_terms();
		int termNum=tf.get_numTerms();//词的数目
		List<Integer> member=cluster.getCurrentMembership();
		float[] meanWeight=new float[termNum];
		Word[] wordArray=new Word[termNum];
		String themeWord="";
		for(int i=0;i<termNum;i++)
		{
			Word w=new Word();//每一个词对应一个word
			List<Integer> newsId=new ArrayList<Integer>();
			w.setIds(newsId);
			w.setWord(term.get(i));
			for(Integer j:member)
			{
				if(termWeight[i][j]!=0)
				{
					w.getIds().add(j);//记录下对应的id
					meanWeight[i]+=termWeight[i][j];//词的每一列相加
				}
				
				
				
			}
			w.setWeight(meanWeight[i]);//加上权重值
			wordArray[i]=w;//增加到数组中
		}
		for(int i=0;i<termNum;i++)//按word的weight给word进行排序,这里使用的交换排序算法
		{
			for(int j=i+1;j<termNum;j++)//好像是冒泡的思路
			{
				Word tem;
				if(wordArray[j].getWeight()>wordArray[i].getWeight())
				{
					tem=wordArray[i];
					wordArray[i]=wordArray[j];
					wordArray[j]=tem;
				}
			}
		}//得到排完序的wordArray
		for(int i=0;i<3;i++)//得到三个词就0k了
		{
			if(i!=3)
				themeWord+=wordArray[i].getWord()+" ";
			else
				themeWord+=wordArray[i];
		}
		cluster.setThemeWord(themeWord);
		
		
	}
	

}