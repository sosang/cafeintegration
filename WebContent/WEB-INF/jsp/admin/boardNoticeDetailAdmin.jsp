<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 읽기</title>
<style type="text/css">
a#coco{
	background-color: buttonface;
}
</style>
</head>
<body>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>공지사항 읽기</h2>
		<table>
			<tr>
				<td height="40px" width="80">제목</td>
				<td width="600">${boardNotice.titleNtc}</td>
				<td align="right">${boardNotice.dateNtc }</td>
			</tr>
			<tr>
				<td height="40px" width="80">내용</td>
				<td width="800" colspan="2">${boardNotice.contentNtc}</td>
			</tr>
		</table>
		<hr>
		<a href="../admin/boardNoticeUpdateBefore.html?pageNo=${pageNo }&bdNoNtc=${boardNotice.bdNoNtc}" class="btn" id="coco">수정</a>
		<form name="form" method="post" action="boardNoticeDeleteBefore.html?pageNo=${pageNo }&bdNoNtc=${boardNotice.bdNoNtc}">
			<input class="btn " type="button" onclick="delConfirm()" value="삭제허기"> 
		</form>
		<a href="boardNoticeList.html?pageNo=${pageNo}" class="btn" id="coco">목록으로</a>
	</div>
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