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
<script type="text/javascript" src="js/checklogin.js" charset="gbk"></script>
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
		<div>登录社区</div>

		<form method="post" action="user/login.action" id="ctl00">
			<table id="logintable">

				<tr>
					<td><span>请输入Email地址：</span></td>
				</tr>
				<tr>
					<td><input type="text" name="user.email" id="txtUsername"
						class="textbox" /> <span id="username.info" style="color:red"></span>
					</td>
				</tr>
				<tr>
					<td><span class="blank">密码：</span></td>
				</tr>
				<tr>
					<td><input type="password" name="user.pwd" id="txtPassword"
						class="textbox" /> <span id="password.info" style="color:red"></span>
					</td>
				</tr>
				<tr>
					<td><span>验证码：</span></td>
				</tr>
				<tr>
					<td><img id='imgVcode' src="user/imageCode.action" /><br> <input
						name="number" type="text" id="txtVerifyCode" class="yzm_input" />
						<div>
							<p class="t1">
								<span id="vcodeValidMsg">请输入图片中的四个字母。</span> <img src="" alt=""
									id="numberImage" /> <span id="number.info" style="color:red"></span>
								<a href="javascript:;"
									onclick="document.getElementById('imgVcode').src = 'user/imageCode.action?'+(new Date()).getTime()">看不清楚？换个图片</a>
							</p>
						</div></td>
					</td>
				</tr>
				<tr>
					<td><input type="submit" id="btnSignCheck"
						class="button_enter" value="登 录" /></td>
					<td><span style="color: red;">${failCode} </span></td>
				</tr>
			</table>
		</form>
		<div>
		<p>您还不是社区用户？<a href="user/regit.jsp">创建一个新用户&gt;&gt;</a></p>
	</div>
	</div>
	
        
       
      
      
      </div>
      <div id="myRightBar"></div>
      <div class="clr"></div>
      
      
      
    </div>
  </div>
	
	
	



<c:import url="/common/footer.jsp"></c:import>	

	

</body>
</html>


