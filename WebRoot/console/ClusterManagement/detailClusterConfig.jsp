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
     <div class="head">聚类配置详细</div>
     <hr id="line" />
     <ul>
        <li><span id="lable">配置人：</span><span id="content">${clusterConfig.admin.name }</span></li>
        <li><span id="lable">聚类数目：</span><span id="content">${clusterConfig.clcClusterNum }</span></li>
        <li><span id="lable">聚类热点词数：</span><span id="content">${clusterConfig.clcWordNum }</span></li>
        <li><span id="lable">聚类开始时间：</span><span id="content">${clusterConfig.clcStartTime }</span></li>
        <li><span id="lable">聚类结束时间：</span><span id="content">${clusterConfig.clcEndTime }</span></li>
        <li><span id="lable">是否当前配置：</span><span id="content"><c:if test="${clusterConfig.present==true }">是</c:if><c:if test="${clusterConfig.present==false }">否</c:if></span></li>
        <li><span id="lable">所属组织：</span><span id="content">${webConfig.organization }</span></li>

        
     </ul>
  </div>
	
</body>
</html>
