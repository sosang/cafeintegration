<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 쓰기</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<script type="text/javascript" src="<c:url value="/ckeditor/ckeditor.js" />"></script>
<script type="text/javascript">
window.onload=function(){
    CKEDITOR.replace('contents',{enterMode:'2',shiftEnterMode:'3',customConfig: '/cafeintegration/ckeditor/ckwriter.js'});
};
</script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>자유게시판 쓰기</h2>
		<form name="form" method="post" action="boardQaWrite.html">
			<table>
				<tr height="40px">
					<td>제  목</td>
					<td><input type="text" name="titleQa" size="115"></td>
				</tr>
				<tr height="40px">
					<td>내  용</td>
					<td><textarea id="contents" name="contentQa"  rows="10" cols="100"></textarea></td>
				</tr>
			</table>
			<br>
			<input type="hidden" value="${pageNo }" name="pageNo">
			<input type="hidden" value="${USER_KEY.userEmail }" name="userEmail">
			<input type="hidden" value="${USER_KEY.userAlias }" name="userAlias">
			<input type="submit" value="등록" /><input type="reset" value="리셋" /><br>
			<br>
		</form>
		<hr>
		<a href="boardQa.html?pageNo=1">목록으로</a>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>