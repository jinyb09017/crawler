package com.jinyb.crawler.strategy.impl;

import java.util.List;

import com.jinyb.crawler.entity.News;
import com.jinyb.crawler.strategy.ParserStrategy;
public class ParserContext {
	private ParserStrategy parser;

	/**
	 * @return the parser
	 */
	public ParserStrategy getParser() {
		return parser;
	}

	/**
	 * @param parser the parser to set
	 */
	public void setParser(ParserStrategy parser) {
		this.parser = parser;
	}
	public News dealWeb(String web)
	{
		News news=parser.parseWeb(web);
		return news;
	}
	public List<String> getCrawlUrl(String web)
	{
		List<String> list=parser.getCrawlUrl(web);
		return list;
	}
	 public static StringBuffer delTag(String tag,StringBuffer content)//去除特定标签内容  
	 {  
	     String beginTag="<"+tag;  
	     String endTag="</"+tag+">";  
	     int pos1=0;  
	     int pos2=0;  
	     
	     while((pos2=content.indexOf(beginTag,0))!=-1)  
	     {  
	             pos1=content.indexOf(endTag,pos2)+endTag.length()-1;  
	             if(pos1>pos2)  
	             {  
	                 content=content.delete(pos2, pos1);  
	             }  
	             else  
	             {  
	                 pos1=content.lastIndexOf("</");  
	                 if(pos1>pos2)  
	                 {  
	                     content=content.delete(pos2, pos1);  
	                     content=content.append(tag+"></body></html");  
	                 }  
	                 else  
	                 {  
	                     content=content.delete(pos2, content.length());  
	                     content=content.append("</body></html");  
	                 }  
	             }  
	     }  
	     return content;  
	  }  


}
