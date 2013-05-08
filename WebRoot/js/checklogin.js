var checkFlag={"username":false,"pwd":false,"code":false};
$(function (){
	$('#txtUsername').val("");
	$('#txtPassword').val("");
	$('#txtVerifyCode').val("");
	$('#txtUsername').blur(function check_username(){
		var email=$('#txtUsername').val();
		checkFlag.username=false;
		if(email==''){
			$('#username\\.info').html('请输入邮箱！');
			checkFlag.username=false;
			return ;
		}
		var reg= /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if(!reg.test(email)){
			$('#username\\.info').html('邮箱格式不对！');
			checkFlag.username=false;
			return ;
		}else{
			$('#username\\.info').html('');
		}
		//alert("123");
		checkFlag.username=true;
	});
	$('#txtPassword').blur(function check_pwd(){
		var pwd=$('#txtPassword').val();
		if(pwd==''){
			$('#password\\.info').html('请输入密码！');
			 checkFlag.pwd=false;
		}else{
			var reg=/^[A-Za-z0-9]{6,20}$/;
			if(reg.test(pwd)){
				$('#password\\.info').html('');
				 checkFlag.pwd=true;
			}else{
				$('#password\\.info').html('密码为6~20个字符(由大小写字母、数字组成)');
				 checkFlag.pwd=false;
			}
		}
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
	$('#ctl00').submit(function(){
		if(checkFlag.username&&checkFlag.pwd&&checkFlag.code){
			return true;
		}else{
		  
		  if(checkFlag.username==false)
			  alert("名称有误");
		  if(checkFlag.pwd==false)
			  alert("密码有误");
		  if(checkFlag.code==false)
			  alert("验证有误");
		  return false;
		}
	});
});
