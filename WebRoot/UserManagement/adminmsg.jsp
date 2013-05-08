<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<title>管理员用户管理</title>
  </head>
  
  <body>
    <div>
      <table align="center" border="1">
         <thead>
         <tr>
           <td>编号</td>
           <td>管理员名称</td>
           <td>管理员级别</td>
           <td>管理员状态</td>
           <td>操作</td>
         </tr>
         </thead>
         <tbody>
        
        <c:choose>
        <c:when test="${empty listAdmin}">
        <tr><td>库中没有数据！</td></tr>
        </c:when>
        <c:otherwise>
         <c:forEach items="${listAdmin}" var="admin" begin="0"  varStatus="s">
         <tr>
         <td>${s.index+1}</td>
         <td>${admin.name }</td>
         <td><c:choose><c:when test="${admin.level==0 }">超级管理员</c:when><c:otherwise>普通管理员</c:otherwise></c:choose></td>
         <td> <c:choose><c:when test="${admin.state==0 }">不正常</c:when><c:otherwise>正常</c:otherwise></c:choose></td>
         <td><a href="usermsg_delAdmin.action?id=${admin.id }">
         <input type="button" value="删除" /></a>  
         <a href="usermsg_updateJSPAdmin.action?admin.id=${admin.id }&admin.name=${admin.name}&admin.level=${admin.level}&admin.state=${admin.state}"><input type="button" value="修改"/></a></td>
         </tr>
           </c:forEach>
           <tr><td>共${total }页</td>
           <c:choose>
             <c:when test="${ page}>0"> <td><a href="usermsg_findAllAdmin.action?page=${page-1 }"><input type="button" value="上一页"/></a></td></c:when>
             <c:otherwise><td>到最前面啦！</td></c:otherwise>
           </c:choose>
          
           
            <c:choose>
             <c:when test="${page}>=${total } "><td>到尽头啦！</td></c:when>
             <c:otherwise><td><a href="usermsg_findAllAdmin.action?page=${page+1 }"><input type="button" value="下一页"/>
           </a></td></c:otherwise>
           </c:choose>
           
           </tr>
        </c:otherwise>
        </c:choose>
        
        
       
         </tbody>
      </table>
     <div>
     <a href="usermsg_addJSPAdmin.action"><input type="button" value="增加" align="middle"/></a>
     </div>
    </div>
  </body>
</html>
