package com.hundsun.jinyb.action;

import com.jinyb.crawler.entity.User;
import com.jinyb.crawler.service.UserService;

public class ValidEmailAction {
	  private String email;
	  private boolean ok;
	  private UserService  userService;
	  public String execute(){
	  	User user=null;
	  	try {
				user=userService.findUserByEmail(email);
				System.out.println("邮箱验证调用？");
				if(user==null){
					ok=true;
				}else{
					ok=false;
				}
				return "success";
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
	  }
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public boolean isOk() {
			return ok;
		}
		public void setOk(boolean ok) {
			this.ok = ok;
		}
		public void setUserService(UserService userService) {
			this.userService = userService;
		}
	  
	}
