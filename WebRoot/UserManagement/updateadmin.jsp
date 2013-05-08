<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<title>管理员用户修改</title>
  </head>

  <body>
    <form action="usermsg_updateAdmin.action" method="post">
    <input type="text" name="admin.id" value="${admin.id }" style="display: none;"/>
     管理员名称：<input type="text" name="admin.name" value="${admin.name }"/><br/>
     管理员级别:<input type="text" name="admin.level" value="${admin.level }"/><br/>
     管理员状态:<input type="text" name="admin.state" value="${admin.state }"/><br/>
     <input type="submit" value="修改">
  </form>
  </body>
</html>
