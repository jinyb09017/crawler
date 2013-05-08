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
	<div>
		<form action="/crawler/cluster/clusterConfig_add" method=post> 
		聚类个数：<input type="text" name="clusterConfig.clcClusterNum"><br>
		热点词数：<input type="text" name="clusterConfig.clcWordNum"><br>
		开始时间：<input id="startTime" type="text" name="clusterConfig.clcStartTime">    结束时间：<input type="text" id="endTime" name="clusterConfig.clcEndTime">
		<input type="submit" value="增加">
		</form>
	</div>
	
</body>
</html>
