<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입자 현황</title>
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
	<div align="center">
		<h2>회원목록 테스트</h2>
		<h4>이달의 가입자는 총 ${thisMonth} 명 입니다.</h4>
		<c:if test="${memberList ne null }">
			<table border="1">
				<tr>
					<td align="center">회원 아이디</td>
					<td align="center">회원 별명</td>
					<td align="center">화원 연락처</td>
					<td align="center">우편번호</td>
					<td align="center">주&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;소</td>
					<td align="center">등급</td>
					<td align="center">포인트</td>
					<td align="center">회원 가입일</td>
				</tr>
				<c:forEach items="${memberList}" var="member">
					<tr class="record">
						<td align="center" class="listtxt" width="160"><c:out
								value="${member.userEmail}" /></td>
						<td align="center" class="listtxt" width="100"><c:out
								value="${member.userAlias}" /></td>
						<td align="center" class="listtxt" width="160"><c:out
								value="${member.userPhone1}" />-<c:out
								value="${member.userPhone2}" />-<c:out
								value="${member.userPhone3}" /></td>
						<td align="center" class="listtxt" width="100"><c:out
								value="${member.userPostcode}" /></td>
						<td align="center" class="listtxt" width="300"><c:out
								value="${member.userAddress1}" />&nbsp;<c:out
								value="${member.userAddress2}" /></td>
						<td align="center" class="listtxt" width="70"><c:out
								value="${member.userLevel}" /></td>
						<td align="center" class="listtxt" width="70"><c:out
								value="${(member.userNumOfPractice*100)+(member.userNumOfArticle*2)+(member.userNumOfReply)}" />
						</td>
						<td align="center" class="listtxt" width="160"><c:out
								value="${member.userJoinDate}" /></td>
					</tr>
				</c:forEach>
			</table>
			<!-- 하단부 페이지 이동버튼 만들기 -->
			<c:if test="${listCount>0 }">
				<c:set var="maxPage" value="${requestScope.maxPage }" />
				<c:set var="startPage" value="${requestScope.startPage }" />
				<c:set var="endPage" value="${requestScope.endPage }" />
				<c:if test="${startPage>10 }">
					<a href="member.html?pageNo=${startPage -10 }">이전</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<a href="member.html?pageNo=${i }">[${i }]</a>
				</c:forEach>
				<c:if test="${endPage<maxPage }">
					<a href="member.html?pageNo=${startPage+10 }">다음</a>
				</c:if>
			</c:if>
			<!-- 하단부 페이지 이동버튼 만들기 -->
		</c:if>
		<c:if test="${empty memberList}">
			<h1>등록된 회원이 없습니다.</h1>
		</c:if>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>