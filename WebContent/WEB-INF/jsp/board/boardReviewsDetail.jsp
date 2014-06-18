<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>후기게시판 읽기</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<style type="text/css">
.myColor{
	background-color: #1fa67a;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>후기게시판 읽기</h2>
		<table class="tableType table-striped">
		
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
				<td width="800" colspan="5"><br>
				<pre style="background-color: #fff; border: 0px">${boardReviews.contentRev}</pre></td>
			</tr>
			
			<tr>
				<td colspan="6" align="center" style="text-align: right;">글쓴이IP : ${boardReviews.userIp }</td>
			</tr>
			<tr>
				<td colspan="3">
					<c:if test="${writer == USER_KEY.userEmail }">
						<form class="pull-right" name="form" method="post" action="boardReviewsDeleteBefore.html?pageNo=${pageNo }&bdNoRev=${boardReviews.bdNoRev}">
							<input type="hidden" value="${boardReviews }" name="boardQa">
							<input type="hidden" value="${pageNo }" name="pageNo">
							<input type="hidden" value="${boardReviews.bdNoRev }" name="bdNoRev">
							<input type="hidden" value="${boardReviews.refRev }" name="refRev">
							<input type="hidden" value="${boardReviews.reStep }" name="reStepRev">
							<input type="hidden" value="${boardReviews.reLevel }" name="reLevelRev">
							<input class="btn btn-info" type="button" onclick="location.href='boardReviewsEdit.html?pageNo=${pageNo }&bdNoRev=${boardReviews.bdNoRev}'" value="수정하긔">
							<input class="btn btn-danger" type="button" onclick="delConfirm()" value="삭제허기"> 
						</form>
						<%-- <input type="button" onclick="location.href='boardQaDeleteBefore.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}'" value="삭제하긔"> --%>
					</c:if>
				</td>
				<td colspan="3">
					<c:if test="${!empty USER_KEY || !empty ADMIN_KEY}">
						<input class="btn btn-info" type="button" onclick="location.href='boardQaReplyBefore.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}'" value="답글달긔">
						<input class="btn btn-success pull-right" type="button" onclick="location.href='boardReviewsRecommend.html?pageNo=${pageNo}&bdNoRev=${boardReviews.bdNoRev}'" value="추천하긔">
					</c:if>
				</td>
			</tr>
		
		</table>
<%-- 여기부터 댓글관련 --%>

<hr style="visibility: hidden;">
<%@ include file="/WEB-INF/jsp/board/boardReviewsDetailComments.jsp"%>
<hr style="visibility: hidden;">

<%-- 여기까지 댓글관련 --%>
		<a href="boardReviews.html?pageNo=${pageNo}" class="btn btn-info">목록으로</a>
	
	<c:if test="${!empty boardReviewsList}">
		<table class="tableType table-striped">
			<tr class="active" >
				<th class="active" align="center" width="5%">번호</th>
				<th class="active" align="center" width="60%">제 목</th>
				<th class="active" align="center" width="10%">글쓴이</th>
				<th class="active" align="center" width="15%">글쓴날</th>
				<th class="active" align="center" width="10%">조회/추천수</th>
			</tr>

			<c:forEach items="${boardReviewsList}" var="reviews">
				<tr>
					<td align="left" class="listtxt">
						<c:out value="${reviews.bdNoRev}" />
					</td>
					
					<!-- 게시판 타이틀 영역 -->
					<td align="left" class="listtxt title">
					<c:choose>
					
						<c:when test="${reviews.reStep == 0}">
							<a	href="<c:url value="boardReviewsDetail.html">
											<c:param name="pageNo" value="${pageNo}"/>
											<c:param name="bdNoRev" value="${reviews.bdNoRev}"/>
										</c:url>">
								<c:out value="${reviews.titleRev}" />
							</a>
						</c:when>
						
						<c:otherwise>
							<c:forEach begin="0" end="${reviews.reStep}" var="i">
								&nbsp;&nbsp;
							</c:forEach>
							<a	href="<c:url value="boardReviewsDetail.html">
											<c:param name="pageNo" value="${pageNo}"/>
											<c:param name="bdNoRev" value="${reviews.bdNoRev}"/>
										</c:url>">
								<c:out value="▷답글 : ${reviews.titleRev}" />
							</a>
						</c:otherwise>
					</c:choose>
					</td>
					
					<td align="center" class="listtxt">
						<c:out value="${reviews.userAlias}" />
					</td>
					<td align="center" class="listtxt">
						<font size="0.1"><c:out value="${reviews.dateRev}" /></font>
					</td>
					<td align="center" class="listtxt">
						<c:out value="${reviews.countRev}" />/
						<c:out value="${reviews.recommendRev}" />
					</td>
				</tr>
			</c:forEach>
		</table>
		<!-- 하단부 페이지 이동버튼 만들기 -->
		<c:if test="${listCountRev>0 }">
			<c:set var="maxPageRev" value="${requestScope.maxPageRev }" />
			<c:set var="startPageRev" value="${requestScope.startPageRev }" />
			<c:set var="endPageRev" value="${requestScope.endPageRev }" />
			<c:if test="${startPageRev>10 }">
				<a href="boardReviews.html?pageNo=${startPageRev -10 }">이전</a>
			</c:if>
			<c:forEach var="i" begin="${startPageRev }" end="${endPageRev }">
				<a href="boardReviews.html?pageNo=${i }">[${i }]</a>
			</c:forEach>
			<c:if test="${endPageRev<maxPageRev }">
				<a href="boardReviews.html?pageNo=${startPageRev+10 }">다음</a>
			</c:if>
		</c:if>
		<input type="hidden" value="${pageNo }" name="pageNo">
		<!-- 하단부 페이지 이동버튼 만들기 -->
	</c:if>

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
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>
