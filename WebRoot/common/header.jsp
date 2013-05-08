<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/cufon-yui.js"></script>
<script type="text/javascript" src="js/arial.js"></script>
<script type="text/javascript" src="js/cuf_run.js"></script>
<script type="text/javascript" src="js/jquery-1.4.3.js"></script>
<script type="text/javascript" src="js/radius.js"></script>
	
  </head>
  
  <body>
    <div class="header">
    
    <div class="header_resize">
      <div><span><c:if test="${!empty username}">欢迎：${username} <a href="user/outLogin.action" >退出</a></c:if>&nbsp;</span><c:if test="${empty username }"><a href="user/login.jsp" >登陆</a></c:if></div>
      <div class="menu_nav">
        <ul>
          <li class="active"><a href="webinfeng/admin_index" style="border-top-right-radius: 6px; border-top-left-radius: 6px;">首页</a></li>
          <li><a href="webinfeng/clusterResult_list" style="border-top-right-radius: 6px; border-top-left-radius: 6px;">聚类列表</a></li>
          <li><a href="webinfeng/word_list" style="border-top-right-radius: 6px; border-top-left-radius: 6px;">热点词表</a></li>
          <li><a href="blog.html" style="border-top-right-radius: 6px; border-top-left-radius: 6px;">新闻搜索</a></li>
          <li><a href="blog.html" style="border-top-right-radius: 6px; border-top-left-radius: 6px;">知识普及</a></li>
          <li><a href="AboutManagement/messageUs.jsp" style="border-top-right-radius: 6px; border-top-left-radius: 6px;">Contact Us</a></li>
           <li><a href="AboutManagement/aboutUs.jsp" style="border-top-right-radius: 6px; border-top-left-radius: 6px;">About Us</a></li>
        </ul>
      </div>
      <div class="clr"></div>
      <div class="logo"><h1><a href="index.html"><cufon class="cufon cufon-canvas" alt="Free" style="width: 75px; height: 42px;"><canvas width="157" height="56" style="width: 157px; height: 56px; top: -9px; left: -28px;"></canvas><cufontext>Free</cufontext></cufon><span><cufon class="cufon cufon-canvas" alt="sim" style="width: 57px; height: 42px;"><canvas width="127" height="56" style="width: 127px; height: 56px; top: -9px; left: -28px;"></canvas><cufontext>sim</cufontext></cufon></span><cufon class="cufon cufon-canvas" alt=" " style="width: 9px; height: 42px;"><canvas width="102" height="56" style="width: 102px; height: 56px; top: -9px; left: -28px;"></canvas><cufontext> </cufontext></cufon><small><cufon class="cufon cufon-canvas" alt="put " style="width: 21px; height: 12px;"><canvas width="46" height="16" style="width: 46px; height: 16px; top: -2px; left: -8px;"></canvas><cufontext>put </cufontext></cufon><cufon class="cufon cufon-canvas" alt="your " style="width: 27px; height: 12px;"><canvas width="53" height="16" style="width: 53px; height: 16px; top: -2px; left: -8px;"></canvas><cufontext>your </cufontext></cufon><cufon class="cufon cufon-canvas" alt="slogan " style="width: 39px; height: 12px;"><canvas width="65" height="16" style="width: 65px; height: 16px; top: -2px; left: -8px;"></canvas><cufontext>slogan </cufontext></cufon><cufon class="cufon cufon-canvas" alt="here" style="width: 25px; height: 12px;"><canvas width="47" height="16" style="width: 47px; height: 16px; top: -2px; left: -8px;"></canvas><cufontext>here</cufontext></cufon></small></a></h1></div>
      <div class="clr"></div>
    </div>
  </div>
  </body>
</html>
