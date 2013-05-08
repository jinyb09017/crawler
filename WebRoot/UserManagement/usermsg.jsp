<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<title>会员用户管理</title>
  </head>
  
  <body>
    <div>
      <table align="center" border="1">
         <thead>
         <tr>
           <td>编号</td>
           <td>用户昵称</td>
           <td>用户邮箱</td>
           <td>是否激活</td>
           <td>用户类型</td>
           <td>用户级别</td>
           <td>用户状态</td>
           <td>操作</td>
         </tr>
         </thead>
         <tbody>
        
        <c:choose>
        <c:when test="${empty listUser}">
        <tr><td>库中没有数据！</td></tr>
        </c:when>
        <c:otherwise>
         <c:forEach items="${listUser}" var="user" begin="0"  varStatus="s">
         <tr>
         <td>${s.index+1}</td>
         <td>${user.name }</td>
         <td>${user.email }</td>
          <td><c:choose><c:when test="${user.active==0 }">未激活</c:when><c:otherwise>已激活</c:otherwise></c:choose></td>
         <td><c:choose><c:when test="${user.type==1 }">网站用户</c:when><c:otherwise>客户端用户</c:otherwise></c:choose></td>
         <td><c:choose><c:when test="${user.level==0 }">高级会员</c:when><c:otherwise>普通会员</c:otherwise></c:choose></td>
         <td> <c:choose><c:when test="${user.state==0 }">不正常</c:when><c:otherwise>正常</c:otherwise></c:choose></td>
         <td><a href="usermsg_delUser.action?id=${user.id }">
         <input type="button" value="删除" /></a>  
         <a href="usermsg_updateJSPUser.action?user.id=${user.id }&user.name=${user.name }&user.email=${user.email }&user.active=${user.active}&user.type=${user.type}&user.level=${user.level}&user.state=${user.state}"><input type="button" value="修改"/></a></td>
         </tr>
           </c:forEach>
        </c:otherwise>
        </c:choose>
        
        
       
         </tbody>
      </table>
     <div>
     </div>
    </div>
  </body>
</html>
