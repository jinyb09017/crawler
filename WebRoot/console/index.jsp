<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<title>web信息工程研究中心 后台管理工作平台 by jinyb</title>
<link rel="stylesheet" type="text/css" href="console/css/style.css"/>
<script type="text/javascript" src="console/js/js.js" charset="gbk"></script>
<script type="text/javascript" src="js/jquery-1.4.3.js"></script>
<script type="text/javascript" src="js/checklogin.js" charset="gbk"></script>
<style type="text/css">
<!--
   #imgVcode {width:260px;height:80px;}

-->
</style>
</head>
<body>
<div id="top"> </div>
<form id="ctl00" name="login" action="users/usermsg_adminLogin" method="post">
  <div id="center">
    <div id="center_left"> <img id='imgVcode' src="user/imageCode.action"   /><br/>
    <a href="javascript:;" onclick="document.getElementById('imgVcode').src = 'user/imageCode.action?'+(new Date()).getTime()">看不清楚？换个图片</a>
    </div>
    <div id="center_middle">
      <div class="user">
        <label>用户名：
        <input type="text" name="admin.name" id="txtUsername" />
        </label>
      </div>
      <div class="user">
        <label>密　码：
        <input type="password" name="admin.pwd" id="txtPassword" />
        </label>
      </div>
      <div class="chknumber">
        <label>验证码：
        <input name="chknumber" type="text" id="txtVerifyCode" maxlength="4" class="chknumber_input" />
        </label><span id="number.info" style="color:red"></span>
       
        
        
      </div>
    </div>
    <div id="center_middle_right"></div>
    <div id="center_submit">
      <div class="button"> <img src="console/images/dl.gif" width="57" height="20" onclick="form_submit()" > </div>
      <div class="button"> <img src="console/images/cz.gif" width="57" height="20" onclick="form_reset()"> </div>
    </div>
    <div id="center_right"></div>
  </div>
</form>
<div id="footer"><span  style="color:red;margin-top: 20px;">${msg }</span></div>
</body>
</html>
