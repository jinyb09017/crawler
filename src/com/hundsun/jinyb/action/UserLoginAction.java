package com.hundsun.jinyb.action;

import com.jinyb.crawler.entity.User;
import com.jinyb.crawler.service.UserService;

public class UserLoginAction extends BaseAction {
	private User user;
	private UserService userService;
    private String failCode;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String login() {
		System.out.println(user.getName());
		System.out.println("界面接受：" + user.getEmail() + "  " + user.getPwd());
		String password=user.getPwd();
		User u=userService.findUserByEmail(this.user.getEmail());
		String checkCode = userService.login(u,password);
		if(checkCode.equals("verifyEmailError")){
			return "verifyEmailError";
		}else if(checkCode.equals("cancel")){
			failCode="本账户已经注销!";
			return "pwderror";
		}else if(checkCode.equals("pwderror")){
			failCode="用户名或密码错误!";
			return "pwderror";
		}else if(checkCode.equals("success")){
		//4.将user写入session
		   session.put("username", u.getName());
			//5.跳转到/main/main.jsp
			return "success";
		}else{
			return "error";
		}
	}

	public String getFailCode() {
		return failCode;
	}

	public void setFailCode(String failCode) {
		this.failCode = failCode;
	}


}
