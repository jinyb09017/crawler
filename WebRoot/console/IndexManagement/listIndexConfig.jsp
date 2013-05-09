<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

<title>索引配置列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
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
                <td width="94%" valign="bottom"><span class="STYLE1"> 索引基本信息列表</span></td>
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
        <td width="5%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">编号</span></div></td>
        <td width="5%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">配置人</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">索引文章基数</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">索引目录</span></div></td>

        
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">索引开始时间</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">索引结束时间</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">是否被覆盖</span></div></td>
        
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">是否当前配置</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">设为当前配置</span></div></td>
      </tr>
     <c:forEach items="${indexConfigList}" var="indexConfig" begin="0"  varStatus="s">
      
      <tr>
        <td height="20" bgcolor="#FFFFFF"><div align="center">
          <input style="width:100%;" type="checkbox" name="checkbox2" id="checkbox2" />
        </div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">${s.index+1}</span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${indexConfig.admin.name}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${indexConfig.webpagenum}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${indexConfig.dir}</div></td>
        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">${indexConfig.startTime}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center">${indexConfig.endTime}</div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><c:if test="${indexConfig.overRide==true }">是</c:if><c:if test="${indexConfig.overRide==false }">否</c:if></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><c:if test="${indexConfig.present==true }">是</c:if><c:if test="${indexConfig.present==false }">否</c:if></div></td>
        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21"><a href="/crawler/index/indexConfig_present?indexConfig.id=${indexConfig.id }">设置为当前</a></div></td>
      </tr>
     </c:forEach> 
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
		<a href="/crawler/cluster/indexConfig_startIndex">index</a>  
		<a href="/crawler/console/IndexManagement/addIndexConfig.jsp"><input type="button" value="增加配置"></a>
	</div>
</body>
</html>
