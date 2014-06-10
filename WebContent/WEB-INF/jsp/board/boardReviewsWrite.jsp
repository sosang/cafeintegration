<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckfinder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기게시판 쓰기</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<script type="text/javascript" src="<c:url value="/ckeditor/ckeditor.js" />"></script>
<script type="text/javascript">
window.onload=function(){
    CKEDITOR.replace('contents',{enterMode:'2',shiftEnterMode:'3'});
};
</script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>후기게시판 쓰기</h2>
		<form name="fileForm" method="post" enctype="multipart/form-data" action="../board/boardReviewsWrite.html"> 
			<table>
				<tr height="40px">
					<td>제  목</td>
					<td><input type="text" name="titleRev" size="115"></td>
				</tr>
				<tr height="40px">
					<td>내  용</td>
					<td><textarea id="contents" name="contentRev" rows="10" cols="100" ></textarea></td>
				</tr>
				<tr>
				<td>
				</td>
				<td colspan="2"><font color="red">*사진파일만 등록가능</font></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2">사진 : <input type="file" name="filePath">
					</td>
				</tr>
				<!-- <tr>
					<td></td>
					<td colspan="2">사진2 : <input type="file" name="filePath2"></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="2">사진3 : <input type="file" name="filePath3"></td>
				</tr> -->
			</table>
			<br>
 
			<input type="hidden" value="${pageNo }" name="pageNo">
			<input type="hidden" value="${USER_KEY.userEmail }" name="userEmail">
			<input type="hidden" value="${USER_KEY.userAlias }" name="userAlias">
			<input type="submit" value="등록" /><input type="reset" value="리셋" /><br>
			<br>
		</form>
		<hr>
		<a href="boardReviews.html?pageNo=1">목록으로</a>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>