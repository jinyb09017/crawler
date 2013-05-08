var checkFlag={"valcode":false};
$(function (){
	$('#validatecode').blur(function checkvalidatecode(){
		var emailCode=$('#validatecode').val();
		if(emailCode==''){
			$('#validatecodeEmail').html('不能为空！');
			checkFlag.valcode=false;
		}else{
		reg=/^.{1,64}(|){1}[0-9]+$/;
		if(reg.test(emailCode)){
			$('#validatecodeEmail').html('');
			checkFlag.valcode=true;
		}else{
			$('#validatecodeEmail').html('格式不对！');
			checkFlag.valcode=false;
		}
		}
	});
	$('#emailcheck').submit(function (){
		if(checkFlag.valcode){
			return true;
		}else{
			return false;
		}
	});
});