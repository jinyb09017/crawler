<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    
    <title>欢迎访问WEB信息分析系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.4.3.js"></script>
	<script type="text/javascript">
	   $(function(){
	      $.post("webinfeng/admin_listHot",{},function(data){
	         $("#myRightBar").html(data);        
	      });
	   });
	</script>
	
  </head>
  
  <c:import url="common/header.jsp"></c:import>
 <div class="content">
    <div class="content_resize">
      <div class="mainbar">
        <div class="article">
          <h2><span><cufon class="cufon cufon-canvas" alt="Template" style="width: 98px; height: 24px;"><canvas width="143" height="32" style="width: 143px; height: 32px; top: -5px; left: -16px;"></canvas><cufontext>Template</cufontext></cufon></span><cufon class="cufon cufon-canvas" alt=" " style="width: 7px; height: 24px;"><canvas width="59" height="32" style="width: 59px; height: 32px; top: -5px; left: -16px;"></canvas><cufontext> </cufontext></cufon><cufon class="cufon cufon-canvas" alt="License" style="width: 83px; height: 24px;"><canvas width="128" height="32" style="width: 128px; height: 32px; top: -5px; left: -16px;"></canvas><cufontext>License</cufontext></cufon></h2><div class="clr"></div>
          <p><span class="date">Date: <a href="#">16.03.2018</a></span> &nbsp;|&nbsp; Posted by <a href="#">Owner</a> &nbsp;|&nbsp; Filed under <a href="#">templates</a>, <a href="#">internet</a></p>
          <a href="#" class="com" style="border-bottom-left-radius: 6px; border-top-left-radius: 6px;">11</a>
          <img src="images/img1.jpg" width="625" height="205" alt="image">
          <p>
             <c:forEach items="${clusterResultList }" var="clusterResult" varStatus="s">
                    <div class="cluster">
                        <div><span>${s.index}</span>${clusterResult.cTitle }</div>
                   <c:forEach items="${clusterResult.news }" var="news" varStatus="m" begin="0" end="3"><!-- 默认显示不四条信息 -->
                       <div><span>${m.index+1 }</span><a href="webinfeng/news_detail?news.newsId=${news.newsId }">
                       <c:if test="${fn:indexOf(news.newsTitle, '|')>0 }">${fn:substring(news.newsTitle, 0, fn:indexOf(news.newsTitle, '|'))}</c:if>
                       </a><br></div>  
                        
                   </c:forEach>
                   <c:if test="${(s.index+1)%2==0 }"><div class="clr"></div></c:if>
                   <div><span>……</span></div>
        
             </div>
    
           </c:forEach>
          </p>
          <div class="clr"></div>
          <p class="spec"><a href="#" class="rm">Read more »</a></p>
        </div>
        <div class="article">
          <h2><span><cufon class="cufon cufon-canvas" alt="Office" style="width: 63px; height: 24px;"><canvas width="108" height="32" style="width: 108px; height: 32px; top: -5px; left: -16px;"></canvas><cufontext>Office</cufontext></cufon></span><cufon class="cufon cufon-canvas" alt=" " style="width: 7px; height: 24px;"><canvas width="59" height="32" style="width: 59px; height: 32px; top: -5px; left: -16px;"></canvas><cufontext> </cufontext></cufon><cufon class="cufon cufon-canvas" alt="interior" style="width: 74px; height: 24px;"><canvas width="124" height="32" style="width: 124px; height: 32px; top: -5px; left: -16px;"></canvas><cufontext>interior</cufontext></cufon></h2><div class="clr"></div>
          <p><span class="date">Date: <a href="#">15.03.2010</a></span> &nbsp;|&nbsp; Posted by <a href="#">Owner</a> &nbsp;|&nbsp; Filed under <a href="#">templates</a>, <a href="#">internet</a></p>
          <a href="#" class="com" style="border-bottom-left-radius: 6px; border-top-left-radius: 6px;">7</a>
          <img src="images/img2.jpg" width="625" height="205" alt="image">
          <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. Morbi tincidunt, orci ac convallis aliquam, lectus turpis varius lorem, eu posuere nunc justo tempus leo. Donec mattis, purus nec placerat bibendum, dui pede condimentum odio, ac blandit ante orci ut diam. Cras fringilla magna. Phasellus suscipit, leo a pharetra condimentum, lorem tellus eleifend magna, eget fringilla velit magna id neque. Curabitur vel urna. In tristique orci porttitor ipsum. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec libero. Suspendisse bibendum. Cras id urna. Morbi tincidunt, orci ac convallis aliquam.</p>
          <p class="spec"><a href="#" class="rm">Read more »</a></p>
        </div>
        <p class="pages"><small>Page 1 of 2 &nbsp;&nbsp;&nbsp;</small> <span>1</span> <a href="#">2</a> <a href="#">»</a></p>
      </div>
     <div id="myRightBar"></div>
      <div class="clr"></div>
    </div>
  </div>
      
  <c:import url="common/footer.jsp"></c:import>
  
</html>
