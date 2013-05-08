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
<title>管理员用户管理</title>
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
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">数目</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">管理员名称</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">管理员级别</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">管理员状态</span></div></td>
        <td width="20%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作</span></div></td>


      </tr>
      <c:choose>
        <c:when test="${empty listAdmin}">
        <tr><td>库中没有数据！</td></tr>
        </c:when>
        <c:otherwise>
         <c:forEach items="${listAdmin}" var="admin" begin="0"  varStatus="s">
         <tr>
         <td height="20" bgcolor="#FFFFFF"><div align="center">
          <input style="width:100%;" type="checkbox" name="checkbox2" id="checkbox2" />
        </div></td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19">${s.index+1}</td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19">${admin.name }</td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19"><c:choose><c:when test="${admin.level==0 }">超级管理员</c:when><c:otherwise>普通管理员</c:otherwise></c:choose></td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19"> <c:choose><c:when test="${admin.state==0 }">不正常</c:when><c:otherwise>正常</c:otherwise></c:choose></td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19"><a href="users/usermsg_delAdmin.action?id=${admin.id }">
         <input type="button" value="删除" /></a>  
         <a href="users/usermsg_updateJSPAdmin.action?admin.id=${admin.id }&admin.name=${admin.name}&admin.level=${admin.level}&admin.state=${admin.state}"><input type="button" value="修改"/></a></td>
         </tr>
           </c:forEach>
           <tr><td height="20" bgcolor="#FFFFFF" class="STYLE19">共${total }页</td>
           <c:choose>
             <c:when test="${ page}>0"> <td><a href="users/usermsg_findAllAdmin.action?page=${page-1 }"><input type="button" value="上一页"/></a></td></c:when>
             <c:otherwise><td height="20" bgcolor="#FFFFFF" class="STYLE19">到最前面啦！</td></c:otherwise>
           </c:choose>
          
           
            <c:choose>
             <c:when test="${page}>=${total } "><td height="20" bgcolor="#FFFFFF" class="STYLE19">到尽头啦！</td></c:when>
             <c:otherwise><td height="20" bgcolor="#FFFFFF" class="STYLE19"><a href="users/usermsg_findAllAdmin.action?page=${page+1 }"><input type="button" value="下一页"/>
           </a></td></c:otherwise>
           </c:choose>
           
           </tr>
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
     <a href="users/usermsg_addJSPAdmin.action"><input type="button" value="增加" align="middle"/></a>
     </div>
    </div>
  </body>
</html>
