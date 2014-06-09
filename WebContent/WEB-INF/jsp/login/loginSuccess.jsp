<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인완료</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<%
	request.getSession().getAttributeNames();
%>
	<hr>

	<div align="center" class="body">
		<h2>로그인 완료 화면</h2>
		<b>환영해요 ${USER_KEY.userEmail }님! 환영합니다.</b><br> <a
			href="../mypage">마이페이지</a><br> <a href="../login/logout.html">로그아웃</a>
		<hr>


	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>