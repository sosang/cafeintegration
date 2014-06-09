<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 읽기</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>자유게시판 읽기</h2>
		<table>
			<tr>
				<td height="40px" width="80">제목</td>
				<td width="600">${boardQa.titleQa}</td>
				<td align="right">${boardQa.dateQa }</td>
			</tr>
			<tr>
				<td height="40px" width="80">글쓴이</td>
				<td>${boardQa.userEmail}</td>
				<td align="right">조회수 : ${boardQa.countQa }</td>
			</tr>
			<tr>
				<td height="40px" width="80">내용</td>
				<td width="800" colspan="2">${boardQa.contentQa}</td>
			</tr>
			<tr>
				<td colspan="3" align="right">글쓴이IP : ${boardQa.userIp }</td>
			</tr>
		</table>
		<c:if test="${writer == USER_KEY.userEmail }">
			<input type="hidden" value="${boardQa }" name="boardQa">
			<input type="hidden" value="${pageNo }" name="pageNo">
			<input type="hidden" value="${boardQa.bdNoQa }" name="bdNoQa">
			<input type="hidden" value="${boardQa.refQa }" name="refQa">
			<input type="hidden" value="${boardQa.reStep }" name="reStepQa">
			<input type="hidden" value="${boardQa.reLevel }" name="reLevelQa">
			<input type="button"
				onclick="location.href='boardQaEdit.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}'"
				value="수정하긔">
			<input type="button"
				onclick="location.href='boardQaReplyBefore.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}'"
				value="답글달긔">
			<form name="form" method="post"
				action="boardQaDeleteBefore.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}">
				<input type="button" onclick="delConfirm()" value="삭제허기">
			</form>
			<%-- <input type="button" onclick="location.href='boardQaDeleteBefore.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}'" value="삭제하긔"> --%>
		</c:if>
		<%-- 여기부터 댓글관련 --%>
		<hr>
		<%@ include file="/WEB-INF/jsp/board/boardQaDetailComments.jsp"%>
		<hr>

		<%-- 여기까지 댓글관련 --%>
		<a href="boardQa.html?pageNo=${pageNo}">목록으로</a>
	</div>
	<%-- 삭제확인폼 --%>
	<script type="text/javascript">
function delConfirm(){
	if (confirm("정말 삭제하시겠습니까??") == true){    //확인
		    document.form.submit();
	}else{   //취소
		    return;
	}
}
function really(bdNoQaComments){
	if (confirm("댓글을 삭제하시겠습니까?") == true){    //확인
		location.href='boardQaDelCom.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}&bdNoQaComments='+bdNoQaComments;
	}else{   //취소
	    return;
	}
}
<%--삭제 확인폼 --%>
</script>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>