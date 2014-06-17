<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckfinder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 답글</title>
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
	
		<h2>자유게시판 답글</h2>
		<form name="form" method="post" action="boardQaReplyForm.html?pageNo=${pageNo }&bdNoQa=${bdNoQa }">
			<table>
				<tr height="40px">
					<td>제  목</td>
					<td><input type="text" name="titleQa" size="115" value="${boardQaTitle }"></td>
				</tr>
				<tr height="40px">
					<td>내  용</td>
					<td><textarea id="contents" name="contentQa" rows="10" cols="100">${boardQaContent}</textarea></td>
				</tr>
			</table>
			<br>
			
			<input type="hidden" value="${boardQa.bdNoQa }" name="bdNoQa">
			<input type="hidden" value="${pageNo }" name="pageNo">
			<input type="hidden" value="${bdNoQa }" name="bdNoQa">
			<input type="hidden" value="${USER_KEY.userEmail }" name="userEmail">
			<input type="hidden" value="${USER_KEY.userAlias }" name="userAlias">
			<input type="submit" value="답글작성완료" /><input type="reset" value="리셋" /><br>
			<br>
		</form>
		<hr>
		<a href="boardQa.html?pageNo=${pageNo}">목록으로</a>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>