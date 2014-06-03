<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<!-- Bootstrap CDN css -->
<link rel="stylesheet"
	href="http://netdna.bootstrapcdn.com/bootstrap/3.0.0-wip/css/bootstrap.min.css">

<title></title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>로그인 완료 화면</h2>
		환영해요,${USER_KEY.userEmail }님! 환영합니다.&nbsp;&nbsp;&nbsp;
		<a href="../mypage">MyPage</a>
		<a href="../login/logout.html">Logout</a>
<!-- 		<input type="submit" value="로그아웃"> -->
	
	</div>
	

	
	<footer>
	${USER_KEY.userEmail } = ${USER_KEY.userEmail }
<%@ include file="/WEB-INF/jsp/js_footer.jsp" %>
	

	</footer>
</body>
</html>