package com.jinyb.crawler.cluster;

import java.util.Dictionary;
import java.util.Hashtable;


    /// <summary>
    /// 用于移除停止词
    /// </summary>
	public class StopWordsHandler
	{		
		public static String[] stopWordsList=new String[] {"的",
            "我们","要","自己","之","将","“","”","，","（","）","后","应","到","某","后",
            "个","是","位","新","一","两","在","中","或","有","更","好"
		} ;

		private static Hashtable _stopwords=null;//哈希表来保存信用词的权值。

		public static Object AddElement(Dictionary collection,Object key, Object newValue)
		{
			Object element = collection.get(key);
			collection.put(key, newValue);
			return element;
		}//赋权的一个方法吧。

		public static boolean IsStopword(String str)
		{//判断是不是停用词
			
			//int index=Array.BinarySearch(stopWordsList, str)
			return _stopwords.containsKey(str.toLowerCase());
		}
	

		static  //加载静态的方法，给所有的信用词赋值都为0
		{
			if (_stopwords == null)
			{
				_stopwords = new Hashtable();
				double dummy = 0;
				for(String word:stopWordsList){
					_stopwords.put(word, dummy);//赋值都是不0的。
				}
				/*foreach (String word in stopWordsList)
				{
					AddElement(_stopwords, word, dummy);
				}*/
			}
		}
		public static void main(String args[])
		{//输出的测试的
			for(String world:stopWordsList)
				System.out.println(world);
		}
	}

