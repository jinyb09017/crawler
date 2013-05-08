<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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


	
  </head>

<div class="sidebar">
        <div class="searchform">
          <form id="formsearch" name="formsearch" method="post" action="">
            <span><input name="editbox_search" class="editbox_search" id="editbox_search" maxlength="80" value="Search our ste:" type="text"></span>
            <input name="button_search" src="images/search_btn.gif" class="button_search" type="image">
          </form>
        </div>
        <div class="clr"></div>
        <div class="gadget">
        
              <h3>聚类热点词列表</h3>
          <div class="clr"></div>
          <div>
                 <c:forEach items="${wordList }" var="word">
                 <span><a href="webinfeng/news_listByWordId?word.id=${word.id }">${word.word }</a></span><span>  &nbsp;</span>
                 </c:forEach>


          </div>
          
        </div>
        <div class="gadget">
        <h3>聚类结果列表</h3>
          <div class="clr"></div>
          <ul class="ex_menu">
            <c:forEach items="${clusterResultList }" var="clusterResult">
                     <li><a href="webinfeng/news_listByResultId?clusterResult.id=${ clusterResult.id}" title="${clusterResult.cTitle }">${clusterResult.cTitle }</a></li>
             </c:forEach>
           
          </ul>
        </div>
      </div>