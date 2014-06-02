<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 읽기</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>공지사항 읽기</h2>
		<table>
			<tr>
				<td height="40px" width="80">제목</td>
				<td width="600">${boardNotice.titleNtc}</td>
				<td align="right">${boardNotice.dateNtc }</td>
			</tr>
			<tr>
				<td height="40px" width="80">내용</td>
				<td width="800" colspan="2">${boardNotice.contentNtc}</td>
			</tr>
		</table>
		<hr>
		<a href="boardNotice.html?pageNo=${pageNo}">목록으로</a>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>