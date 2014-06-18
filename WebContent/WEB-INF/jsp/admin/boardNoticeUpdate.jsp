<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckfinder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,  initial-scale=1">
<title>공지사항 수정</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<style type="text/css">
a#coco{
	background-color: buttonface;
}
</style>
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
		<h2>공지사항 수정</h2>
		<form name="form" method="post" action="boardNoticeUpdate.html">
			<table>
				<tr height="40px">
					<td>제  목</td>
					<td><input type="text" name="titleNtc" size="115" value="${boardNtc.titleNtc }"></td>
				</tr>
				<tr height="40px">
					<td>내  용</td>
					<td><textarea id="contents" name="contentNtc"  rows="10" cols="100">${boardNtc.contentNtc }</textarea></td>
				</tr>
			</table>
			<br>
			<input type="hidden" value="${pageNo }" name="pageNo">
			<input type="hidden" value="${boardNtc.bdNoNtc }" name="bdNoNtc">
			<input type="submit" value="등록" class="btn" id="coco"/><br>
			<br>
		</form>
		<hr>
		<a href="boardNoticeList.html?pageNo=1">목록으로</a>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>