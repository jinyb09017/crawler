/**
 * 
 */
package com.hundsun.jinyb.action;

import java.util.List;

import com.jinyb.crawler.dao.WebConfigDao;
import com.jinyb.crawler.entity.WebConfig;

/**
 * @author jinyb09017
 *
 */
public class WebConfigAction extends BaseAction {
	private WebConfigDao webConfigDao;
	
	public String detail()
	{
		List<WebConfig> webConfigList=webConfigDao.queryList();
		WebConfig webConfig=webConfigList.get(0);
		httpRequest.setAttribute("webConfig", webConfig);
		return "detail";
	}

	public WebConfigDao getWebConfigDao() {
		return webConfigDao;
	}

	public void setWebConfigDao(WebConfigDao webConfigDao) {
		this.webConfigDao = webConfigDao;
	}

	

}
