/**
 * 
 */
package com.jinyb.crawler.strategy;

import java.util.List;

import com.jinyb.crawler.entity.News;


/**
 * @author 金雅博 @copyright
 * use the strategy design 
 * this is the abstract strategy
 */
public interface ParserStrategy {
	public abstract List<String> getCrawlUrl(String web);//根据种子web获得url列表
	
	public abstract News parseWeb(String web);//解析网页，返回解析成功对象


}
