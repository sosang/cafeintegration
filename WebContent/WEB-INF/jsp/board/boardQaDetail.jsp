<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 읽기</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>자유게시판 읽기</h2>
		<table class="tableType">
			<tr>
			
				<th height="40px"  width="10%">제목</th>
				<td width="60%">${boardQa.titleQa}</td>
				<th align="right" width="10%">조회수</th>
				<td width="20%">${boardQa.countQa }</td>
			</tr>
			<tr>
				<th height="40px">글쓴이</th>
				<td>${boardQa.userEmail}</td>
				<th>작성일</th>
				<td>${boardQa.dateQa }</td>
			</tr>
	
			<tr style="border-top: 1px solid #F5CB43; ">
				<th height="40px" width="80">내용</th>
				<td width="30%" colspan="3"><pre style="background-color: #fff; border: 0px">${boardQa.contentQa}</pre></td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: right;">글쓴이IP : ${boardQa.userIp }</td>
			</tr>
		</table><p>
		<c:if test="${writer == USER_KEY.userEmail }">
			<form name="form" method="post" action="boardQaDeleteBefore.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}">
				<input type="hidden" value="${boardQa }" name="boardQa">
				<input type="hidden" value="${pageNo }" name="pageNo">
				<input type="hidden" value="${boardQa.bdNoQa }" name="bdNoQa">
				<input type="hidden" value="${boardQa.refQa }" name="refQa">
				<input type="hidden" value="${boardQa.reStep }" name="reStepQa">
				<input type="hidden" value="${boardQa.reLevel }" name="reLevelQa">
				<input class="btn" type="button" onclick="location.href='boardQaEdit.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}'" value="수정하긔">
				<input class="btn" type="button" onclick="location.href='boardQaReplyBefore.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}'" value="답글달긔">
				<input class="btn" type="button" onclick="delConfirm()" value="삭제허기"> 
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