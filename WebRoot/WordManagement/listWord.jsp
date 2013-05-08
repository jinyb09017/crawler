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

<title>热点词列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.4.3.js"></script>
	<script type="text/javascript">
	   $(function(){
	      $.post("webinfeng/admin_listHot",{},function(data){
	         $("#myRightBar").html(data);        
	      });
	   });
	</script>
</head>

<body>
	<c:import url="/common/header.jsp"></c:import>
	<div class="content">
		<div class="content_resize">
			<div class="mainbar">
				<div class="article">
					<div>
						<table>
							<thead>
								<tr>
									<td>词数</td>
									<td>热点词</td>
									<td>热点词权重</td>
									<td>引用新闻数目</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${wordList}" var="word" begin="0"
									varStatus="s">
									<tr>
										<td>${s.index+1}</td>

										<td>${word.word}</td>
										<td>${word.weight}</td>
										<td><a
											href="webinfeng/news_listByWordId?word.id=${word.id }">${word.newsNum}</a>
										</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					</div>
					共${page.totalPage }页 第${page.currentPage }页
					<c:choose>

						<c:when test="${page.hasPrePage }">
							<a
								href="webinfeng/word_list?page.currentPage=${page.currentPage-1 }">上一页</a>
						</c:when>

						<c:otherwise>  上一页
   </c:otherwise>

					</c:choose>
					<c:choose>

						<c:when test="${page.hasNextPage }">
							<a
								href="webinfeng/word_list?page.currentPage=${page.currentPage +1}">下一页</a>
						</c:when>

						<c:otherwise> 下一页
   </c:otherwise>

					</c:choose>
				</div>
				<div class="article">
					<h2>
						<span><cufon class="cufon cufon-canvas" alt="3"
								style="width: 14px; height: 24px;">
							<canvas width="59" height="32"
								style="width: 59px; height: 32px; top: -5px; left: -16px;"></canvas>
							<cufontext>3</cufontext></cufon>
						</span>
						<cufon class="cufon cufon-canvas" alt=" "
							style="width: 7px; height: 24px;">
						<canvas width="59" height="32"
							style="width: 59px; height: 32px; top: -5px; left: -16px;"></canvas>
						<cufontext> </cufontext></cufon>
						<cufon class="cufon cufon-canvas" alt="Responses"
							style="width: 121px; height: 24px;">
						<canvas width="167" height="32"
							style="width: 167px; height: 32px; top: -5px; left: -16px;"></canvas>
						<cufontext>Responses</cufontext></cufon>
					</h2>
					<div class="clr"></div>
					<div class="comment">
						<a href="#"><img src="images/userpic.gif" width="40"
							height="40" alt="user" class="userpic">
						</a>
						<p>
							<a href="#">admin</a> Says:<br>April 20th, 2009 at 2:17 pm
						</p>
						<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
							Donec libero. Suspendisse bibendum.</p>
					</div>
					<div class="comment">
						<a href="#"><img src="images/userpic.gif" width="40"
							height="40" alt="user" class="userpic">
						</a>
						<p>
							<a href="#">Owner</a> Says:<br>April 20th, 2009 at 3:21 pm
						</p>
						<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
							Donec libero. Suspendisse bibendum. Cras id urna. Morbi
							tincidunt, orci ac convallis aliquam, lectus turpis varius lorem,
							eu posuere nunc justo tempus leo.</p>
					</div>
					<div class="comment">
						<a href="#"><img src="images/userpic.gif" width="40"
							height="40" alt="user" class="userpic">
						</a>
						<p>
							<a href="#">admin</a> Says:<br>April 20th, 2009 at 2:17 pm
						</p>
						<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit.
							Donec libero. Suspendisse bibendum.</p>
					</div>
				</div>
				<div class="article">
					<h2>
						<span><cufon class="cufon cufon-canvas" alt="Leave "
								style="width: 73px; height: 24px;">
							<canvas width="124" height="32"
								style="width: 124px; height: 32px; top: -5px; left: -16px;"></canvas>
							<cufontext>Leave </cufontext></cufon>
							<cufon class="cufon cufon-canvas" alt="a"
								style="width: 14px; height: 24px;">
							<canvas width="59" height="32"
								style="width: 59px; height: 32px; top: -5px; left: -16px;"></canvas>
							<cufontext>a</cufontext></cufon>
						</span>
						<cufon class="cufon cufon-canvas" alt=" "
							style="width: 7px; height: 24px;">
						<canvas width="59" height="32"
							style="width: 59px; height: 32px; top: -5px; left: -16px;"></canvas>
						<cufontext> </cufontext></cufon>
						<cufon class="cufon cufon-canvas" alt="Reply"
							style="width: 62px; height: 24px;">
						<canvas width="108" height="32"
							style="width: 108px; height: 32px; top: -5px; left: -16px;"></canvas>
						<cufontext>Reply</cufontext></cufon>
					</h2>
					<div class="clr"></div>
					<form action="#" method="post" id="leavereply">
						<ol>
							<li><label for="name">Name (required)</label> <input
								id="name" name="name" class="text"></li>
							<li><label for="email">Email Address (required)</label> <input
								id="email" name="email" class="text"></li>
							<li><label for="website">Website</label> <input id="website"
								name="website" class="text"></li>
							<li><label for="message">Your Message</label> <textarea
									id="message" name="message" rows="8" cols="50"></textarea></li>
							<li><input type="image" name="imageField" id="imageField"
								src="images/submit.gif" class="send">
								<div class="clr"></div></li>
						</ol>
					</form>

				</div>
			</div>
			<div id="myRightBar"></div>
			<div class="clr"></div>



		</div>
	</div>


	<c:import url="/common/footer.jsp"></c:import>

</body>
</html>



