<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>聚类管理</title>
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
       <form action="<%=path %>/news/news_list.action" method="post">
          <select name="column.id">
          <option value="">--请选择新闻栏目--</option>
          <c:forEach items="${columnList}" var="column" varStatus="index">
              <option value="${column.id}">${column.column}</option>
          </c:forEach>
              
          </select>
          <input type="submit" value="确定" />
       </form>
    </div>
	<div>
		<table >
			<thead>
			  <tr>
				<td>新闻数目</td>
				<td>新闻标题</td>
				<td>上传作者</td>
				<td>上传时间</td>
				<td>是否推荐</td>
				<td>删除</td>
				<td>修改</td>
			 </tr>
			</thead>
			<tbody>
			<c:forEach items="${newsList}" var="news" begin="0"  varStatus="s">
                <tr>
				<td>${s.index+1}</td>
				<td>${news.title}</td>
				<td>${news.title}</td>
				<td>${news.time}</td>
				<td>
				    <c:if test="${ news.recomend==true}">是</c:if>
			        <c:if test="${ news.recomend==false}">否</c:if>
				</td>
				<td><a href="<%=path %>/news/news_del.action?news.id=${news.id}">删除</a></td>
				<td><a href="<%=path %>/news/news_edit.action?news.id=${news.id}">编辑</a></td>
				</tr>
            </c:forEach>
			   
			</tbody>
		</table>
	</div>
	<div><a href="<%=path %>/news/news_forwardAdd.action"><input type="button" value="增加" /></a></div>
</body>
</html>
