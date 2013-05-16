<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css" href="console/css/style.css"/>
<style type="text/css">
<!--
body {background: white;}

-->

</style>
</head>
<body>
  <div class="list">
     <div class="head">索引配置详细</div>
     <hr id="line" />
     <ul>
        <li><span id="lable">配置人：</span><span id="content">${indexConfig.admin.name }</span></li>
        <li><span id="lable">索引目录：</span><span id="content">${indexConfig.dir }</span></li>        
        <li><span id="lable">索引开始时间：</span><span id="content">${indexConfig.startTime }</span></li>
        <li><span id="lable">索引结束时间：</span><span id="content">${indexConfig.endTime }</span></li>

        <li><span id="lable">索引文章基数：</span><span id="content">${indexConfig.webpagenum }</span></li>
        <li><span id="lable">是否当前配置：</span><span id="content"><c:if test="${indexConfig.present==true }">是</c:if><c:if test="${indexConfig.present==false }">否</c:if></span></li>
        <li><span id="lable">是否已经覆盖：</span><span id="content"><c:if test="${indexConfig.overRide==true }">是</c:if><c:if test="${indexConfig.overRide==false }">否</c:if></span></li>

        
     </ul>
     <form id="index" action="index/myIndex_startIndex" method="post">
		   <input type="checkbox" value="是否确认当前配置">
		   <input type="submit" value="开始爬取">
	 </form>
  </div>
  
</body>
</html>
