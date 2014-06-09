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
	회원정보

	<form:form action="../mypage/mypageDelete.html">
		<table>
			<tr>
				<td colspan="2"><font color="green">내 카트</font></td>
			</tr>
			<tr>
				<td>상품명</td>
				<td>가격</td>
				<td>상품갯수</td>
				<td>소계</td>
			</tr>
			<c:forEach items="${mycart }" var="cartSet">

				<tr>
					<td><input type="checkbox" value="${cartSet.itemNo }"
						name="checkCart" /></td>
					<td><c:out value="${cartSet.itemName }" /></td>
					<td><c:out value="${cartSet.price }" /></td>
					<td><c:out value="${cartSet.cartNumOfProduct }" /></td>
					<td><c:out value="${cartSet.cartSubTotal }" />원</td>
				</tr>
			</c:forEach>

			<tr>
				<td colspan="2"><font color="green">결제내역</font></td>
			</tr>
			<tr>
				<td><input type="submit" value="선택삭제"></td>
				<td><input type="button" value="바로구매"></td>
			</tr>
			<tr>
				<td>상품명</td>
				<td>가격</td>
				<td>상품갯수</td>
				<td>소계</td>
			</tr>
			<c:forEach items="${mypurchase }" var="purchaseSet">
				<tr>

					<td><c:out value="${purchaseSet.itemName }" /></td>
					<td><c:out value="${purchaseSet.price }" /></td>
					<td><c:out value="${purchaseSet.numOfProduct }" /></td>

				</tr>
			</c:forEach>
		</table>
	</form:form>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>