<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,  initial-scale=1">
<title>공지사항 읽기</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>공지사항 읽기</h2>
		<table  class="tableType table-striped"  >
			<tr>
				<td >제목</td>
				<td >${boardNotice.titleNtc}</td>
				<td align="right">${boardNotice.dateNtc }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td  colspan="2">${boardNotice.contentNtc}</td>
			</tr>
		</table>
		<div class="tableType"><hr></div>
 		<a href="boardNotice.html?pageNo=${pageNo}">목록으로</a>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>