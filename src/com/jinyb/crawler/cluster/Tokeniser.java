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
	// / �Կհ��ַ����м򵥷ִʣ������Դ�Сд��
	// / ʵ������п������������ķִ��㷨
	// / </summary>
	// / <param name="input"></param>
	// / <returns></returns>
	private ICTCLAS50 icta;
	private volatile boolean initialized = false;

	public Tokeniser() {
		icta = new ICTCLAS50();
	    String initPath=".";
	    // ��ʼ��
	    try {
			if (icta.ICTCLAS_Init(initPath.getBytes("GB2312")) == false) {
			  System.out.println("Init Fail!");
			  return;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    // ���ô��Ա�ע��(0 ������������ע����1 ������һ����ע����2 ���������ע����3 ����һ����ע��)
	    icta.ICTCLAS_SetPOSmap(2);

	    // �����û��ֵ�
	    int nCount = 0;
	    String usrdir = "userdict.txt"; // �û��ֵ�·��
	    byte[] usrdirb = usrdir.getBytes();// ��stringת��Ϊbyte����
	    // �����û��ֵ�,���ص����û����������һ������Ϊ�û��ֵ�·�����ڶ�������Ϊ�û��ֵ�ı�������
	    nCount = icta.ICTCLAS_ImportUserDictFile(usrdirb, 0);
	    System.out.println("�����û��ʸ���" + nCount);
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
	        contentbuffer.append(tempstr);//���ַ��ķ�ʽ����ȡ
	      }
	      byte nativeBytes[] = icta.ICTCLAS_ParagraphProcess(contentbuffer.toString().getBytes("GB2312"), 2, 1);
	      String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
	     // System.out.println("�ִʽ���� " + nativeStr);
	      	//���д��ôʹ���
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
	   * �Էִʽ�����й���
	   * */
	  private boolean accept(String term,String type){
		  boolean accept=false;
		//�Դʵ�Ҫ��
//		  if(term.length()>1){
//			  accept=true;
//		  }
//		  if(!accept)return accept;
		  //�Դ��Ե�Ҫ��
		  if(type.startsWith("n")	//����
				||type.startsWith("t")	//ʱ���
				||type.startsWith("s")	//������
	    		||type.startsWith("f")	//��λ��
	    		||type.startsWith("a")	//���ݴ�
	    		||type.startsWith("v")	//����
	    		||type.startsWith("b")	//�����
	    		||type.startsWith("z")	//״̬��
//	    		||type.startsWith("r")	//����
//	    		||type.startsWith("m")	//����
	    		||type.startsWith("q")	//����
//	    		||type.startsWith("d")	//����
	    		||type.startsWith("p")	//���
	    		||type.startsWith("c")	//����
//	    		||type.startsWith("u")	//����
//	    		||type.startsWith("e")	//̾��
//	    		||type.startsWith("y")	//������
	    		||type.startsWith("o")	//������
	    		||type.startsWith("h")	//ǰ׺
	    		||type.startsWith("k")	//��׺
	    		||type.startsWith("x")	//��ַURL
//	    		||type.startsWith("w")	//������
		    		){
			   accept=true;
		  }
		  if(term.length()<2)//�ų�ֻ��һ���ֵĴ�
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
		List<String> word=token.partition("��ǰ���ã��ܺ�����������������Ǽ�ʮ���˷����ѵõ����飬������������ȡ��ϵ��ʽ��Ϊ���ѣ��������л��������ˣ�ȴû���Ҫ����ȥ������ϵ��������Ȼ��Ȼ�س�Ϊ�����ѡ���ǰ��������ȡ�����Ϊĳλϲ�������Ǽ�����ķ�˿��������ֻ��Ҫ��ǩ���գ������ذ��������ھͺá���ǰ��������·����ÿ����Ȥ�ĵط�������Ӱ��������ÿ��ֵ����Ӱ�ĵط����Ῠ��һ�¾ͺ�");
		for(String i:word)
			System.out.print(i+" ");
				token.close();
	}
}
