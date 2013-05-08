package com.jinyb.crawler.dao;

import java.util.List;

import com.jinyb.crawler.entity.Admin;
import com.jinyb.crawler.entity.User;

public interface UserMsgDao {
	public List<User> findAllUser(int page, int pageSize);

	public List<Admin> findAllAdmin(int page, int pageSize);

	public boolean updateUserById(int id);

	public boolean updateUserByUser(User user);

	public boolean addAdmin(Admin admin);

	public boolean delAdminById(int id);

	public boolean updateAdminByAdmin(Admin admin);

	public int findTotalAdmin(int pageSize);
	
	public List<Admin> queryByCondition(String con,Object[] o);
}
