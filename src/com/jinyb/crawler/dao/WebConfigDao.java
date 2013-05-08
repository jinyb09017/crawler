/**
 * 
 */
package com.jinyb.crawler.dao;

import java.util.List;

import com.jinyb.crawler.entity.WebConfig;

/**
 * @author jinyb09017
 *
 */
public interface  WebConfigDao {
	 public abstract void save(WebConfig webConfig);
		
		public abstract void delete(Integer id);
		
		public abstract WebConfig queryById(Integer id);
		
		public abstract void update(WebConfig webConfig);
		
		public abstract List<WebConfig> queryList();

}
