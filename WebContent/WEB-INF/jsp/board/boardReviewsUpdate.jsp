<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckfinder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기게시판 게시물 수정</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<script type="text/javascript" src="<c:url value="/ckeditor/ckeditor.js" />"></script>
<script type="text/javascript">
window.onload=function(){
    CKEDITOR.replace('contents',{enterMode:'2',shiftEnterMode:'3',language:'ko',
    	toolbar:[['Bold','Italic','Underline','Strike','-','Subscript','Superscript','-','TextColor','BGColor','-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','Link','Unlink','-','Find','Replace','SelectAll','RemoveFormat','-','Image','Flash','Table','SpecialChar'],'/',['Source','-','ShowBlocks','-','Font','FontSize','Undo','Redo','-','About']]
    
    });
};
</script>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>후기게시판 수정</h2>
		<form name="form" method="post" action="boardReviewsUpdateForm.html?pageNo=${pageNo}">
			<table class="tableType table-striped">
				<tr height="40px">
					<td>제  목</td>
					<td><input type="text" name="titleRev" size="115" value="${boardRev.titleRev}"></td>
				</tr>
				<tr height="40px">
					<td>내  용</td>
					<td><textarea id="contents" name="contentRev"  rows="10" cols="100">${boardRev.contentRev}</textarea></td>
				</tr>
			</table>
			<br>
			<input type="hidden" value="${boardRev.bdNoRev }" name="bdNoRev">
			<input type="hidden" value="${USER_KEY.userEmail }" name="userEmail">
			<input type="hidden" value="${USER_KEY.userAlias }" name="userAlias">
			<input type="submit" value="수정하기" /><input type="reset" value="리셋" /><br>
			<br>
		</form>
		<hr>
		<a href="boardReviews.html?pageNo=${pageNo}">목록으로</a>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>