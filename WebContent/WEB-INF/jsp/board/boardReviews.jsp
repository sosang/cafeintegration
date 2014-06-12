<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기게시판</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
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
		<h2>후기게시판</h2>
		<c:if test="${!empty USER_KEY}">
			<a href="boardReviewsWriteBefore.html">글쓰기</a>
		</c:if>
		<c:if test="${empty USER_KEY}">
			<b>글을 쓰시려면 --> <a href="../login/login.html">로그인</a></b>
		</c:if>
<c:if test="${!empty articleListRev}">
		<table>
			<tr>
				<th align="center" width="80">번호</th>
				<th align="center" width="620">제 목</th>
				<th align="center" width="100">글쓴이</th>
				<th align="center" width="80">글쓴날</th>
				<th align="center" width="80">조회/추천</th>
			</tr>

			<c:forEach items="${articleListRev}" var="reviews">
				<tr class="record">
					<td align="left" class="listtxt">
						<c:out value="${reviews.bdNoRev}" />
					</td>
					<td align="left" class="listtxt">
					<c:choose>
						<c:when test="${reviews.reStep == 0}">
							<a	href="<c:url value="boardReviewsDetail.html">
											<c:param name="pageNo" value="${pageNo}"/>
											<c:param name="bdNoRev" value="${reviews.bdNoRev}"/>
										</c:url>">
								<c:out value="${reviews.titleRev}" />
							</a>
						</c:when>
						<c:otherwise>
							<c:forEach begin="0" end="${reviews.reStep}" var="i">
								&nbsp;&nbsp;
							</c:forEach>
							<a	href="<c:url value="boardReviewsDetail.html">
											<c:param name="pageNo" value="${pageNo}"/>
											<c:param name="bdNoRev" value="${reviews.bdNoRev}"/>
										</c:url>">
								<c:out value="▷답글 : ${reviews.titleRev}" />
							</a>
						</c:otherwise>
					</c:choose>
					</td>
					<td align="center" class="listtxt">
						<c:out value="${reviews.userAlias}" />
					</td>
					<td align="center" class="listtxt">
						<font size="0.1"><c:out value="${reviews.dateRev}" /></font>
					</td>
					<td align="center" class="listtxt">
						<c:out value="${reviews.countRev}" />/
						<c:out value="${reviews.recommendRev}" />
					</td>
				</tr>
			</c:forEach>
		</table>
		<!-- 하단부 페이지 이동버튼 만들기 -->
		<c:if test="${listCountRev>0 }">
			<c:set var="maxPageRev" value="${requestScope.maxPageRev }" />
			<c:set var="startPageRev" value="${requestScope.startPageRev }" />
			<c:set var="endPageRev" value="${requestScope.endPageRev }" />
			<c:if test="${startPageRev>10 }">
				<a href="boardReviews.html?pageNo=${startPageRev -10 }">이전</a>
			</c:if>
			<c:forEach var="i" begin="${startPageRev }" end="${endPageRev }">
				<a href="boardReviews.html?pageNo=${i }">[${i }]</a>
			</c:forEach>
			<c:if test="${endPageRev<maxPageRev }">
				<a href="boardReviews.html?pageNo=${startPageRev+10 }">다음</a>
			</c:if>
		</c:if>
		<input type="hidden" value="${pageNo }" name="pageNo">
		<!-- 하단부 페이지 이동버튼 만들기 -->
</c:if>
<c:if test="${!empty USER_KEY}">
	<a href="boardReviewsWriteBefore.html">글쓰기</a>
</c:if>
<c:if test="${empty USER_KEY}">
	<br><b>글을 쓰시려면 --> <a href="../login/login.html">로그인</a></b>
</c:if>
		<hr>
<c:if test="${empty articleListRev}">
<h1>등록된 게시물이 없습니다.</h1>
</c:if>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>