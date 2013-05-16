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

<title>知识天地</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.4.3.js"></script>
	
</head>

<body>
<c:import url="/common/header.jsp"></c:import>
    <div class="content">
    <div class="content_resize">
      <div class="mainbar">
        <div class="article">
          <p>${article.title }</p>
          <p>Posted by <a href="#">${article.resource }</a>  <span>&nbsp;•&nbsp;</span>  发表时间：${article.time }</p>
          <p class="newsContent">${article.content }</p>
          <p>Tagged: <a href="#">orci</a>, <a href="#">lectus</a>, <a href="#">varius</a>, <a href="#">turpis</a></p>
          <p><a href="#"><strong>Comments (3)</strong></a>  <span>&nbsp;•&nbsp;</span> ${article.time } <span>&nbsp;•&nbsp;</span>  <a href="#"><strong>Edit</strong></a></p>
        </div>
        
        <div class="article">
          <h2><span><cufon class="cufon cufon-canvas" alt="Leave " style="width: 73px; height: 24px;"><canvas width="124" height="32" style="width: 124px; height: 32px; top: -5px; left: -16px;"></canvas><cufontext>Leave </cufontext></cufon><cufon class="cufon cufon-canvas" alt="a" style="width: 14px; height: 24px;"><canvas width="59" height="32" style="width: 59px; height: 32px; top: -5px; left: -16px;"></canvas><cufontext>a</cufontext></cufon></span><cufon class="cufon cufon-canvas" alt=" " style="width: 7px; height: 24px;"><canvas width="59" height="32" style="width: 59px; height: 32px; top: -5px; left: -16px;"></canvas><cufontext> </cufontext></cufon><cufon class="cufon cufon-canvas" alt="Reply" style="width: 62px; height: 24px;"><canvas width="108" height="32" style="width: 108px; height: 32px; top: -5px; left: -16px;"></canvas><cufontext>Reply</cufontext></cufon></h2><div class="clr"></div>
          <form action="#" method="post" id="leavereply">
          <ol><li>
            <label for="name">Name (required)</label>
            <input id="name" name="name" class="text">
          </li><li>
            <label for="email">Email Address (required)</label>
            <input id="email" name="email" class="text">
          </li><li>
            <label for="website">Website</label>
            <input id="website" name="website" class="text">
          </li><li>
            <label for="message">Your Message</label>
            <textarea id="message" name="message" rows="8" cols="50"></textarea>
          </li><li>
            <input type="image" name="imageField" id="imageField" src="images/submit.gif" class="send">
            <div class="clr"></div>
          </li></ol>
          </form>
          
        </div>
      </div>
      <div class="sidebar">
        <div class="searchform">
          <form id="formsearch" name="formsearch" method="post" action="">
            <span><input name="editbox_search" class="editbox_search" id="editbox_search" maxlength="80" value="Search our ste:" type="text"></span>
            <input name="button_search" src="images/search_btn.gif" class="button_search" type="image">
          </form>
        </div>
        <div class="clr"></div>
        
        <div class="gadget">
        <h3>知识普及</h3>
          <div class="clr"></div>
          <ul class="ex_menu">
            <c:forEach items="${column.article }" var="article">
                     <li><a href="article/article_detail?article.id=${article.id }" title="${article.title }">${article.title }</a></li>
            </c:forEach>
           
          </ul>
        </div>
      </div>
      <div class="clr"></div>
      
      
      
    </div>
  </div>
	
	
	



<c:import url="/common/footer.jsp"></c:import>	

</body>
</html>
