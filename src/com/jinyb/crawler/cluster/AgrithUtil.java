package com.jinyb.crawler.cluster;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.entity.Word;

public class AgrithUtil {
//	public static void getThreeTopWords(TFIDFMeasure tf,WawaCluster cls)//����㷨�������⡣
//	{
//		List<Integer> member=cls.getCurrentMembership();
//		String hotThreeWord="";
//		@SuppressWarnings("unchecked")
//		List<String> term=tf.get_terms();
//		float weight[]={0,0,0};
//		int num[]={0,0,0};
//		float[][] termWeight=tf.get_termWeight();
//	//	System.out.println("kkk");
//		for(Integer i:member)//��ȡÿ��ȡ���е�Ȩ����ߵĴ�����
//		{
//			
//			//System.out.println(i);
//			
//			for(int j=0;j<tf.get_numTerms();j++)
//			{
//				//System.out.print(termWeight[j][i]+" ");
//				if(termWeight[j][i]>weight[0])
//				{
//					float temp1=weight[0];//����һ���м����
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
////		for(int i=0;i<num.length;i++)//���»�ԭ���顣�ǵ���Ϊ�������Ǿ�̬����
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
		int termNum=tf.get_numTerms();//�ʵ���Ŀ
		int docNum=termWeight[0].length;//�ĵ�����Ŀ
		float[] meanWeight=new float[termNum];
		Word[] wordArray=new Word[termNum];
		for(int i=0;i<termNum;i++)
		{
			Word w=new Word();//ÿһ���ʶ�Ӧһ��word
			List<Integer> newsId=new ArrayList<Integer>();
			w.setIds(newsId);
			w.setWord(term.get(i));
			for(int j=0;j<docNum;j++)
			{
				if(termWeight[i][j]!=0)
				{
					w.getIds().add(j);//��¼�¶�Ӧ��id
					meanWeight[i]+=termWeight[i][j];//�ʵ�ÿһ�����
				}
				
				
				
			}
			w.setWeight(meanWeight[i]);//����Ȩ��ֵ
			wordArray[i]=w;//���ӵ�������
		}
		for(int i=0;i<termNum;i++)//��word��weight��word��������,����ʹ�õĽ��������㷨
		{
			for(int j=i+1;j<termNum;j++)//������ð�ݵ�˼·
			{
				Word tem;
				if(wordArray[j].getWeight()>wordArray[i].getWeight())
				{
					tem=wordArray[i];
					wordArray[i]=wordArray[j];
					wordArray[j]=tem;
				}
			}
		}//�õ��������wordArray
		for(int i=0;i<wordArray.length;i++)
			System.out.println(wordArray[i]);
		System.out.println("word num:"+wordArray.length);
		return wordArray;
		
	}
	public static void getThreeWord(TFIDFMeasure tf,WawaCluster cluster)
	{
		float[][] termWeight=tf.get_termWeight();
		List<String> term=tf.get_terms();
		int termNum=tf.get_numTerms();//�ʵ���Ŀ
		List<Integer> member=cluster.getCurrentMembership();
		float[] meanWeight=new float[termNum];
		Word[] wordArray=new Word[termNum];
		String themeWord="";
		for(int i=0;i<termNum;i++)
		{
			Word w=new Word();//ÿһ���ʶ�Ӧһ��word
			List<Integer> newsId=new ArrayList<Integer>();
			w.setIds(newsId);
			w.setWord(term.get(i));
			for(Integer j:member)
			{
				if(termWeight[i][j]!=0)
				{
					w.getIds().add(j);//��¼�¶�Ӧ��id
					meanWeight[i]+=termWeight[i][j];//�ʵ�ÿһ�����
				}
				
				
				
			}
			w.setWeight(meanWeight[i]);//����Ȩ��ֵ
			wordArray[i]=w;//���ӵ�������
		}
		for(int i=0;i<termNum;i++)//��word��weight��word��������,����ʹ�õĽ��������㷨
		{
			for(int j=i+1;j<termNum;j++)//������ð�ݵ�˼·
			{
				Word tem;
				if(wordArray[j].getWeight()>wordArray[i].getWeight())
				{
					tem=wordArray[i];
					wordArray[i]=wordArray[j];
					wordArray[j]=tem;
				}
			}
		}//�õ��������wordArray
		for(int i=0;i<3;i++)//�õ������ʾ�0k��
		{
			if(i!=3)
				themeWord+=wordArray[i].getWord()+" ";
			else
				themeWord+=wordArray[i];
		}
		cluster.setThemeWord(themeWord);
		
		
	}
	

}