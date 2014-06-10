<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 게시물 수정</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<%// CLEditor 적용 %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/cleditor/jquery.cleditor.css" >
<script type="text/javascript" src="<%=request.getContextPath() %>/cleditor/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/cleditor/jquery.cleditor.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/cleditor/jquerytable.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#content").cleditor({
		width:960,//에디타 넓이
		height:400,//에디타 높이
	});
});
function resets(){
	var editor = $("#content").cleditor()[0];
	var f = document.form;
	f.titleQa.value="";
	f.contentQa.value="";
	editor.focus().clear();
}
window.onload = function(){
	document.form.titleQa.focus();
};
</script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>자유게시판 수정</h2>
		<form name="form" method="post" action="boardQaUpdateForm.html?pageNo=${pageNo}">
			<table>
				<tr height="40px" style="width: 10%">
					<td>제  목</td>
					<td><input type="text" name="titleQa" size="80%" value="${boardQa.titleQa}"></td>
				</tr>
				<tr height="40px" tyle="width: 60%">
					<td>내  용</td>
					<td><textarea id="content" name="contentQa" >${boardQa.contentQa}</textarea></td>
				</tr>
			</table>
			<br>
			<input type="hidden" value="${boardQa.bdNoQa }" name="bdNoQa">
			<input type="hidden" value="${USER_KEY.userEmail }" name="userEmail">
			<input type="hidden" value="${USER_KEY.userAlias }" name="userAlias">
			<input type="submit" value="수정하기" /><input type="reset" value="리셋" /><br>
			<br>
		</form>
		<hr>
		<a href="boardQa.html?pageNo=${pageNo}">목록으로</a>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>