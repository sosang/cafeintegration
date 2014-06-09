<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/faq.css">
<script type="text/javascript"
	src="<%=request.getContextPath() %>/java_script/faq.js"></script>
<style type="text/css">
a.listtxt:link {
	font-size: 12px;
	color: #535353;
	text-decoration: none;
} /*lisk link*/
a.listtxt:visited {
	color: #535353;
	text-decoration: none;
}

a.listtxt:hover {
	color: #4A86B6;
	text-decoration: underline;
}

.listtxt {
	color: #535353;
} /*text default*/
</style>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>FAQ 테스트</h2>
		<c:if test="${faqList ne null }">
			<div class="faq">
				<div class="faqHeader">
					<h1>FAQ</h1>
					<button type="button" class="showAll">답변 모두 여닫기</button>
				</div>
				<c:forEach items="${faqList}" var="article">

					<ul class="faqBody">
						<li class="article" id="a1">
							<p class="q">
								<a href="#a1"><c:out value="${article.titleFaq}" /></a>
							</p>
							<p class="a">
								<c:out value="${article.contentFaq}" />
							</p>
						</li>
					</ul>

				</c:forEach>

				<br />
				<button type="button" onclick="$('link').attr('href','')">CSS(X)</button>
				<button type="button"
					onclick="$('link').attr('href','boardFaq.html?pageNo=1')">CSS(O)</button>
				<script type="text/javascript"
					src="http://code.jquery.com/jquery-latest.js"></script>
				<script type="text/javascript"
					src="<%=request.getContextPath() %>/java_script/faq.js"></script>
				<!-- 하단부 페이지 이동버튼 만들기 -->
				<c:if test="${listCount>0 }">
					<c:set var="maxPage" value="${requestScope.maxPage }" />
					<c:set var="startPage" value="${requestScope.startPage }" />
					<c:set var="endPage" value="${requestScope.endPage }" />
					<c:if test="${startPage>10 }">
						<a href="boardFaq.html?pageNo=${startPage -10 }">이전</a>
					</c:if>
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<a href="boardFaq.html?pageNo=${i }">[${i }]</a>
					</c:forEach>
					<c:if test="${endPage<maxPage }">
						<a href="boardFaq.html?pageNo=${startPage+10 }">다음</a>
					</c:if>
				</c:if>
				<!-- 하단부 페이지 이동버튼 만들기 -->
			</div>
		</c:if>
		<hr>
		<c:if test="${empty faqList}">
			<h1>등록된 게시물이 없습니다.</h1>
		</c:if>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</body>
</html>