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
		private String[][] _ngramDoc;//��ά���ĵ�
		private int _numDocs=0;//the number of the docs
		private int _numTerms=0;//the nummber of terms
		private ArrayList _terms;//list to store the term
		private int[][] _termFreq;//the frequent of the term
		private float[][] _termWeight;//the weight of the term
		private int[] _maxTermFreq;//���Ĵ�Ƶ����
		private int[] _docFreq;//����Ƶ�ʵ�����
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

		private ArrayList GenerateTerms(String[] docs)//���ɴ���
		{//ͨ��ѭ�����㷨��ȡ���������ĵ��еĲ��ظ��Ĳ�����ͣ�ôʵ��ĵ�������uniques.
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
//		{//����һ���ʵ������ɡ�
//			Object element=collection.get(key);
//			collection.put(key, newValue);
//			return element;
//		}

		private int GetTermIndex(String term)//�����ʵ�����
		{
			Object index=_wordsIndex.get(term);
			if (index == null) return -1;
			return (Integer)index;
		}

		private void myInit()
		{
			System.out.println("generate terms����");
			_terms=GenerateTerms (_docs );//_termsΪ�ĵ����Ĵʻ�����
			System.out.println("after generate ,terms.size()"+_terms.size() );
			_numTerms=_terms.size() ;//_numTermsΪ�ĵ��������ʵĸ�����

			_maxTermFreq=new int[_numDocs] ;//�����Ƶ����
			_docFreq=new int[_numTerms] ;//����Ƶ��
			_termFreq =new int[_numTerms][] ;//��Ƶ
			_termWeight=new float[_numTerms][] ;//�ʵ�Ȩ��

			for(int i=0; i < _terms.size() ; i++)			
			{
				_termWeight[i]=new float[_numDocs] ;
				_termFreq[i]=new int[_numDocs] ;
				_wordsIndex.put(_terms.get(i),i);//�������ɵľ���ʿ�
				System.out.print(_terms.get(i)+" ");

			//	AddElement(_wordsIndex, _terms.get(i), i);//����������һ�������ʵ�����			
			}
			
			GenerateTermFrequency();
//			for(int i=0;i<_numDocs;i++)
//			{
//				//System.out.print("�ı�"+i+"�Ĵ�Ƶ");
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

		private void GenerateTermFrequency()//���ɴ�Ƶ
		{
			for(int i=0; i < _numDocs  ; i++)
			{								
				String curDoc=_docs[i];
				Dictionary freq=GetWordFrequency(curDoc);//������ĵ��Ĵʵĸ�����Ӧ�ļ���
				Enumeration enums=freq.keys();
				
				while(enums.hasMoreElements()){
					String word=(String) enums.nextElement();
					int wordFreq=(Integer)freq.get(word);//ĳ�����ʵ�Ƶ��
					int termIndex=GetTermIndex(word);//�ж�ÿ�����Ƿ��������������
                    if(termIndex == -1)
                        continue;//���¿�ʼѭ��
					_termFreq [termIndex][i]=wordFreq;//�ĵ�i�Ĵʵ�Ƶ��,�������������ж�Ӧ
					_docFreq[termIndex] ++;//��ʾ�����ôʵ��ĵ�����һ��

					if (wordFreq > _maxTermFreq[i]) _maxTermFreq[i]=wordFreq;	//ͳ��ĳ���ĵ������Ĵ�Ƶ��������Ϊ��ĸ������
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
			return tf * idf;//���termweight��û�������·�ĸ�Ĵ���
		}
		
		private  float[] GetTermVector(int doc)
		{
			float[] w=new float[_numTerms] ; 
			for (int i=0; i < _numTerms; i++)											
				w[i]=_termWeight[i][doc];
			
				
			return w;//����ı���Ȩ��������
		}
        public double [] GetTermVector2(int doc)//ת����double
        {
            double [] ret = new double[_numTerms];
            float[] w = GetTermVector(doc);
            for (int i = 0; i < ret.length; i++ )
            {
                ret[i] = w[i];
            }
//            System.out.print("�ı�����"+doc+"����Ȩ�أ�");
//            for(double d:ret){
//            	System.out.print(d+" ");
//            }
//            System.out.println();
            return ret;
        }//����ǵõ��ı�Ȩ��������һ��������������һ������Ĵ���

		public double GetSimilarity(int doc_i, int doc_j)
		{
			double [] vector1=GetTermVector2 (doc_i);
			double [] vector2=GetTermVector2 (doc_j);

			return TermVector.ComputeCosineSimilarity(vector1, vector2) ;

		}//���Ҽнǵ�һ�����㡣
		
		private Dictionary GetWordFrequency(String input)//��ôʵ�Ƶ���ļ���
		{
			String convertedInput=input.toLowerCase() ;//������ط��ˣ�����һ��ת������
					
            List<String> temp = new ArrayList<String>(_tokenizer.partition(convertedInput));//�ִʣ�Ҳȥ�������õĴʣ�
            String[] words=new String[temp.size()];
            temp.toArray(words);	//ת��������	
	        
			Arrays.sort(words);//������
			
			String[] distinctWords=GetDistinctWords(words);//ȥ���ظ��Ĵʵļ���
						
			Dictionary result=new Hashtable();
			for (int i=0; i < distinctWords.length; i++)
			{
				Object tmp;
				tmp=CountWords(distinctWords[i], words);//ĳ���ʵĸ���
				result.put(distinctWords[i], tmp);
				
			}
			//System.out.println(result);
			return result;
		}				
				
		private static String[] GetDistinctWords(String[] input)
		//ȥ���ظ��Ĵ�
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
		

		
		private int CountWords(String word, String[] words)//���ֲ��ң���������ȥ���������г��ֵĴ���
		{
			int itemIdx=Arrays.binarySearch(words, word);//�����ֲ���
			
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
			 
			return count;//������ĳ���ʵĸ�����
		}

		public int get_numTerms() {
			return _numTerms;
		}

		public void set_numTerms(int terms) {
			_numTerms = terms;
		}				
}
