<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<base href="<%=basePath%>">
<title>无标题文档</title>
<script type="text/javascript" src="console/js/jquery.js"></script>
<script type="text/javascript" src="console/js/chili-1.7.pack.js"></script>
<script type="text/javascript" src="console/js/jquery.easing.js"></script>
<script type="text/javascript" src="console/js/jquery.dimensions.js"></script>
<script type="text/javascript" src="console/js/jquery.accordion.js"></script>
<script language="javascript">
	jQuery().ready(function(){
		jQuery('#navigation').accordion({
			header: '.head',
			navigation1: true, 
			event: 'click',
			fillSpace: true,
			animated: 'bounceslide'
		});
	});
</script>
<style type="text/css">
<!--
html,body{ 
margin:0px; 
height:100%; 
} 
body {
	margin:0px;
	padding:0px;
	font-size: 12px;
}
#navigation {
	margin:0px;
	padding:0px;
	width:147px;
}
#navigation a.head {
	cursor:pointer;
	background:url(console/images/main_34.gif) no-repeat scroll;
	display:block;
	font-weight:bold;
	margin:0px;
	padding:5px 0 5px;
	text-align:center;
	font-size:12px;
	text-decoration:none;
}
#navigation ul {
	border-width:0px;
	margin:0px;
	padding:0px;
	text-indent:0px;
}
#navigation li {
	list-style:none; display:inline;
}
#navigation li li a {
	display:block;
	font-size:12px;
	text-decoration: none;
	text-align:center;
	padding:3px;
}
#navigation li li a:hover {
	background:url(console/images/tab_bg.gif) repeat-x;
		border:solid 1px #adb9c2;
}
-->
</style>
</head>
<body>
<div  style="height:100%;">
  <ul id="navigation">
    <li> <a class="head">网站配置管理</a>
      <ul>
        <li><a href="webConfig/webConfig_detail" target="rightFrame">查看网站配置</a></li>
        
        
      </ul>
      
    </li>
    <li> <a class="head">爬虫设置管理</a>
      <ul>
        <li><a href="crawler/crawlerSeeds_list" target="rightFrame">查看种子站点</a></li>
        <li><a href="crawler/crawlerConfig_list" target="rightFrame">查看爬虫配置</a></li>
        <li><a href="crawler/crawlerHistory_list" target="rightFrame">查看爬虫历史</a></li>
        <li><a href="crawler/crawlerConfig_toPresent" target="rightFrame">启动爬虫主页</a></li>
        
      </ul>
    </li>
    <li> <a class="head">聚类设置管理</a>
      <ul>
        <li><a href="cluster/clusterConfig_list" target="rightFrame">查看聚类配置</a></li>
        <li><a href="cluster/clusterResult_list" target="rightFrame">查看聚类结果</a></li>
        <li><a href="cluster/clusterHistory_list" target="rightFrame">查看聚类历史</a></li>
        <li><a href="cluster/clusterConfig_moniter" target="rightFrame">启动聚类主页</a></li>
        
      </ul>
    </li>
    <li> <a class="head">搜索设置管理</a>
      <ul>
        <li><a href="messages.php" target="rightFrame">查看/删除留言</a></li>
        <li><a href="comments.php" target="rightFrame">查看/删除评论</a></li>
      </ul>
    </li>
    <li> <a class="head">资讯信息管理</a>
      <ul>
        <li><a href="AddLink.php" target="rightFrame">栏目管理</a></li>
        <li><a href="Links.php" target="rightFrame">新闻管理</a></li>
      </ul>
    </li>
     <li> <a class="head">用户信息管理</a>
      <ul>
        <li><a href="users/usermsg_findAllUser" target="rightFrame">用户信息列表</a></li>
        
      </ul>
    </li>
    <li><a class="head">超级用户管理管理</a>
      <ul>
        <li><a href="users/usermsg_findAllAdmin" target="rightFrame">管理员列表</a></li>
        
      </ul>
    </li>
    <li> <a class="head">版本信息管理</a>
      <ul>
        <li><a href="http://www.webinfeng.com" target="_blank">by jinyb</a></li>
      </ul>
    </li>
  </ul>
</div>
</body>
</html>
