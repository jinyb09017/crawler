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
@Scope("prototype")//���붨���prototype����struts2������������ϣ�������ɵ������Ӷ�����̲߳���ȫ�ġ�
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
		String message="���óɹ�";
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
		System.out.println("·���ǣ�"+dir);
		conParser.setDir(dir);
	}

}
