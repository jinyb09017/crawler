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
<style type="text/css">
<!--
body {background: white;}

-->

</style>
</head>
<body>
  <div class="list">
     <div class="head">网站配置信息</div>
     <hr id="line" />
     <ul>
        <li><span id="lable">网站作者：</span><span id="content">${webConfig.author }</span></li>
        <li><span id="lable">联系电话：</span><span id="content">${webConfig.phone }</span></li>
        <li><span id="lable">联系QQ：</span><span id="content">${webConfig.qq }</span></li>
        <li><span id="lable">网站邮件：</span><span id="content">${webConfig.email }</span></li>
        <li><span id="lable">网站名称：</span><span id="content">${webConfig.webName }</span></li>
        <li><span id="lable">工作地址：</span><span id="content">${webConfig.address }</span></li>
        <li><span id="lable">所属组织：</span><span id="content">${webConfig.organization }</span></li>

        
     </ul>
  </div>
</body>
</html>
