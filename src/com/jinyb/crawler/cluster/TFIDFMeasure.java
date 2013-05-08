package com.jinyb.crawler.cluster;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class TFIDFMeasure {

	//term frequency-inverse document frequency
		private String[] _docs;//
		private String[][] _ngramDoc;//二维的文档
		private int _numDocs=0;//the number of the docs
		private int _numTerms=0;//the nummber of terms
		private ArrayList _terms;//list to store the term
		private int[][] _termFreq;//the frequent of the term
		private float[][] _termWeight;//the weight of the term
		private int[] _maxTermFreq;//最大的词频数组
		private int[] _docFreq;//文章频率的数组
		private Dictionary _wordsIndex=new Hashtable() ;

        /**
		 * @return the _termWeight
		 */
		public float[][] get_termWeight() {
			return _termWeight;
		}



		/**
		 * @param _termWeight the _termWeight to set
		 */
		public void set_termWeight(float[][] _termWeight) {
			this._termWeight = _termWeight;
		}



		/**
		 * @return the _wordsIndex
		 */
		public Dictionary get_wordsIndex() {
			return _wordsIndex;
		}



		/**
		 * @param _wordsIndex the _wordsIndex to set
		 */
		public void set_wordsIndex(Dictionary _wordsIndex) {
			this._wordsIndex = _wordsIndex;
		}

		ITokeniser _tokenizer = null;




	    

		public TFIDFMeasure(String[] documents,ITokeniser tokeniser)
		{
			System.out.println("TFIDFMeasure()");
			_docs=documents;
			_numDocs=documents.length ;
		    _tokenizer = tokeniser;
		    System.out.println("start Init()");
			myInit();
		}

	    

	    private void GeneratNgramText()
		{
			
		}

		private ArrayList GenerateTerms(String[] docs)//生成词组
		{//通过循环的算法，取得了所有文档中的不重复的不包含停用词的文档词数组uniques.
			ArrayList uniques=new ArrayList() ;
			_ngramDoc=new String[_numDocs][] ;
			for (int i=0; i < docs.length ; i++)
			{
				System.out.println(i+":start tokenizer!");
				List<String> words=_tokenizer.partition(docs[i]);	
				System.out.println("words.size(): "+words.size());

				for (int j=0; j < words.size(); j++)
					if (!uniques.contains(words.get(j)))				
						uniques.add(words.get(j)) ;
								
			}
			return uniques;
		}
		


//		private static Object AddElement(Dictionary collection, Object key, Object newValue)
//		{//建立一个词的索引吧。
//			Object element=collection.get(key);
//			collection.put(key, newValue);
//			return element;
//		}

		private int GetTermIndex(String term)//特征词的索引
		{
			Object index=_wordsIndex.get(term);
			if (index == null) return -1;
			return (Integer)index;
		}

		private void myInit()
		{
			System.out.println("generate terms……");
			_terms=GenerateTerms (_docs );//_terms为文档集的词汇数组
			System.out.println("after generate ,terms.size()"+_terms.size() );
			_numTerms=_terms.size() ;//_numTerms为文档集特征词的个数。

			_maxTermFreq=new int[_numDocs] ;//最大视频数组
			_docFreq=new int[_numTerms] ;//文章频率
			_termFreq =new int[_numTerms][] ;//词频
			_termWeight=new float[_numTerms][] ;//词的权重

			for(int i=0; i < _terms.size() ; i++)			
			{
				_termWeight[i]=new float[_numDocs] ;
				_termFreq[i]=new int[_numDocs] ;
				_wordsIndex.put(_terms.get(i),i);//这是生成的聚类词库
				System.out.print(_terms.get(i)+" ");

			//	AddElement(_wordsIndex, _terms.get(i), i);//在这里生成一个特征词的索引			
			}
			
			GenerateTermFrequency();
//			for(int i=0;i<_numDocs;i++)
//			{
//				//System.out.print("文本"+i+"的词频");
//				for(int j=0;j<_numTerms;j++)
//				{
//					
//					System.out.print(_termFreq[j][i]+"  ");
//					
//				}
//				System.out.println();
//			}
				
			
			
			GenerateTermWeight();			
				
		}
		
		private float Log(float num)
		{
			return (float) Math.log(num) ;//log2
		}

		private void GenerateTermFrequency()//生成词频
		{
			for(int i=0; i < _numDocs  ; i++)
			{								
				String curDoc=_docs[i];
				Dictionary freq=GetWordFrequency(curDoc);//获得了文档的词的个数对应的集合
				Enumeration enums=freq.keys();
				
				while(enums.hasMoreElements()){
					String word=(String) enums.nextElement();
					int wordFreq=(Integer)freq.get(word);//某个单词的频数
					int termIndex=GetTermIndex(word);//判断每个词是否存在于特征词中
                    if(termIndex == -1)
                        continue;//重新开始循环
					_termFreq [termIndex][i]=wordFreq;//文档i的词的频数,并且与索引序列对应
					_docFreq[termIndex] ++;//表示包含该词的文档增加一个

					if (wordFreq > _maxTermFreq[i]) _maxTermFreq[i]=wordFreq;	//统计某个文档的最大的词频数（并作为分母来处理）
				}
				//DictionaryEnumerator enums=freq.GetEnumerator() ;
			//	_maxTermFreq[i]=Integer.MIN_VALUE ;
				/*freq.elements()
				Object ele=null;
				while ((ele=enums.nextElement())!=null)
				{
					
					String word=(String)ele.
					int wordFreq=(int)enums.Value ;
					int termIndex=GetTermIndex(word);
                    if(termIndex == -1)
                        continue;
					_termFreq [termIndex][i]=wordFre/q;
					_docFreq[termIndex] ++;

					if (wordFreq > _maxTermFreq[i]) _maxTermFreq[i]=wordFreq;					
				}*/
			}
		}
		

		/**
		 * @return the _terms
		 */
		public ArrayList get_terms() {
			return _terms;
		}



		/**
		 * @param _terms the _terms to set
		 */
		public void set_terms(ArrayList _terms) {
			this._terms = _terms;
		}



		private void GenerateTermWeight()
		{			
			for(int i=0; i < _numTerms   ; i++)
			{
				for(int j=0; j < _numDocs ; j++)				
					_termWeight[i][j]=ComputeTermWeight (i, j);				
			}
		}

		private float GetTermFrequency(int term, int doc)
		{			
			int freq=_termFreq [term][doc];
			int maxfreq=_maxTermFreq[doc];			
		//	System.out.println("frep="+freq+"   maxfrep="+maxfreq);
		//	System.out.println((float) freq/(float)maxfreq);
			return ( (float) freq/(float)maxfreq );
		}

		private float GetInverseDocumentFrequency(int term)
		{
			int df=_docFreq[term];
			//System.out.println("it is less 0"+Log((float) (_numDocs) / (float) df ));
			return Log((float) (_numDocs) / (float) df );
		}

		private float ComputeTermWeight(int term, int doc)
		{
			float tf=GetTermFrequency (term, doc);
			float idf=GetInverseDocumentFrequency(term);
			return tf * idf;//获得termweight，没有做底下分母的处理。
		}
		
		private  float[] GetTermVector(int doc)
		{
			float[] w=new float[_numTerms] ; 
			for (int i=0; i < _numTerms; i++)											
				w[i]=_termWeight[i][doc];
			
				
			return w;//获得文本的权重向量数
		}
        public double [] GetTermVector2(int doc)//转换成double
        {
            double [] ret = new double[_numTerms];
            float[] w = GetTermVector(doc);
            for (int i = 0; i < ret.length; i++ )
            {
                ret[i] = w[i];
            }
//            System.out.print("文本向量"+doc+"向里权重：");
//            for(double d:ret){
//            	System.out.print(d+" ");
//            }
//            System.out.println();
            return ret;
        }//这就是得到文本权重向量的一个副本，并作了一个输出的处理。

		public double GetSimilarity(int doc_i, int doc_j)
		{
			double [] vector1=GetTermVector2 (doc_i);
			double [] vector2=GetTermVector2 (doc_j);

			return TermVector.ComputeCosineSimilarity(vector1, vector2) ;

		}//余弦夹角的一个运算。
		
		private Dictionary GetWordFrequency(String input)//获得词的频数的集合
		{
			String convertedInput=input.toLowerCase() ;//就这个地方了，作了一个转换处理
					
            List<String> temp = new ArrayList<String>(_tokenizer.partition(convertedInput));//分词（也去掉了信用的词）
            String[] words=new String[temp.size()];
            temp.toArray(words);	//转换成数组	
	        
			Arrays.sort(words);//作排序
			
			String[] distinctWords=GetDistinctWords(words);//去掉重复的词的集合
						
			Dictionary result=new Hashtable();
			for (int i=0; i < distinctWords.length; i++)
			{
				Object tmp;
				tmp=CountWords(distinctWords[i], words);//某个词的个数
				result.put(distinctWords[i], tmp);
				
			}
			//System.out.println(result);
			return result;
		}				
				
		private static String[] GetDistinctWords(String[] input)
		//去掉重复的词
		{				
			if (input == null)			
				return new String[0];			
			else
			{
                List<String> list = new ArrayList<String>();
				
				for (int i=0; i < input.length; i++)
					if (!list.contains(input[i])) // N-GRAM SIMILARITY?				
						list.add(input[i]);
				String[] v=new String[list.size()];
				return (String[]) list.toArray(v);
			}
		}
		

		
		private int CountWords(String word, String[] words)//二分查找，并最后计算去词在文章中出现的次数
		{
			int itemIdx=Arrays.binarySearch(words, word);//作二分查找
			
			if (itemIdx > 0)			
				while (itemIdx > 0 && words[itemIdx].equals(word))				
					itemIdx--;				
						
			int count=0;
			while (itemIdx < words.length && itemIdx >= 0)
			{
				if (words[itemIdx].equals(word)) count++;				
				
				itemIdx++;
				if (itemIdx < words.length)				
					if (!words[itemIdx].equals(word)) break;					
				
			}
			 
			return count;//数组中某个词的个数吧
		}

		public int get_numTerms() {
			return _numTerms;
		}

		public void set_numTerms(int terms) {
			_numTerms = terms;
		}				
}
