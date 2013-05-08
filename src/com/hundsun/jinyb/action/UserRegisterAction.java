package com.hundsun.jinyb.action;

import org.apache.struts2.ServletActionContext;

import com.jinyb.crawler.entity.User;
import com.jinyb.crawler.service.UserService;
import com.jinyb.crawler.util.EmailUtil;
import com.opensymphony.xwork2.ActionContext;

public class UserRegisterAction extends BaseAction{
	private UserService userService;
	private User user;
	public String execute() {
		System.out.println("注册：——User:"+user);
		session.put("user", user);
		String validataCode=userService.register(user);
		String content="http://localhost:8080/crawler/user/checkEmail.action?code="+validataCode;
		EmailUtil.sendEmail(user.getEmail(), "web信息管理系统欢迎您的注册", content);
		System.out.println(validataCode);
	  	if(!validataCode.equals("")){
	  		return "success";
	  	}else{
	  		return "error";
	  	}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
