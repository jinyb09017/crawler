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

<title>新闻编辑</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath %>/console/ckeditor/ckeditor.js"></script>
</head>

<body>
<div class="list">
     <div class="head">增加文章</div>
     <hr id="line" />
     <form action="<%=path %>/article/article_upd.action" method=post> 
     <ul>
        <li><span id="lable">新闻栏目：</span>${clmn}</li>
        <li><span id="lable">新闻标题：</span><input type="text" name="article.title" value="${article.title }"></li>
        <li><span id="lable"> 新闻内容：</span><textarea class="ckeditor" name="article.content" >${article.content }</textarea></li>
        <li><span id="lable">新闻来源：</span><input type="text" name="article.resource" value="${article.resource }"></li>
        <li><span id="lable"> 是否推荐：</span><s:radio list="#{true:'是',false:'否'}" name="article.recomend" value="#request.article.recomend"></s:radio></li>
        
     </ul>
     <input type="hidden" name="article.id" value="${article.id }">
		<input type="submit" value="更新">
     
     </form>
  </div>
	<div>
		<form action="<%=path %>/article/article_upd.action" method=post> 
		 新闻栏目：${clmn}<br>
                       新闻标题：<input type="text" name="article.title" value="${article.title }"><br>
                       新闻内容：<textarea class="ckeditor" name="article.content">${article.content }</textarea><br>
                       新闻来源：<input type="text" name="article.resource" value="${article.resource }"><br>
		 是否推荐：<s:radio list="#{true:'是',false:'否'}" name="article.recomend" value="#request.article.recomend"></s:radio><br>
		<input type="hidden" name="article.id" value="${article.id }"><br>
		<input type="submit" value="更新"><br>
		</form>
	</div>
	
</body>
</html>
