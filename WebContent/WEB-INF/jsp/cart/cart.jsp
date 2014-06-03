<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<style>
.carttable {
	counter-reset: number 0;
	table-layout: auto;
}

.carttable td {
	border: 1px solid black;
	padding: 5px;
}

.carttable th {
	height: 50px;
	text-align: center;
	padding: 5px;
}
.carttable h5:before{
	counter-increment: number;
        content: counter(number) "";
}
</style>

</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<div class="cart">

			<table  class="carttable" style="font: 30px;">
				<tr>
					<th colspan="2"><font color="green" size="20px">장바구니 상품 목록</font></th>
				</tr>
				<tr>
					<th>번호</th>
					<th>상품명</th>
					<th>판매가</th>
					<th>수량</th>
					<th>소계</th>
				</tr>


				<c:forEach items="${cart }" var="itemSet">
					<tr>
					<td width="10"><h5></h5></td>
						<td><img width="100px" height="50px" src="../img/${itemSet.photo }"> <c:out
								value="${itemSet.itemName }" /></td>
						<td><c:out value="${itemSet.price }" /></td>
						<td><c:out value="${itemSet.cartNumOfProduct }" /></td>
						<td><c:out value="${itemSet.cartSubTotal}" />원</td>
					</tr>
				</c:forEach>
			</table>

			<input type="submit" value="바로구매"
				onclick="location.href='../purchase/purchaseCart.html?userEmail=cafe4&indirect=indirect'">
			<input type="button" value="상품목록보기"
				onclick="location.href='../item/item.html'"> <input
				type="button" value="장바구니비우기"
				onclick="location.href='../cart/cartClear.html?userEmail=cafe4'">

		</div>
		<br>


		<c:out value="${message }" />
		<br>
	</div>
</body>
</html>