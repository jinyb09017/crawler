<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
<c:import url="header.jsp"></c:import>
  <div class="content">
    
      <c:forEach items="${clusterResultList }" var="clusterResult" varStatus="s">
        <div id="cluster${s.index}">
            <div>${clusterResult.cTitle }</div>
            <c:forEach items="${clusterResult.news }" var="news" varStatus="m">
                       ${m.index+1 }  
                        ${news.newsTitle }<br>
            </c:forEach>
        
        </div>
    
    </c:forEach>
  
    
  </div> 
  <c:import url="footer.jsp"></c:import>
  <%=basePath %>
</body>
  
</html>
