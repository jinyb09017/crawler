package com.hundsun.jinyb.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

public class BaseAction implements SessionAware,ServletRequestAware,ServletResponseAware,ServletContextAware{
  protected Map<String,Object> session;
  //protected Map<String,Object> request;,RequestAware
  protected static HttpServletRequest httpRequest;
  protected HttpServletResponse httpResponse;
  protected ServletContext application;
	public void setSession(Map<String, Object> session) {
	  this.session=session;
	}
//	public void setRequest(Map<String, Object> request) {
//		this.request=request;
//	}

	public void setServletRequest(HttpServletRequest httpRequest) {
		this.httpRequest=httpRequest;
	}

	public void setServletContext(ServletContext application) {
		this.application=application;
		
	}

	public void setServletResponse(HttpServletResponse httpResponse) {
		this.httpResponse=httpResponse;
	}

}
