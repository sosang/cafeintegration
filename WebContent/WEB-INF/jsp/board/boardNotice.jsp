<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width">
<title>공지사항</title>
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
		<h2>공지사항</h2>
<c:if test="${articleList ne null }">
		<table class="tableType"  border="1">
			
			<tr class="table-striped">
				<th align="center" width="15%">번호</th>
				<th align="center" width="60%">제 목</th>
				<th align="center" width="15%">글쓴날</th>
				<th align="center" width="10%">조회수</th>
			</tr>

			<c:forEach items="${articleList}" var="article">
				<tr class="record">
					<td align="center" class="listtxt">
						<c:out value="${article.bdNoNtc}" />
					</td>
					<td align="left" class="listtxt">
						<a	href="<c:url value="boardNoticeDetail.html">
										<c:param name="pageNo" value="${pageNo}"/>
										<c:param name="bdNoNtc" value="${article.bdNoNtc}"/>
									</c:url>">
							<c:out value="${article.titleNtc}" />
						</a>
					</td>
					<td align="center" class="listtxt">
						<c:out value="${article.dateNtc}" />
					</td>
					<td align="center" class="listtxt">
						<c:out value="${article.countNtc}" />
					</td>
				</tr>
			</c:forEach>
		</table>
		<!-- 하단부 페이지 이동버튼 만들기 -->
		<c:if test="${listCount>0 }">
			<c:set var="maxPage" value="${requestScope.maxPage }" />
			<c:set var="startPage" value="${requestScope.startPage }" />
			<c:set var="endPage" value="${requestScope.endPage }" />
			<c:if test="${startPage>10 }">
				<a href="boardNotice.html?pageNo=${startPage -10 }">이전</a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<a href="boardNotice.html?pageNo=${i }">[${i }]</a>
			</c:forEach>
			<c:if test="${endPage<maxPage }">
				<a href="boardNotice.html?pageNo=${startPage+10 }">다음</a>
			</c:if>
		</c:if>
		<!-- 하단부 페이지 이동버튼 만들기 -->
</c:if>
<hr>
<c:if test="${empty articleList}">
<h1>등록된 게시물이 없습니다.</h1>
</c:if>
		
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>