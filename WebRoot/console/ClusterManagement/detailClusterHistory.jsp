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
     <div class="head">配置历史详细</div>
     <hr id="line" />
     <ul>
        <li><span id="lable">配置人：</span><span id="content">${clusterHistory.admin.name }</span></li>
        <li><span id="lable">聚类时间：</span><span id="content">${clusterHistory.chTime }</span></li>
        <li><span id="lable">聚类文章基数：</span><span id="content">${clusterHistory.chWebpageNum}</span></li>
        <li><span id="lable">聚类配置id：</span><span id="content">${clusterHistory.chCclId }</span></li>
        <li><span id="lable">是否为默认配置：</span><span id="content"><c:if test="${clusterHistory.present==true }">是</c:if><c:if test="${clusterHistory.present==false }">否</c:if></span></li>
        
        
     </ul>
  </div>
	
</body>
</html>
