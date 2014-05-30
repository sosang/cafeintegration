<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>
	<div align="center" class="body">
		<h2>상품 목록 화면</h2>
		<table border="1">
			<tr class="header">
				<th align="center" width="80">상품ID</th>
				<th align="center" width="320">상품명</th>
				<th align="center" width="100">가격</th>
			</tr>
			<c:forEach items="${itemList}" var="item">
				<tr class="record">
					<td align="center">${item.itemNo}</td>
					<td align="left"><a href="../detail/detail.html?itemNo=${item.itemNo}">${item.itemName}</a></td>
					<td align="right">${item.price}원</td>
				</tr>
			</c:forEach>
		</table>
	</div>
<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>