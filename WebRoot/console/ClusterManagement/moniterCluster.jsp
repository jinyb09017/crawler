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
     <div class="head">当前聚类配置</div>
     <hr id="line" />
     <ul>
        <li><span id="lable">聚类设置人：</span><span id="content">${clusterConfig.admin.name }</span></li>
        <li><span id="lable">聚类开始时间：</span><span id="content">${clusterConfig.clcStartTime }</span></li>
        <li><span id="lable">聚类结束时间：</span><span id="content">${clusterConfig.clcEndTime }</span></li>
        <li><span id="lable">聚类个数：</span><span id="content">${clusterConfig.clcClusterNum }</span></li>
        <li><span id="lable">聚类热点词数：</span><span id="content">${clusterConfig.clcWordNum }</span></li>


        
     </ul>
     <form id="cluster" action="cluster/cluster_startCluster" method="post">
		   <input type="checkbox" value="是否确认当前配置">
		   <input type="submit" value="开始爬取">
		</form>
  </div>
	
	
</body>
</html>
