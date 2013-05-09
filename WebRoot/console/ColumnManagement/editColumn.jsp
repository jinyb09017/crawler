<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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

<title>编辑栏目</title>
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
<div class="list">
     <div class="head">修改栏目</div>
     <hr id="line" />
    <form action="column/column_upd" method="get" > 
     <ul>
        <li><span id="lable">栏目名称：</span><input type="text" name="column" value="${col.column }" /></li>
        
       
     </ul>
      <input type="hidden" name="myCol.id" value="${col.id }">
			<input type="submit"
				value="更新" />
     </form>
  </div>
	
</body>
</html>
