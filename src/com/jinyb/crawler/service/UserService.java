package com.jinyb.crawler.service;

import com.jinyb.crawler.entity.User;

public interface UserService {
  public String login(User user,String password);
  public User findUserByEmail(String email);
public String register(User user);
public User findUserByUUidId(int id, String uuId);
public void updateUser(String emailVerify, int id);
  
  
}
