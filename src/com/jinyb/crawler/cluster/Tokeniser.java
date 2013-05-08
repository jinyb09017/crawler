package com.jinyb.crawler.cluster;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;

import ICTCLAS.I3S.AC.ICTCLAS50;


/*
 Tokenization
 Author: Thanh Ngoc Dao - Thanh.dao@gmx.net
 Copyright (c) 2005 by Thanh Ngoc Dao.
 */

/// <summary>
/// Summary description for Tokeniser.
/// Partition string into SUBwords
/// </summary>
public class Tokeniser implements ITokeniser {

	// / <summary>
	// / 以空白字符进行简单分词，并忽略大小写，
	// / 实际情况中可以用其它中文分词算法
	// / </summary>
	// / <param name="input"></param>
	// / <returns></returns>
	private ICTCLAS50 icta;
	private volatile boolean initialized = false;

	public Tokeniser() {
		icta = new ICTCLAS50();
	    String initPath=".";
	    // 初始化
	    try {
			if (icta.ICTCLAS_Init(initPath.getBytes("GB2312")) == false) {
			  System.out.println("Init Fail!");
			  return;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    // 设置词性标注集(0 计算所二级标注集，1 计算所一级标注集，2 北大二级标注集，3 北大一级标注集)
	    icta.ICTCLAS_SetPOSmap(2);

	    // 导入用户字典
	    int nCount = 0;
	    String usrdir = "userdict.txt"; // 用户字典路径
	    byte[] usrdirb = usrdir.getBytes();// 将string转化为byte类型
	    // 导入用户字典,返回导入用户词语个数第一个参数为用户字典路径，第二个参数为用户字典的编码类型
	    nCount = icta.ICTCLAS_ImportUserDictFile(usrdirb, 0);
	    System.out.println("导入用户词个数" + nCount);
	    initialized = true;
	  
	}

	public List<String> partition(String input) {

		input=input.toLowerCase();
		Reader reader=new StringReader(input);
		List<String> result = new ArrayList<String>();
	    try {
	      StringBuffer contentbuffer = new StringBuffer();
	      char[] temp = new char[1024];
	      int size = 0;
	      while ((size = reader.read(temp, 0, 1024)) != -1) {
	        String tempstr = new String(temp, 0, size);
	        contentbuffer.append(tempstr);//按字符的方式来读取
	      }
	      byte nativeBytes[] = icta.ICTCLAS_ParagraphProcess(contentbuffer.toString().getBytes("GB2312"), 2, 1);
	      String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
	     // System.out.println("分词结果： " + nativeStr);
	      	//进行词用词过滤
	     String[] terms=nativeStr.split("\\s+");
	   //  System.out.println(nativeStr);
	     int pos;
	     String term,type;
	     for (String string : terms) {
	    	 pos=string.lastIndexOf('/');
	    	 if(pos==-1)continue;
	    	 term=string.substring(0,pos);
	    	 type=string.substring(pos+1, string.length());
	    	 if(accept(term,type)){
	    		 result.add(term);
	    		// System.out.println(term);
	    	 }
	     }
	    } catch (Throwable e) {
	      e.printStackTrace();
	    }
	    return result;
		
	}
	
	 

	 
	  /*
	   * 对分词结果进行过滤
	   * */
	  private boolean accept(String term,String type){
		  boolean accept=false;
		//对词的要求
//		  if(term.length()>1){
//			  accept=true;
//		  }
//		  if(!accept)return accept;
		  //对词性的要求
		  if(type.startsWith("n")	//名词
				||type.startsWith("t")	//时间词
				||type.startsWith("s")	//处所词
	    		||type.startsWith("f")	//方位词
	    		||type.startsWith("a")	//形容词
	    		||type.startsWith("v")	//动词
	    		||type.startsWith("b")	//区别词
	    		||type.startsWith("z")	//状态词
//	    		||type.startsWith("r")	//代词
//	    		||type.startsWith("m")	//数词
	    		||type.startsWith("q")	//量词
//	    		||type.startsWith("d")	//副词
	    		||type.startsWith("p")	//介词
	    		||type.startsWith("c")	//连词
//	    		||type.startsWith("u")	//助词
//	    		||type.startsWith("e")	//叹词
//	    		||type.startsWith("y")	//语气词
	    		||type.startsWith("o")	//拟声词
	    		||type.startsWith("h")	//前缀
	    		||type.startsWith("k")	//后缀
	    		||type.startsWith("x")	//网址URL
//	    		||type.startsWith("w")	//标点符号
		    		){
			   accept=true;
		  }
		  if(term.length()<2)//排除只有一个字的词
		  {
			  accept=false;
		  }
		  
		  return accept;
	  }
	 
	  
	  public void close() {
	    icta.ICTCLAS_SaveTheUsrDic();
	    icta.ICTCLAS_Exit();
	    initialized = false;
	  }
	public static void main(String args[])
	{
		Tokeniser token=new Tokeniser();
		List<String> word=token.partition("以前觉得，能和外国人流利地聊天是件十分兴奋又难得的事情，甚至想主动索取联系方式成为朋友，后来，有机会聊天了，却没想过要刻意去保持联系，反而自然而然地成为了朋友。以前，好想争取机会成为某位喜欢的明星记忆里的粉丝，后来，只想要张签名照，端正地摆在视线内就好。以前，很想在路过的每个有趣的地方留下身影，后来，每个值得留影的地方轻轻卡擦一下就好");
		for(String i:word)
			System.out.print(i+" ");
				token.close();
	}
}
