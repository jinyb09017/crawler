function form_submit(){

	  
	  if($("#txtUsername").val()=="")
		  
	  {
		  alert("name error"); return;
	  }
	  
	  if($("#txtPassword").val()=="")
		  {
		  alert("password error");
		  return;
		  }
	  
		 
	  if($("#txtVerifyCode").val()=="")
		  {
		  alert("code error");
		  return ;
		  }
		 
	
	document.getElementById("ctl00").submit();
}
function form_reset(){
	document.getElementById("ctl00").reset();
}
function reloadcode(){
    var verify=document.getElementById('safecode');
    verify.setAttribute('src','code.php?'+Math.random());
}