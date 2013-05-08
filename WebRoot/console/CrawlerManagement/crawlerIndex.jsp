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

<title>爬虫管理</title>
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
		<table >
			<thead>
			  <tr>
				<td>栏目数</td>
				<td>栏目名称</td>
				<td>删除</td>
				<td>编辑</td>
			 </tr>
			</thead>
			<tbody>
			<c:forEach items="${columnList}" var="column" begin="0"  varStatus="s">
                <tr>
				<td>${s.index+1}</td>
				<td>${column.column}</td>
				<td><a href="<%=path %>/column/column_del.action?myCol.id=${column.id}">删除</a></td>
				<td><a href="<%=path %>/column/column_search.action?myCol.id=${column.id}">编辑</a></td>
				</tr>
            </c:forEach>
			   
			</tbody>
		</table>
	</div>
	<div><a href="<%=path %>/console/ColumnManagement/addColumn.jsp"><input type="button" value="增加" /></a></div>
</body>
</html>
