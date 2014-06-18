<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,  initial-scale=1">
<title>FAQ</title>
<style type="text/css">
a#coco{
	background-color: buttonface;
}
</style>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/faq.css">
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<c:if test="${faqList ne null }">
	<div align="center" class="body">
	<h1>FAQ</h1>
		<br>
		<a style="text-align:right;" href="boardFaqWriteBefore.html" class="btn" id="coco"><i class="fa fa-pencil"></i> FAQ쓰기</a>
		<!-- <input class="btn " type="button" onclick="location.href='boardFaqWriteBefore.html'" value="FAQ쓰기"> -->
		<hr>
			<table width="960px">
				<c:forEach items="${faqList}" var="articles" varStatus="status">
					<tr>
						<td width="200px">
							<a href="#bda${status.count }"><c:out value="${articles.titleFaq}" /></a>
						</td>
						<td width="460px">
							<c:out value="${articles.contentFaq}" />
						</td>
						<td width="300px">
							<input class="btn" type="button" onclick="location.href='boardFaqUpdateBefore.html?pageNo=${pageNo }&bdNoFaq=${articles.bdNoFaq}'" value="수정">
							<input class="btn" type="button" onclick="location.href='boardFaqDeleteBefore.html?pageNo=${pageNo }&bdNoFaq=${articles.bdNoFaq}'" value="지우개">
						</td>
					</tr>		
				</c:forEach>
			</table>
<br />
		<!-- 하단부 페이지 이동버튼 만들기 -->
		<c:if test="${listCount>0 }">
			<c:set var="maxPage" value="${requestScope.maxPage }" />
			<c:set var="startPage" value="${requestScope.startPage }" />
			<c:set var="endPage" value="${requestScope.endPage }" />
			<c:if test="${startPage>10 }">
				<a href="boardFaq.html?pageNo=${startPage -10 }">이전</a>
			</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<a href="boardFaq.html?pageNo=${i }">[${i }]</a>
			</c:forEach>
			<c:if test="${endPage<maxPage }">
				<a href="boardFaq.html?pageNo=${startPage+10 }">다음</a>
			</c:if>
		</c:if>
		<!-- 하단부 페이지 이동버튼 만들기 -->
	</div>
</c:if>
<hr>
<c:if test="${empty faqList}">
<h1>등록된 게시물이 없습니다.</h1>
</c:if>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/java_script/faq.js"></script>
<script type="text/javascript">
function delConfirm(){
	if (confirm("정말 삭제하시겠습니까??") == true){    //확인
		    document.form.submit();
	}else{   //취소
		    return;
	}
}
</script>
<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>

</body>
</html>