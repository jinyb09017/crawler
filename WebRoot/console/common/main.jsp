<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-

transitional.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>web信息工程研究中心 后台管理工作平台 by jinyb</title>
<link rel="stylesheet" type="text/css" href="console/css/style.css"/>
<script type="text/javascript" src="console/js/js.js"></script>
<script type="text/javascript" src="js/jquery-1.4.3.js"></script>

<script type="text/javascript">
    $(function(){
      
         var admin="${session.admin}";
         if(admin==null||admin=="")//此处是""在起作用的"
         {
            window.location.href="console/index.jsp";
         }
         
       
    });
</script>
</head>
<frameset rows="127,*,11" frameborder="no" border="0" framespacing="0">
	<frame src="console/common/top.jsp" name="topFrame" scrolling="No"
		noresize="noresize" id="topFrame" />
	<frame src="console/common/center.jsp" name="mainFrame" id="mainFrame" />
	<frame src="console/common/down.jsp" name="bottomFrame" scrolling="No"
		noresize="noresize" id="bottomFrame" />
</frameset>



</html>
