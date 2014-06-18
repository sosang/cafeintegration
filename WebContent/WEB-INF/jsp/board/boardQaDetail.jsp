<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 읽기</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<style type="text/css">
a#coco{
	background-color: buttonface;
}
#lleft{
	align : left;
}
</style>
</head>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>자유게시판 읽기</h2>
		<table class="tableType table-striped">
			<tr>
			
				<th height="40px"  width="10%" class="active" >제목</th>
				<td width="60%">${boardQa.titleQa}</td>
				<th align="right" width="10%" class="active" >조회수</th>
				<td width="20%">${boardQa.countQa }</td>
			</tr>
			<tr>
				<th height="40px" class="active" >글쓴이</th>
				<td>${boardQa.userEmail}</td>
				<th class="active" >작성일</th>
				<td>${boardQa.dateQa }</td>
			</tr>
	
			<tr style="border-top: 1px solid #F5CB43; ">
				<th height="40px" width="80" class="active" >내용</th>
				<td width="30%" colspan="3"><pre style="background-color: #fff; border: 0px">${boardQa.contentQa}</pre></td>
			</tr>
			<tr>
				<td colspan="4" style="text-align: right;">글쓴이IP : ${boardQa.userIp }</td>
			</tr>
			<tr>
				<td colspan="3">
					<c:if test="${writer == USER_KEY.userEmail || writer == ADMIN_KEY.adminEmail}">
						<form class="pull-right " name="form" method="post" action="boardQaDeleteBefore.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}">
							<input type="hidden" value="${boardQa }" name="boardQa">
							<input type="hidden" value="${pageNo }" name="pageNo">
							<input type="hidden" value="${boardQa.bdNoQa }" name="bdNoQa">
							<input type="hidden" value="${boardQa.refQa }" name="refQa">
							<input type="hidden" value="${boardQa.reStep }" name="reStepQa">
							<input type="hidden" value="${boardQa.reLevel }" name="reLevelQa">
							<input class="btn btn-info" type="button" onclick="location.href='boardQaEdit.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}'" value="수정하기">
							<input class="btn btn-danger" type="button" onclick="delConfirm()" value="삭제하기"> 
						</form>
					</c:if>
				</td>
				<td>
					<c:if test="${!empty USER_KEY || !empty ADMIN_KEY}">
						<input class="btn btn-info pull-right" type="button" onclick="location.href='boardQaReplyBefore.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}'" value="답글달기">
					</c:if>
				</td>
			</tr>
		</table>
<%-- 여기부터 댓글관련 --%>
<hr>
<%@ include file="/WEB-INF/jsp/board/boardQaDetailComments.jsp"%>
<hr>

<%-- 여기까지 댓글관련 --%>
		<a href="boardQa.html?pageNo=${pageNo}" class="btn btn-info">목록으로</a>
		
<%-- 여기부터 질답목록 --%>
<c:if test="${!empty boardQaList}">
		<table border="1" class="tableType">
			<tr>
				<th align="center" width="5%">번호</th>
				<th align="center" width="60%">제 목</th>
				<th align="center" width="10%">글쓴이</th>
				<th align="center" width="15%">글쓴날</th>
				<th align="center" width="10%">조회수</th>
			</tr>

			<c:forEach items="${boardQaList}" var="article">
				<tr>
					<td align="center" class="listtxt">
						<c:out value="${article.bdNoQa}" />
					</td>
					
					
					<td align="left" class="listtxt title" >
					<c:choose>
						<c:when test="${article.reStep == 0}">
							<a	href="<c:url value="boardQaDetail.html">
											<c:param name="pageNo" value="${pageNo}"/>
											<c:param name="bdNoQa" value="${article.bdNoQa}"/>
										</c:url>">
								<c:out value="${article.titleQa}" />
							</a>
						</c:when>
						<c:otherwise>
							<c:forEach begin="0" end="${article.reStep}" var="i">
								&nbsp;&nbsp;
							</c:forEach>
							<a	href="<c:url value="boardQaDetail.html">
											<c:param name="pageNo" value="${pageNo}"/>
											<c:param name="bdNoQa" value="${article.bdNoQa}"/>
										</c:url>">
								<c:out value="▷답글 : ${article.titleQa}" />
							</a>
						</c:otherwise>
					</c:choose>
					</td>
					
					
					<td align="center" class="listtxt">
						<c:out value="${article.userAlias}" />
					</td>
					<td align="center" class="listtxt">
						<font><c:out value="${article.dateQa}" /></font>
					</td>
					<td align="center" class="listtxt">
						<c:out value="${article.countQa}" />
					</td>
				</tr>
			</c:forEach>
		</table>
		<!-- 하단부 페이지 이동버튼 만들기 -->
		<c:if test="${listCountQa>0 }">
			<c:set var="maxPageQa" value="${requestScope.maxPageQa }" />
			<c:set var="startPageQa" value="${requestScope.startPageQa }" />
			<c:set var="endPageQa" value="${requestScope.endPageQa }" />
			<c:if test="${startPageQa>10 }">
				<a href="boardQa.html?pageNo=${startPageQa -10 }">이전</a>
			</c:if>
			<c:forEach var="i" begin="${startPageQa }" end="${endPageQa }">
				<a href="boardQa.html?pageNo=${i }">[${i }]</a>
			</c:forEach>
			<c:if test="${endPageQa<maxPageQa }">
				<a href="boardQa.html?pageNo=${startPageQa+10 }">다음</a>
			</c:if>
		</c:if>
		<input type="hidden" value="${pageNo }" name="pageNo">
		<!-- 하단부 페이지 이동버튼 만들기 -->
</c:if>

<%-- 여기까지 질답목록 --%>	
		
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
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>
