<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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

<title>爬虫配置管理--增加配置</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
</head>

<body>
	<div>
				<form action="/crawler/crawler/crawlerConfig_add" method="get" >
			线程个数：<input type="text" name="crawlerConfig.ccThreads" value="" /><br>
			栏目站点：<s:checkboxlist list="#request.crawlerSeedsMap"  listKey="key" 
			listValue="value" name="crawlerConfig.ccWebSeeds"></s:checkboxlist><br>
			<input type="submit" value="添加" />
		</form>
	</div>
</body>
</html>
