var checkFlag={"email":false,"emailajax":false,"nickname":false,"pwd":false,"repeatPwd":false,"code":false,"ufo":false};
$(function(){
	$('#txtPassword').blur(function check_pwd(){
		var pwd=$('#txtPassword').val();
		if(pwd==''){
			$('#password\\.info').html('请输入密码！');
			 checkFlag.pwd=false;
			 $('#pwdInfoimage').attr("src","/crawler/images/unchecked.gif");
		}else{
			var reg=/^[A-Za-z0-9]{6,20}$/;
			if(reg.test(pwd)){
				$('#password\\.info').html('密码输入正确！');
				 checkFlag.pwd=true;
				 $('#pwdInfoimage').attr("src","/crawler/images/checked.gif");
			}else{
				$('#password\\.info').html('密码为6~20个字符(由大小写字母、数字组成)');
				 checkFlag.pwd=false;
				 $('#pwdInfoimage').attr("src","/crawler/images/unchecked.gif");
			}
		}
	});
	$('#txtRepeatPass').blur(function check_Repeatpwd(){
		var repeatpwd=$('#txtRepeatPass').val();
		var pwd=$('#txtPassword').val();
		if(repeatpwd==''){
			$('#password1\\.info').html('请输入密码！');
			checkFlag.repeatPwd=false;
			$('#password1Image').attr("src","/crawler/images/unchecked.gif");
		}else{
			var reg=/^[A-Za-z0-9]{6,20}$/;
			if(reg.test(repeatpwd)){
				if(pwd==repeatpwd){
					$('#password1\\.info').html('密码一致！');
					 checkFlag.repeatPwd=true;
					 $('#password1Image').attr("src","/crawler/images/checked.gif");
				}else{
					$('#password1\\.info').html('两次输入的密码不一致！');
					 checkFlag.repeatPwd=false;
					 $('#password1Image').attr("src","/crawler/images/unchecked.gif");
				}
				
			}else{
				$('#password1\\.info').html('密码为6~20个字符(由大小写字母、数字组成)');
				 checkFlag.repeatPwd=false;
				 $('#password1Image').attr("src","/crawler/images/unchecked.gif");
			}
		}
	});
	
	//alert("123");
	$('#txtNickName').blur(function check_nickname(){
		var nickname=$('#txtNickName').val();
		if(nickname==''){
			$('#name\\.info').html('请输入昵称！');
			checkFlag.nickname=false;
			 $('#nicknameImage').attr("src","/crawler/images/unchecked.gif");
		}else{
			var reg=/^[\u4E00-\u9FA5\w\d]{4,20}$/;
			if(reg.test(nickname)){
				$('#name\\.info').html('昵称可用！');
				checkFlag.nickname=true;
				 $('#nicknameImage').attr("src","/crawler/images/checked.gif");
			}else{
				$('#name\\.info').html('昵称格式不对！');
				checkFlag.nickname=false;
				 $('#nicknameImage').attr("src","/crawler/images/unchecked.gif");
			}
		}
	});
	
	
	$('#txtEmail').blur(function(){
		var email=$('#txtEmail').val();
		checkFlag.email=false;
		checkFlag.emailajax=false;
		if(email==''){
			$('#email\\.info').html('请输入邮箱！');
			checkFlag.email=false;
			$('#emailImage').attr("src","/crawler/images/unchecked.gif");
			return ;
		}
		var reg= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if(!reg.test(email)){
			$('#email\\.info').html('邮箱格式不对！');
			checkFlag.email=false;
			$('#emailImage').attr("src","/crawler/images/unchecked.gif");
			return ;
		}
		checkFlag.email=true;//通过非空和格式认证
		$.post("user/validEmail.action",{"email":email},function(ok){
			if(ok){
				$('#email\\.info').html('邮箱可用');
				checkFlag.emailajax=true;
				$('#emailImage').attr("src","/crawler/images/checked.gif");
			}else{
				$('#email\\.info').html('该邮箱已经注册账户！');
				checkFlag.emailajax=false;
				$('#emailImage').attr("src","/crawler/images/unchecked.gif");
			}
		});
	});
	
	$('#txtVerifyCode').blur(function (){
		if($('#txtVerifyCode').val()==""){
		 $('#number\\.info').html('验证码不能为空！');
		 checkFlag.code=false;
		 $('#numberImage').attr("src","/crawler/images/unchecked.gif");
		 return ;
		}
		//alert('1');
		$.post("user/validate.action",{"code":$("#txtVerifyCode").val()},function(data){
			//特别注意jquery版本问题，对于josn数据解析有区别的
			
		if(data.ok){
		//alert('2');
		$('#number\\.info').html('验证码正确');
		checkFlag.code=true;
		 $('#numberImage').attr("src","/crawler/images/checked.gif");
		}else{
		//alert('3');
		$('#number\\.info').html('验证码错误');
		checkFlag.code=false;
		 $('#numberImage').attr("src","/crawler/images/unchecked.gif");
		}
		//alert('4');
		});
		});
		$('#imgVcode').click(function(){
		$(this).attr("src","user/imageCode.action?dt="+new Date().getTime());
		});
		
		$('#btnReset').click(function(){
			$('#txtEmail').val('');
			$('#txtNickName').val('');
			$('#txtRepeatPass').val('');
			$('#txtPassword').val('');
			$('#txtVerifyCode').val('');
			$('#password\\.info').html('');
			$('#pwdInfoimage').attr("src","");
			$('#password1\\.info').html('');
			 $('#password1Image').attr("src","");
			 $('#name\\.info').html('');
			 $('#nicknameImage').attr("src","");
			 $('#email\\.info').html('');
			 $('#emailImage').attr("src","");
			 $('#number\\.info').html('');
			 $('#numberImage').attr("src","");
		});
	//页面提交
	$('#f').submit(function(){
		if($("#ufo").attr("checked")==true){
			checkFlag.ufo=true;
		}
		if(checkFlag.email&&checkFlag.emailajax&&checkFlag.nickname&&checkFlag.pwd&&checkFlag.repeatPwd&&checkFlag.code&&checkFlag.ufo){
			return true;
		}else{
		  alert('表单数据填写有误，请检查！');
		  return false;
		}
	});
});

