<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,  initial-scale=1">
<title>자유게시판</title>
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
table.tableType tr {
	text-align: center;
}
table.tableType tr td title{
	text-align: left;
}
table.leftType{
	text-align: left;
}


</style>




</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>자유게시판</h2>
		<c:if test="${empty USER_KEY && empty ADMIN_KEY}">
			<font color="blue">글을 쓰시려면 ☞</font> <a href="../login/login.html" class="btn btn-primary">로그인</a>
		</c:if>
		<c:if test="${!empty USER_KEY || !empty ADMIN_KEY}">
			<a style="text-align:right;" href="boardQaWriteBefore.html" class="btn btn-primary"><i class="fa fa-pencil"></i> 글쓰기</a>
		</c:if>
<c:if test="${!empty articleListQa}">
		<table border="1" class="tableType table-striped">
			<tr>
				<th align="center" width="5%">번호</th>
				<th align="center" width="60%">제 목</th>
				<th align="center" width="10%">글쓴이</th>
				<th align="center" width="15%">글쓴날</th>
				<th align="center" width="10%">조회수</th>
			</tr>

			<c:forEach items="${articleListQa}" var="article">
				<tr>
					<td align="center" class="listtxt">
						<c:out value="${article.bdNoQa}" />
					</td>
					
					
					<td align="left" class="leftType">
					<c:choose>
						<c:when test="${article.reStep == 0}">
							<a	href="<c:url value="boardQaDetail.html">
											<c:param name="pageNo" value="${pageNo}"/>
											<c:param name="bdNoQa" value="${article.bdNoQa}"/>
										</c:url>">
								<c:out value="${article.titleQa}" />
							</a>
						</c:when>
						<c:otherwise>
							<c:forEach begin="0" end="${article.reStep}" var="i">
								&nbsp;&nbsp;
							</c:forEach>
							<a	href="<c:url value="boardQaDetail.html">
											<c:param name="pageNo" value="${pageNo}"/>
											<c:param name="bdNoQa" value="${article.bdNoQa}"/>
										</c:url>">
								<c:out value="▷답글 : ${article.titleQa}" />
							</a>
						</c:otherwise>
					</c:choose>
					</td>
					
					
					<td align="center" class="listtxt">
						<c:out value="${article.userAlias}" />
					</td>
					<td align="center" class="listtxt">
						<font><c:out value="${article.dateQa}" /></font>
					</td>
					<td align="center" class="listtxt">
						<c:out value="${article.countQa}" />
					</td>
				</tr>
			</c:forEach>
		</table>
		<!-- 하단부 페이지 이동버튼 만들기 -->
		<c:if test="${listCountQa>0 }">
			<c:set var="maxPageQa" value="${requestScope.maxPageQa }" />
			<c:set var="startPageQa" value="${requestScope.startPageQa }" />
			<c:set var="endPageQa" value="${requestScope.endPageQa }" />
			<c:if test="${startPageQa>10 }">
				<a href="boardQa.html?pageNo=${startPageQa -10 }">이전</a>
			</c:if>
			<c:forEach var="i" begin="${startPageQa }" end="${endPageQa }">
				<a href="boardQa.html?pageNo=${i }">[${i }]</a>
			</c:forEach>
			<c:if test="${endPageQa<maxPageQa }">
				<a href="boardQa.html?pageNo=${startPageQa+10 }">다음</a>
			</c:if>
		</c:if>
		<input type="hidden" value="${pageNo }" name="pageNo">
		<!-- 하단부 페이지 이동버튼 만들기 -->
</c:if>


		<hr>
		<c:if test="${!empty USER_KEY || !empty ADMIN_KEY}">
			<a style="text-align:right;" href="boardQaWriteBefore.html" class="btn btn-primary"><i class="fa fa-pencil"></i> 글쓰기</a>
		</c:if>
<c:if test="${empty articleListQa}">
<h1>등록된 게시물이 없습니다.</h1>
</c:if>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>
