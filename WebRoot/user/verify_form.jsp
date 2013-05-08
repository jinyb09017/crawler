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

<title>用户登陆</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript" src="js/jquery-1.4.3.js"></script>
<script type="text/javascript" src="js/verifyform.js" charset="gbk"></script>
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
		<form action="user/checkEmail.action" method="post" id="emailcheck">
			<div>
			<div>感谢您注册本社区！现在请按以下步骤完成您的注册!</div>
				
				<div >
					<div>第一步：查看您的电子邮箱</div>
					<div class="mess reduce_h">
					    <input type="text" name="email" value="${user.email }" style="display: none"/>
						我们给您发送了验证邮件，邮件地址为：
						<span class="red"><span id="lblEmail">${user.email }</span>
						</span>
						<span class="t1"> 
						请登录您的邮箱收信。
						</span>
					</div>
					<div>第二步：输入验证码</div>
					
					<div >
						<span class="write_in">输入您收到邮件中的验证码: </span>
						<input name="code" type="text" id="validatecode"  />
						<input  type="submit" value="完 成" id="Button1" />
						<span id="validatecodeEmail" >${codeInfo }</span>
					</div>
				</div>
			</div>
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





