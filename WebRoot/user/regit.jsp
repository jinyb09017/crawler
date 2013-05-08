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

<title>欢迎您的注册</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
<script type="text/javascript" src="js/jquery-1.4.3.js"></script>

<script type="text/javascript" src="js/checkregist.js" charset="gbk"></script>
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
			<form name="ctl00" method="post"
					 action="user/register.action" id="f">
				<h2>
					用户注册
				</h2>
				<table class="tab_login" id="regittable">
					<tr>
						<td valign="bottom" class="w1">
							请填写您的Email地址：
						</td>
						<td>
							<input name="user.email" type="text" id="txtEmail" class="text_input"/><div class="text_left" id="emailValidMsg">
								    <img src="" alt="" id="emailImage"/>
									<span id="email.info" style="color:red"></span></div>
							
						</td>
					</tr>
					<tr>
						<td valign="bottom" class="w1">
							设置您的昵称：
						</td>
						<td>
							<input name="user.name" type="text" id="txtNickName" class="text_input"/>
							<div class="text_left" id="nickNameValidMsg">
							 <img src="" alt="" id="nicknameImage"/>
								<span id="name.info" style="color:red"></span>
								
								
							</div>
						</td>
					</tr>
					<tr><td></td><td>
									您的昵称可以由小写英文字母、中文、数字组成，
									长度4－20个字符，一个汉字为两个字符。
								</td></tr>
					<tr>
						<td valign="bottom" class="w1">
							设置密码：
						</td>
						<td>
							<input name="user.pwd" type="password" id="txtPassword"
								/>
							<div class="text_left" id="passwordValidMsg">
							<img src="" alt="" id="pwdInfoimage"/>
								<span id="password.info" style="color:red"></span>
								
								
							</div>
						</td>
					</tr>
					<tr><td></td><td>
									您的密码可以由大小写英文字母、数字组成，长度6位。
								</td></tr>
					<tr>
					<tr>
						<td valign="bottom" >
							再次输入您设置的密码：
						</td>
						<td>
							<input name="password1" type="password" id="txtRepeatPass"
								 />
							<div class="text_left" id="repeatPassValidMsg">
							<img src="" alt="" id="password1Image"/>
							<span id="password1.info" style="color:red"></span>
							</div>
						</td>
					</tr>
					<tr>
						<td valign="bottom" class="w1">
							验证码：
						</td>
						<td>
							<img id='imgVcode' src="user/imageCode.action" /><!-- 这个地方得到的是一个imageStream -->
							<input name="number" type="text" id="txtVerifyCode"
								class="yzm_input" />
							
						</td>
					</tr>
					<tr><td></td><td><div >
								<p class="t1">
									<span id="vcodeValidMsg">请输入图片中的四个字母。</span>
									<img src="" alt="" id="numberImage"/>
									<span id="number.info" style="color:red"></span>
									<a href="javascript:;" onclick="document.getElementById('imgVcode').src = 'user/imageCode.action?dt=<%=new Date().getTime() %>'">看不清楚？换个图片</a>
								</p>
							</div></td></tr>
					<tr>
					<tr>
					  <td>
					  <div>  
					     <input type="checkbox" id="ufo" />
					  </div>
					  </td><td><a href="">同意本网站的观点</a></td>
					</tr>
				</table>

				<div >

					<input id="btnClientRegister" name="submit"  type="submit" value="注 册"/>
					&nbsp;&nbsp;
					<input id="btnReset" name="" type="button" value="重置">
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




