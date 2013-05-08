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
<title>会员用户管理</title>
<link rel="stylesheet" type="text/css" href="console/css/style.css"/>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
	background: white;
	
	
}

-->
</style>
</head>
  
  <body>
    <div>
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="console/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 管理人员基本信息列表</span></td>
              </tr>
            </table></td>
            <td><div align="right"><span class="STYLE1">
              <input type="checkbox" name="checkbox11" id="checkbox11" />
              全选      &nbsp;&nbsp;<img src="console/images/add.gif" width="10" height="10" /> 添加   &nbsp; <img src="console/images/del.gif" width="10" height="10" /> 删除    &nbsp;&nbsp;<img src="console/images/edit.gif" width="10" height="10" /> 编辑   &nbsp;</span><span class="STYLE1"> &nbsp;</span></div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce">
      <tr>
        <td width="5%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">
          <input style="width:100%;" type="checkbox" name="checkbox" id="checkbox" />
        </div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">编号</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户昵称</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户邮箱</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">是否激活</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户类型</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户级别</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户状态</span></div></td>
        <td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>


    <c:choose>
        <c:when test="${empty listUser}">
        <tr><td>库中没有数据！</td></tr>
        </c:when>
        <c:otherwise>
         <c:forEach items="${listUser}" var="user" begin="0"  varStatus="s">
         <tr>
         <td width="5%" height="20" bgcolor="d3eaef" class="STYLE10"><div align="center">
          <input style="width:100%;" type="checkbox" name="checkbox" id="checkbox" />
        </div></td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19">${s.index+1}</td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19">${user.name }</td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19">${user.email }</td>
          <td height="20" bgcolor="#FFFFFF" class="STYLE19"><c:choose><c:when test="${user.active==0 }">未激活</c:when><c:otherwise>已激活</c:otherwise></c:choose></td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19"><c:choose><c:when test="${user.type==1 }">网站用户</c:when><c:otherwise>客户端用户</c:otherwise></c:choose></td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19"><c:choose><c:when test="${user.level==0 }">高级会员</c:when><c:otherwise>普通会员</c:otherwise></c:choose></td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19"> <c:choose><c:when test="${user.state==0 }">不正常</c:when><c:otherwise>正常</c:otherwise></c:choose></td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19"><a href="users/usermsg_delUser.action?id=${user.id }">
         <input type="button" value="删除" /></a>  
         <a href="users/usermsg_updateJSPUser.action?user.id=${user.id }&user.name=${user.name }&user.email=${user.email }&user.active=${user.active}&user.type=${user.type}&user.level=${user.level}&user.state=${user.state}"><input type="button" value="修改"/></a></td>
         </tr>
           </c:forEach>
        </c:otherwise>
        </c:choose>
    </table></td>
  </tr>
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> 243</strong> 条记录，当前第<strong> 1</strong> 页，共 <strong>10</strong> 页</span></div></td>
        <td width="67%"><table width="312" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
            <td width="49"><div align="center"><img src="console/images/main_54.gif" width="40" height="15" /></div></td>
            <td width="49"><div align="center"><img src="console/images/main_56.gif" width="45" height="15" /></div></td>
            <td width="49"><div align="center"><img src="console/images/main_58.gif" width="45" height="15" /></div></td>
            <td width="49"><div align="center"><img src="console/images/main_60.gif" width="40" height="15" /></div></td>
            <td width="37" class="STYLE22"><div align="center">转到</div></td>
            <td width="22"><div align="center">
              <input type="text" name="textfield" id="textfield"  style="width:20px; height:12px; font-size:12px; border:solid 1px #7aaebd;"/>
            </div></td>
            <td width="22" class="STYLE22"><div align="center">页</div></td>
            <td width="35"><img src="console/images/main_62.gif" width="26" height="15" /></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
      
     <div>
     </div>
    </div>
  </body>
</html>
