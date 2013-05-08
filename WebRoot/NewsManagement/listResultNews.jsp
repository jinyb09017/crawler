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

<title>新闻列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
</head>

<body>
	<c:import url="/common/header.jsp"></c:import>
	<div class="content">
		<div class="content_resize">
			<div>
				<table>
					<thead>
						<tr>
							<td>数目</td>
							<td>新闻标题</td>
							<td>新闻出处</td>
							<td>来源站点</td>
							<td>发表时间</td>
							<td>新闻分类</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${newsList}" var="news" begin="0" varStatus="s">
							<tr>
								<td>${s.index+1}</td>
								<td><a
									href="webinfeng/news_detail?news.newsId=${news.newsId }">${news.newsTitle}</a>
								</td>
								<td>${news.newsResource}</td>
								<td>${news.newsSite}</td>
								<td>${news.newsPublishtime}</td>
								<td>${news.newsCatagory}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
			<div>

				共${page.totalPage }页 第${page.currentPage }页
				<c:choose>

					<c:when test="${page.hasPrePage }">
						<a
							href="webinfeng/news_listByResultId?clusterResult.id=${clusterResult.id }&page.currentPage=${page.currentPage-1 }">上一页</a>
					</c:when>

					<c:otherwise>  上一页
   </c:otherwise>

				</c:choose>
				<c:choose>

					<c:when test="${page.hasNextPage }">
						<a
							href="webinfeng/news_listByResultId?clusterResult.id=${clusterResult.id }&page.currentPage=${page.currentPage+1 }">下一页</a>
					</c:when>

					<c:otherwise> 下一页
   </c:otherwise>

				</c:choose>



			</div>
		</div>
	    
	</div>

	<c:import url="/common/footer.jsp"></c:import>
</body>
</html>
