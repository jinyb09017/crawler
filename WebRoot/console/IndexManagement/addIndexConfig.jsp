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

<title>增加聚类配置</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<%=basePath %>/console/ckeditor/ckeditor.js"></script>
	<link type="text/css" href="http://code.jquery.com/ui/1.9.1/themes/smoothness/jquery-ui.css" rel="stylesheet" />

    <link href="<%=basePath %>jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.css" type="text/css" />
    <link href="<%=basePath %>jQuery-Timepicker-Addon/demos.css" rel="stylesheet" type="text/css" />

    <script src="http://code.jquery.com/jquery-1.8.2.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="http://code.jquery.com/ui/1.9.1/jquery-ui.min.js"></script>
    <script src="<%=basePath %>jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.js" type="text/javascript"></script>

    <!--中文-->
    <script src="<%=basePath %>js/jquery.ui.datepicker-zh-CN.js.js" type="text/javascript" charset="gb2312"></script>
    <script src="<%=basePath %>js/jquery-ui-timepicker-zh-CN.js" type="text/javascript"></script>
    <script type="text/javascript">
        jQuery(function () {
            // 时间设置
            jQuery('#startTime').datetimepicker({
                timeFormat: "HH:mm:ss",
                dateFormat: "yy-mm-dd"
            });
             jQuery('#endTime').datetimepicker({
                timeFormat: "HH:mm:ss",
                dateFormat: "yy-mm-dd"
            });

        });
    </script>
	
</head>

<body>
     <div class="list">
     <div class="head">增加索引配置</div>
     <hr id="line" />
     <form action="/crawler/index/indexConfig_add" method=post> 
     <ul>
        <li><span id="lable">聚引目录：</span><input type="text" name="indexConfig.dir"></li>
        <li><span id="lable">开始时间：</span><input id="startTime" type="text" name="indexConfig.startTime"></li>
        <li><span id="lable">结束时间：</span><input type="text" id="endTime" name="indexConfig.endTime"></li>
        
        <c:if test="${!empty msg }"><li style="color: red">${msg }</li></c:if>
        
     </ul>
     </form>
     <ul id="commit">
        <li><span id="lable"><input type="submit" value="增加"></span></li>
     </ul>
     </form>
  </div>
	
	
</body>
</html>
