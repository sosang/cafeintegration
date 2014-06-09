<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품목록화면</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center">
		<h2>상품 목록 화면</h2>
		<a href="itemRegBefore.html">상품등록</a>
		<table border="1">
			<tr>
				<th align="center" width="80">상품ID</th>
				<th align="center" width="320">상품명</th>
				<th align="center" width="100">가격</th>
				<th align="center" width="120">수정/삭제</th>
			</tr>
			<c:forEach items="${itemList}" var="item">
				<tr class="record">
					<td align="center">${item.itemNo}</td>
					<td align="left"><a
						href="../admin/detail.html?itemNo=${item.itemNo}">${item.itemName}</a></td>
					<td align="right">${item.price}원</td>
					<td align="center"><a
						href="../admin/itemEdit.html?itemNo=${item.itemNo}">수정</a>/<a
						href="../admin/delete.html?itemNo=${item.itemNo}">삭제</a></td>
				</tr>
			</c:forEach>
		</table>
		<a href="itemRegBefore.html">상품등록</a><a href="itemRegBefore.html">상품등록</a>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>