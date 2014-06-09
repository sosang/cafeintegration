<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<div class="cart">

			<table style="font-size: 10pt;">
				<tr>
					<td colspan="2"><font color="green">바구니에는 다음의 상품이 들어있다.</font></td>
				</tr>
				<tr>
					<td>선택</td>
					<td>상품명</td>
					<td>가격</td>
					<td>상품갯수</td>
					<td>소계</td>
				</tr>


				<c:forEach items="${cart }" var="itemSet">
					<tr>
						<td><c:out value="${itemSet.itemName }" /></td>
						<td><c:out value="${itemSet.price }" /></td>
						<td><c:out value="${itemSet.cartNumOfProduct }" /></td>
						<td><c:out value="${itemSet.cartSubTotal}" />원</td>
					</tr>
				</c:forEach>
				<tr>
					<td><input type="submit" value="바로구매"
						onclick="location.href='../purchase/purchase.html?userEmail=cafe4'"></td>
					<td><input type="button" value="상품목록보기"
						onclick="location.href='../item/item.html'"></td>
					<td colspan="2"><input type="button" value="장바구니비우기"
						onclick="location.href='../cart/cartClear.html?userEmail=cafe4'"></td>
			</table>
		</div>
		<br>


		<c:out value="${message }" />
		<br>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>