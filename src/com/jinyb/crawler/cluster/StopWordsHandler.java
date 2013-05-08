package com.jinyb.crawler.cluster;

import java.util.Dictionary;
import java.util.Hashtable;


    /// <summary>
    /// �����Ƴ�ֹͣ��
    /// </summary>
	public class StopWordsHandler
	{		
		public static String[] stopWordsList=new String[] {"��",
            "����","Ҫ","�Լ�","֮","��","��","��","��","��","��","��","Ӧ","��","ĳ","��",
            "��","��","λ","��","һ","��","��","��","��","��","��","��"
		} ;

		private static Hashtable _stopwords=null;//��ϣ�����������ôʵ�Ȩֵ��

		public static Object AddElement(Dictionary collection,Object key, Object newValue)
		{
			Object element = collection.get(key);
			collection.put(key, newValue);
			return element;
		}//��Ȩ��һ�������ɡ�

		public static boolean IsStopword(String str)
		{//�ж��ǲ���ͣ�ô�
			
			//int index=Array.BinarySearch(stopWordsList, str)
			return _stopwords.containsKey(str.toLowerCase());
		}
	

		static  //���ؾ�̬�ķ����������е����ôʸ�ֵ��Ϊ0
		{
			if (_stopwords == null)
			{
				_stopwords = new Hashtable();
				double dummy = 0;
				for(String word:stopWordsList){
					_stopwords.put(word, dummy);//��ֵ���ǲ�0�ġ�
				}
				/*foreach (String word in stopWordsList)
				{
					AddElement(_stopwords, word, dummy);
				}*/
			}
		}
		public static void main(String args[])
		{//����Ĳ��Ե�
			for(String world:stopWordsList)
				System.out.println(world);
		}
	}

