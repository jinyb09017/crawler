/**
 * 
 */
package com.hundsun.jinyb.comparator;

import java.util.Comparator;

import com.jinyb.crawler.entity.News;

/**
 * @author jinyb09017
 *
 */
public class NewsSortComparator implements Comparator<News> {

	@Override
	public int compare( News news1, News news2) {
		// TODO Auto-generated method stub
		long time=news1.getNewsPublishtime().getTime()-news2.getNewsPublishtime().getTime();
		int result=0;
		if(time>0)
		    result= 1;
		if(time==0)
			result= 0;
		if(time<0)
			result= -1;
		return result;
	}

}
