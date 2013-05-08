package com.hundsun.jinyb.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jinyb.crawler.config.Config;
import com.jinyb.crawler.config.ConfigParser;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")//必须定义成prototype才与struts2的设计理念相结合，否则会变成单例，从而变成线程不安全的。
public class ConfigAction extends BaseAction{
	private ConfigParser conParser;
	private Config config;
	
	/**
	 * @return the conParser
	 */
	public ConfigParser getConParser() {
		return conParser;
	}
	/**
	 * @param conParser the conParser to set
	 */
	public void setConParser(ConfigParser conParser) {
		this.conParser = conParser;
	}
	/**
	 * @return the config
	 */
	public Config getConfig() {
		return config;
	}
	/**
	 * @param config the config to set
	 */
	public void setConfig(Config config) {
		this.config = config;
	}
	public String saveConfig()
	{
		init();
		System.out.println(this.config);

		
		conParser.writeConfig(config);
		String message="配置成功";
		httpRequest.setAttribute("message", message);
		return "config";
		
	}
	public String showConfig()
	{
		init();
		this.config=conParser.getConfig();
		httpRequest.setAttribute("config", config);
		return "config";
	}
	public void init()
	{

		conParser=new ConfigParser();
		String dir=ServletActionContext.getRequest().getRealPath("/")+"/config/config.xml";
		System.out.println("路径是："+dir);
		conParser.setDir(dir);
	}

}
