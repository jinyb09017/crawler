package com.jinyb.crawler.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
public class EmailUtil {
  public static void sendEmail(String email,String title,String content){
  	SimpleEmail sendemail=new SimpleEmail();
  	sendemail.setHostName("smtp.163.com");
  	sendemail.setAuthentication("hundsun_future@163.com", "hundsun123");
  	sendemail.setCharset("utf-8");
  	try {
			sendemail.addTo(email);
			sendemail.setFrom("hundsun_future@163.com");
			sendemail.setSubject(title);
			sendemail.setMsg("��ӭ��ע�᱾���������μǱ���֤��:"+content+"������֤����������������֤��");
			sendemail.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
  }
  public static void main(String[] args) {//Ӧ��Ҫ������������
	  String email="407007276@qq.com";
	  String title="this is from jinyb test";
	  String content="http://localhost:8080/futures/console/";
	  sendEmail(email, title, content);
}
}
