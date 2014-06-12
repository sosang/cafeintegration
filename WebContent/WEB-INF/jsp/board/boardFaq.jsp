<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/faq.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<c:if test="${faqList ne null }">
	<div align="center" class="body">
	<h1>FAQ</h1>

		<dl>
				<c:forEach items="${faqList}" var="articles" varStatus="status">
						<dt><a href="#bda${status.count }"><c:out value="${articles.titleFaq}" /></a></dt>
						<dd><c:out value="${articles.contentFaq}" /></dd>
					</li>
				</c:forEach>
		</dl>

<br />
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
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/java_script/faq.js"></script>
<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>