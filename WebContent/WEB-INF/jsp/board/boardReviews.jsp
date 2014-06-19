<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,  initial-scale=1">
<title>후기게시판</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<style type="text/css">
a#coco{
	background-color: buttonface;
}
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
}
table.tableType tr td {
	text-align: center;
}
table.tableType  td title{
	text-align: left;
}

 /*text default*/
</style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<div class="faqWidth">
<div class="topblock">
		<p>후기게시판</p>
	</div>
</div>
	<div align="center" class="body">
		
		
		<c:if test="${empty USER_KEY && empty ADMIN_KEY}">
			<font color="blue">글을 쓰시려면 ☞</font> <a href="../login/login.html" class="btn btn-primary" >로그인</a><br>
		</c:if>
		<c:if test="${!empty USER_KEY || !empty ADMIN_KEY}">
			<a href="boardReviewsWriteBefore.html" class="btn btn-primary"><i class="fa fa-pencil"></i> 글쓰기</a><br>
		</c:if>
<c:if test="${!empty articleListRev}">
		<table class="tableType table-striped">
			<tr>
				<th align="center" width="5%">번호</th>
				<th align="center" width="60%">제 목</th>
				<th align="center" width="10%">글쓴이</th>
				<th align="center" width="15%">글쓴날</th>
				<th align="center" width="10%">조회/추천수</th>
			</tr>

			<c:forEach items="${articleListRev}" var="reviews">
				<tr>
					<td align="left" class="listtxt">
						<c:out value="${reviews.bdNoRev}" />
					</td>
					
					<!-- 게시판 타이틀 영역 -->
					<td align="left" class="listtxt title">
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
		<hr>
		<c:if test="${!empty USER_KEY || !empty ADMIN_KEY}">
			<a href="boardReviewsWriteBefore.html" class="btn btn-primary"><i class="fa fa-pencil"></i> 글쓰기</a>
		</c:if>
<c:if test="${empty articleListRev}">
<h1>등록된 게시물이 없습니다.</h1>
</c:if>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>
