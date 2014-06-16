<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@ taglib uri="http://cksource.com/ckfinder" prefix="ckfinder" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 수정</title>
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
		<h2>FAQ 수정</h2>
		<form name="form" method="post" action="boardFaqUpdate.html">
			<table>
				<tr height="40px">
					<td>제  목</td>
					<td><input type="text" name="titleFaq" size="115" value=${boardFaq.titleFaq }></td>
				</tr>
				<tr height="40px">
					<td>내  용</td>
					<td><textarea id="contents" name="contentFaq"  rows="10" cols="100">${boardFaq.contentFaq }</textarea></td>
				</tr>
			</table>
			<br>
			<input type="hidden" value="${pageNo }" name="pageNo">
			<input type="hidden" value="${boardFaq.bdNoFaq }" name="bdNoFaq">
			<input type="submit" value="등록" /><input type="reset" value="리셋" /><br>
			<br>
		</form>
		<hr>
		<a href="boardFaqList.html?pageNo=1" class="btn" id="coco">목록으로</a>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>