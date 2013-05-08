package com.jinyb.crawler.service.impl;

import com.jinyb.crawler.dao.UserDao;
import com.jinyb.crawler.entity.User;
import com.jinyb.crawler.service.UserService;
import com.jinyb.crawler.util.EmailUtil;
import com.jinyb.crawler.util.MD5Util;
import com.jinyb.crawler.util.VerifyUtil;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String login(User user, String password) {
		if (user == null) {
			return "pwderror";
		}
		try {
			password = MD5Util.encode(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "pwderror";
		}

		if (user.getState() == 0) {
			return "cancel";
		} else {
			if (user.getActive().equals("0")) {
				return "verifyEmailError";
			} else {
				if (user.getPwd().equals(password)) {
					return "success";
				} else {
					return "pwderror";
				}
			}
		}

	}

	public User findUserByEmail(String email) {
		User dbuser = userDao.findUserByEmail(email);
		return dbuser;
	}

	public String register(User user) {//返回一个注册码
		User curuser = null;
		String emailCode="";
		String password = user.getPwd().trim();
		try {
			System.out.println("加密后的密码：" + MD5Util.encode(password));
			user.setPwd(MD5Util.encode(password));
			user.setType(1);// 表示网站注册的用户
			user.setLevel(1);// 表示网站注册
			user.setState(1);// 表示正常状态
			String verifyCode = VerifyUtil.randomUUID();
			user.setUuid(verifyCode);
			user.setActive("0");
			curuser = userDao.addUser(user);
			emailCode = verifyCode + "|" + curuser.getId();
			}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return emailCode;
	}

	public User findUserByUUidId(int id, String uuId) {
		return userDao.findUserByUUidId(id, uuId);
	}

	public void updateUser(String emailVerify, int id) {
		userDao.updateUser(emailVerify, id);
	}

}
