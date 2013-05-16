package com.jinyb.crawler.analyzer;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;

import ICTCLAS.I3S.AC.ICTCLAS50;

public class ICTCLASAnalyzer extends Analyzer {
  private ICTCLAS50 icta;
  private volatile boolean initialized = false;
  public ICTCLASAnalyzer() throws UnsupportedEncodingException {
    icta = new ICTCLAS50();
    String initPath=".";
    // 初始�?
    if (icta.ICTCLAS_Init(initPath.getBytes("GB2312")) == false) {
      System.out.println("Init Fail!");
      return;
    }
    else
    {
    	System.out.println("init success");
    }

    // 设置词�?标注�?0 计算�?��级标注集�? 计算�?��级标注集�? 北大二级标注集，3 北大�?��标注�?
    icta.ICTCLAS_SetPOSmap(2);

    // 导入用户字典
    int nCount = 0;
    String usrdir = "userdict.txt"; // 用户字典路径
    byte[] usrdirb = usrdir.getBytes();// 将string转化为byte类型
    // 导入用户字典,返回导入用户词语个数第一个参数为用户字典路径，第二个参数为用户字典的编码类型
    nCount = icta.ICTCLAS_ImportUserDictFile(usrdirb, 0);
    //System.out.println("导入用户词个�? + nCount);
    initialized = true;
  }

  public List<String> tokenizeReader(Reader reader) {
    List<String> result = new ArrayList<String>(1000);
    try {
      StringBuffer contentbuffer = new StringBuffer();
      char[] temp = new char[1024];
      int size = 0;
      while ((size = reader.read(temp, 0, 1024)) != -1) {
        String tempstr = new String(temp, 0, size);
        contentbuffer.append(tempstr);//按字符的方式来读�?
      }
      byte nativeBytes[] = icta.ICTCLAS_ParagraphProcess(contentbuffer.toString().getBytes("GB2312"), 2, 1);
      String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "GB2312");
      //System.out.println("分词结果�?" + nativeStr);
      	//进行词用词过�?
     String[] terms=nativeStr.split("\\s+");
     System.out.println(nativeStr);
     int pos;
     String term,type;
     for (String string : terms) {
    	 pos=string.lastIndexOf('/');
    	 if(pos==-1)continue;
    	 term=string.substring(0,pos);
    	 type=string.substring(pos+1, string.length());
    	 if(accept(term,type)){
    		 result.add(string);
    	 }
     }
    } catch (Throwable e) {
      e.printStackTrace();
    }
    return result;
  }
  /*
   * 对分词结果进行过�?
   * */
  private boolean accept(String term,String type){
	  boolean accept=false;
	//对词的要�?
//	  if(term.length()>1){
//		  accept=true;
//	  }
//	  if(!accept)return accept;
	  //对词性的要求
	  if(type.startsWith("n")	//名词
			||type.startsWith("t")	//时间�?
			||type.startsWith("s")	//处所�?
    		||type.startsWith("f")	//方位�?
    		||type.startsWith("a")	//形容�?
    		||type.startsWith("v")	//动词
    		||type.startsWith("b")	//区别�?
    		||type.startsWith("z")	//状�?�?
//    		||type.startsWith("r")	//代词
//    		||type.startsWith("m")	//数词
    		||type.startsWith("q")	//量词
//    		||type.startsWith("d")	//副词
    		||type.startsWith("p")	//介词
    		||type.startsWith("c")	//连词
//    		||type.startsWith("u")	//助词
//    		||type.startsWith("e")	//叹词
//    		||type.startsWith("y")	//语气�?
    		||type.startsWith("o")	//拟声�?
    		||type.startsWith("h")	//前缀
    		||type.startsWith("k")	//后缀
    		||type.startsWith("x")	//网址URL
//    		||type.startsWith("w")	//标点符号
	    		){
		  return true;
	  }
	  
	  return accept;
  }
  @Override
  public TokenStream tokenStream(String fieldName, Reader reader) {
    if(!initialized)
      return null;
    List<String> tokens = tokenizeReader(reader);
    return new ICTCLASTokenizer(tokens);
  }

  @Override
  public TokenStream reusableTokenStream(String fieldName, Reader reader) throws IOException {
    Tokenizer tokenizer = (Tokenizer) getPreviousTokenStream();
    if (tokenizer == null) {
      List<String> tokens = tokenizeReader(reader);
      tokenizer = new ICTCLASTokenizer(tokens);
      setPreviousTokenStream(tokenizer);
    } else{
      tokenizer.reset(reader);
      ICTCLASTokenizer t = (ICTCLASTokenizer)tokenizer;
      List<String> tokens = tokenizeReader(reader);
      t.reset(tokens);
    }
    return tokenizer;
  }
  
  @Override
  public void close() {
    icta.ICTCLAS_SaveTheUsrDic();
    icta.ICTCLAS_Exit();
    initialized = false;
  }
}
