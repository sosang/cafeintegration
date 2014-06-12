<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기게시판 읽기</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>후기게시판 읽기</h2>
		<table class="tableType">
		
			<tr>
				<th height="40px" width="10%">제목</th>
				<td width="60%">${boardReviews.titleRev}</td>
				<th height="40px" width="10%">작성일</th>
				<td align="right" colspan="3">${boardReviews.dateRev }</td>
			</tr>
			
			<tr>
				<th height="40px" width="80">글쓴이</th>
				<td>${boardReviews.userEmail}</td>
				<th>조회/추천수</th>
				<td width="20%" align="right" colspan="3">조회수 : ${boardReviews.countRev } / 추천수 : ${boardReviews.recommendRev }</td>		
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr style="border-top: 1px solid #d2d2d2; ">
				<th height="40px" width="80">내용</th>
				<td width="800" colspan="5"><img src="${SFPT.filePath}"><br>
				<pre style="background-color: #fff; border: 0px">${boardReviews.contentRev}</pre></td>
			</tr>
			
			<tr>
				<td colspan="6" align="center" style="text-align: right;">글쓴이IP : ${boardReviews.userIp }</td>
			</tr>
			
		</table>
		
		
		<c:if test="${writer == USER_KEY.userEmail }">
			<input type="hidden" value="${boardReviews }" name="boardQa">
			<input type="hidden" value="${pageNo }" name="pageNo">
			<input type="hidden" value="${boardReviews.bdNoRev }" name="bdNoRev">
			<input type="hidden" value="${boardReviews.refRev }" name="refRev">
			<input type="hidden" value="${boardReviews.reStep }" name="reStepRev">
			<input type="hidden" value="${boardReviews.reLevel }" name="reLevelRev">
			
			<input  class="btn " type="button" onclick="location.href='boardReviewsEdit.html?pageNo=${pageNo }&bdNoRev=${boardReviews.bdNoRev}'" value="수정하긔">
			<input class="btn " type="button" onclick="location.href='boardReviewsReplyBefore.html?pageNo=${pageNo }&bdNoRev=${boardReviews.bdNoRev}'" value="답글달긔">
			
			<form name="form" method="post" action="boardReviewsDeleteBefore.html?pageNo=${pageNo }&bdNoRev=${boardReviews.bdNoRev}">
				<input class="btn " type="button" onclick="delConfirm()" value="삭제허기"> 
			</form>
			<%-- <input type="button" onclick="location.href='boardQaDeleteBefore.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}'" value="삭제하긔"> --%>
		</c:if>
		
		<c:if test="${!empty USER_KEY}">
			<input class="btn " type="button" onclick="location.href='boardReviewsRecommend.html?pageNo=${pageNo}&bdNoRev=${boardReviews.bdNoRev}'" value="추천하긔">
		</c:if>
		
<%-- 여기부터 댓글관련 --%>

<hr style="visibility: hidden;">
<%@ include file="/WEB-INF/jsp/board/boardReviewsDetailComments.jsp"%>
<hr style="visibility: hidden;">

<%-- 여기까지 댓글관련 --%>
		<a href="boardReviews.html?pageNo=${pageNo}">목록으로</a>
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

function reallyRev(bdNoRevComments){
	if (confirm("댓글을 삭제하시겠습니까?") == true){    //확인
		location.href='boardRevDelCom.html?pageNo=${pageNo }&bdNoRev=${boardReviews.bdNoRev}&bdNoRevComments='+bdNoRevComments;
	}else{   //취소
	    return;
	}
}
<%--삭제 확인폼 --%>
</script>
<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>