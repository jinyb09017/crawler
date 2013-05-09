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
     <div class="head">当前爬虫配置</div>
     <hr id="line" />
     <ul>
        <li><span id="lable">配置人：</span><span id="content">${crawlerConfig.admin.name }</span></li>
        <li><span id="lable">配置时间：</span><span id="content">${crawlerConfig.ccTime }</span></li>
        <li><span id="lable">配置站点总数：</span><span id="content">${fn:length(crawlerConfig.seeds) }</span></li>
        <li><span id="lable">配置站点列表：</span><span id="content">${clusterHistory.chCclId }</span></li>
       
        
        
     </ul>
     <table >
		   
		   <c:forEach items="${crawlerConfig.seeds }" var="seed" varStatus="s">
		   <tr>
		   <td style="width:40px">${s.index+1 }</td><td>${seed }</td>
		    </tr>
		</c:forEach>
		  
		</table>
		<form id="crawler" action="crawler/crawler_startCrawler" method="post">
		   <input type="checkbox" value="是否确认当前配置">
		   <input type="submit" value="开始爬取">
		</form>
  </div>
	
</body>
</html>
