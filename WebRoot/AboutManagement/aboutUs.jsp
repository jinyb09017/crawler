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

<title>热点词列表</title>
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

<body>
<c:import url="/common/header.jsp"></c:import>
    <div class="content">
    <div class="content_resize">
      <div class="mainbar">
        <div class="article">
          <h2><span><cufon class="cufon cufon-canvas" alt="Contact" style="width: 83px; height: 24px;"><canvas width="135" height="32" style="width: 135px; height: 32px; top: -5px; left: -16px;"></canvas><cufontext>Contact</cufontext></cufon></span></h2><div class="clr"></div>
          <p>You can find more of my free template designs at my website. For premium commercial designs, you can check out DreamTemplate.com.</p>
        </div>
        <div class="article">
          <h2><span><cufon class="cufon cufon-canvas" alt="Send " style="width: 63px; height: 24px;"><canvas width="115" height="32" style="width: 115px; height: 32px; top: -5px; left: -16px;"></canvas><cufontext>Send </cufontext></cufon><cufon class="cufon cufon-canvas" alt="us" style="width: 26px; height: 24px;"><canvas width="72" height="32" style="width: 72px; height: 32px; top: -5px; left: -16px;"></canvas><cufontext>us</cufontext></cufon></span><cufon class="cufon cufon-canvas" alt=" " style="width: 7px; height: 24px;"><canvas width="59" height="32" style="width: 59px; height: 32px; top: -5px; left: -16px;"></canvas><cufontext> </cufontext></cufon><cufon class="cufon cufon-canvas" alt="mail" style="width: 44px; height: 24px;"><canvas width="97" height="32" style="width: 97px; height: 32px; top: -5px; left: -16px;"></canvas><cufontext>mail</cufontext></cufon></h2><div class="clr"></div>
          <form action="#" method="post" id="sendemail">
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
      <div id="myRightBar"></div>
      <div class="clr"></div>
      
      
      
    </div>
  </div>
	
	
	



<c:import url="/common/footer.jsp"></c:import>	

</body>
</html>
