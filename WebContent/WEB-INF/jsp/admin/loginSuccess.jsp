<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<c:choose>
	<c:when test="${ADMIN_KEY.adminEmail ne null }">
	${ADMIN_KEY.adminEmail }님 하이요
    </c:when>
    <c:otherwise>
		<form action="../login/loginSuccess.html" method="post">
			아뒤 : <input type="text" name="adminEmail"/><br>
			비번 : <input type="password" name="adminPasswd"/><br>
			<input type="submit" value="로긴"/>
		</form>
	</c:otherwise>
</c:choose>
</body>
</html>