package com.hundsun.jinyb.action;

import com.jinyb.crawler.entity.User;
import com.jinyb.crawler.service.UserService;

public class VerifyEmailAction extends BaseAction {
	private String email;
	private String code;
	private String codeInfo;
	private User user;
  private UserService  userService;
	public String execute(){
		//将接收到的邮箱验证码解析
		//code=de1ee801-9e64-4095-844a-d4ae38c93359|12
		String[] dataCode=code.trim().split("\\|");
		String uuId=dataCode[0];
		int id=Integer.parseInt(dataCode[1]);
		System.out.println(uuId+"  "+id);
		//获取用户ID和UUID
		//利用ID和UUID去数据库检索
		User u=(User) session.get("user");
		try {
			System.out.println("----------------------");
			user=userService.findUserByUUidId(id,uuId);
				if(u!=null&&u.getUuid().equals(uuId)&&u.getEmail().equals(email)){
					String emailVerify="1";
					userService.updateUser(emailVerify, id);
					codeInfo="";
					return "success";
				}else{
					user=new User();//3e49bd12-de89-42fa-9958-baeafb888576|9
					user.setEmail(email);
					codeInfo="验证码输入错误123！";
					return "verifyCodeEmail";//返回原页面
				}
		} catch (Exception e) {
			e.printStackTrace();
			user=new User();
			user.setEmail(email);
			codeInfo="验证码输入错误！";
			return "verifyCodeEmail";
		}
		//判断是否正确
		//如果正确,更新d_user的is_email_verify
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCodeInfo() {
		return codeInfo;
	}

	public void setCodeInfo(String codeInfo) {
		this.codeInfo = codeInfo;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
