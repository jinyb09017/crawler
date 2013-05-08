package com.hundsun.jinyb.action;

public class ImageValidateAction extends BaseAction{
private String code;
private boolean ok=false;
//json请求格式：{code:'sdf',ok:true}
public String getCode() {
	return code;
}
public String execute(){
	String scode=(String) session.get("code");
	System.out.println("seesion code的值："+scode);
	System.out.println("输入的code的值:"+code);
	if(code.equals(scode)){
		ok=true;
	}else{
		ok=false;
	}
	return "success";
}
public void setCode(String code) {
	this.code = code;
}
public boolean isOk() {
	return ok;
}
public void setOk(boolean ok) {
	this.ok = ok;
}
}
