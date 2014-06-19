<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,  initial-scale=1">
<title>FAQ</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	
	<c:if test="${faqList ne null }">
	<div class="topblock">
		<p>FAQ</p>
	</div>
		<div align="center" class="body faqWidth">
			

			<dl>
				<c:forEach items="${faqList}" var="articles" varStatus="status">
					<dt>
						<a href="#bda${status.count }"><c:out
								value="${articles.titleFaq}" /></a>
					</dt>
					<dd>
						${articles.contentFaq}
					</dd>
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
	<div class="faqWidth">
		<h1>등록된 게시물이 없습니다.</h1>
	</div>
	</c:if>
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-latest.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/java_script/faq.js"></script>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
			<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>

</html>
