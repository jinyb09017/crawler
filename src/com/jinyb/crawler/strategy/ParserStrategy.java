/**
 * 
 */
package com.jinyb.crawler.strategy;

import java.util.List;

import com.jinyb.crawler.entity.News;


/**
 * @author ���Ų� @copyright
 * use the strategy design 
 * this is the abstract strategy
 */
public interface ParserStrategy {
	public abstract List<String> getCrawlUrl(String web);//��������web���url�б�
	
	public abstract News parseWeb(String web);//������ҳ�����ؽ����ɹ�����


}
