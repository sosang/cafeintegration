<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>
	<%
	response.sendRedirect("../index/index.html");
%>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>