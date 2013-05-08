<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<title>管理员用户添加</title>
  </head>

  <body>
    <form action="usermsg_addAdmin.action" method="post">
     管理员名称：<input type="text" name="admin.name" /><br/>
  管理员密码：<input type="password" name="admin.pwd"/><br/>   
     管理员级别:<input type="text" name="admin.level" /><br/>
     管理员状态:<input type="text" name="admin.state" /><br/>
     <input type="submit" value="增加">
  </form>
  </body>
</html>
