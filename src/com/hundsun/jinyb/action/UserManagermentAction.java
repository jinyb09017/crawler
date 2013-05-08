package com.hundsun.jinyb.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.jinyb.crawler.entity.Admin;
import com.jinyb.crawler.entity.User;
import com.jinyb.crawler.service.UserMsgService;

public class UserManagermentAction extends BaseAction {
	private UserMsgService userMsgService;
	private List<Admin> listAdmin;
	private List<User> listUser;
	private int page = 0;
	private int pageSize = 100;
	private User user;
	private int id;
	private Admin admin;
	private int total;

	public String findAllAdmin() {
		total = userMsgService.findTotalAdmin(pageSize);
		listAdmin = userMsgService.findAdmin(page, pageSize);
		if (listAdmin == null) {
			return "error";
		}
		return "SUCCESSADMIN";
	}

	public String findAllUser() {
		listUser = userMsgService.findUser(page, pageSize);
		if (listUser == null) {
			return "error";
		}
		return "SUCCESSUSER";
	}

	public String delUser() {
		boolean isok = userMsgService.updateUserById(id);
		if (isok) {
			return "SUCCESSDELUSER";
		} else {
			return "error";
		}

	}

	public String updateUser() {
		boolean isok = userMsgService.updateUserByUser(user);
		if (isok) {
			return "SUCCESSUPDATEUSER";
		} else {
			return "error";
		}

	}

	public String updateJSPUser() {
		try {
			user.setName(new String(user.getName().getBytes("iso-8859-1"),
					"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "updateUser";
	}

	public String delAdmin() {
		boolean isok = userMsgService.delAdminById(id);
		if (isok) {
			return "SUCCESSDELADMIN";
		} else {
			return "error";
		}
	}
	public String adminLogin()
	{
		String msg="";
		List<Admin> adList=userMsgService.adminLogin(admin);
		if(adList.size()==0)
		{
			msg="用户名或密码不正确";
			httpRequest.setAttribute("msg", msg);
			return "loginError";
		}
		//session.put("adminname", admin.getName());
		session.put("admin", admin);
		return "loginSuccess";
		
	}
	public String adminExit()
	{
		
		session.remove("admin");
		return "exitSuccess";
		
	}

	public String updateJSPAdmin() {
		try {
			admin.setName(new String(admin.getName().getBytes("iso-8859-1"),
					"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "updateAdmin";
	}

	public String addJSPAdmin() {
		return "addAdmin";
	}

	public String addAdmin() {
		boolean isok = userMsgService.addAdmin(admin);
		if (isok) {
			return "SUCCESSADDADMIN";
		} else {
			return "error";
		}
	}

	public String updateAdmin() {
		boolean isok = userMsgService.updateAdminByAdmin(admin);
		if (isok) {
			return "SUCCESSUPDATEADMIN";
		} else {
			return "error";
		}
	}

	public void setUserMsgService(UserMsgService userMsgService) {
		this.userMsgService = userMsgService;
	}

	public List<Admin> getListAdmin() {
		return listAdmin;
	}

	public void setListAdmin(List<Admin> listAdmin) {
		this.listAdmin = listAdmin;
	}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
