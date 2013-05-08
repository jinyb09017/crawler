package com.jinyb.crawler.service.impl;

import java.util.List;

import com.jinyb.crawler.dao.UserMsgDao;
import com.jinyb.crawler.entity.Admin;
import com.jinyb.crawler.entity.User;
import com.jinyb.crawler.service.UserMsgService;
import com.jinyb.crawler.util.MD5Util;

public class UserMsgServiceImpl implements UserMsgService {
    private UserMsgDao userMsgDao;
	public List<Admin> findAdmin(int page, int pageSize) {
		
		return userMsgDao.findAllAdmin(page, pageSize);
	}

	public List<User> findUser(int page, int pageSize) {
		return userMsgDao.findAllUser(page, pageSize);
	}

	public void setUserMsgDao(UserMsgDao userMsgDao) {
		this.userMsgDao = userMsgDao;
	}

	public boolean updateUserById(int id) {
		
		return userMsgDao.updateUserById(id);
	}

	public boolean updateUserByUser(User user) {
		// TODO Auto-generated method stub
		return userMsgDao.updateUserByUser(user);
	}

	public boolean addAdmin(Admin admin) {
	   try {
		admin.setPwd(MD5Util.encode(admin.getPwd()));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
		return userMsgDao.addAdmin(admin);
	}

	public boolean delAdminById(int id) {
		
		return userMsgDao.delAdminById(id);
	}

	public boolean updateAdminByAdmin(Admin admin) {
		return userMsgDao.updateAdminByAdmin(admin);
	}

	public int findTotalAdmin(int pageSize) {
		return userMsgDao.findTotalAdmin(pageSize);
	}

	@Override
	public List<Admin>  adminLogin(Admin admin) {
		// TODO Auto-generated method stub
		String pwd = null;
		try {
			pwd=MD5Util.encode(admin.getPwd());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Admin> adminList=userMsgDao.queryByCondition("from Admin a where a.name=? and a.pwd=?", new Object[]{admin.getName(),pwd});
		
		return adminList;
	}

}
