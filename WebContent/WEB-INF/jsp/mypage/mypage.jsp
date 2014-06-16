<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	function submitForm1() {
		var form = document.forms['test_form'];
		form.action = '../mypage/mypageDelete.html';
		form.submit();
	}
	function submitForm2() {
		var form = document.forms['test_form'];
		form.action = '../purchase/purchaseDirect.html';
		form.submit();
	}
</script>
<title>Insert title here</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>

</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	회원정보
	<table>
		<tr height="40px">
			<td>별명:</td>
			<td><input type="text" value="${user.userAlias }"></td>
		</tr>
		<tr height="40px">
			<td>전화번호</td>
			<td><select name="userPhone">
					<option value="010">010</option>
					<option value="011">011</option>
					<option value="016">016</option>
					<option value="019">019</option>
			</select> - <input type="text" maxlength="5" size="5"
				value="${user.userPhone2 }" /> - <input type="text" maxlength="5"
				size="5" value="${user.userPhone3 }" /></td>
		</tr>

		<tr height="40px">
			<td>우편번호:</td>
			<td><input type="text" value="${user.userPostcode }"></td>
		</tr>
		<tr height="40px">
			<td>기본주소:</td>
			<td><input type="text" value="${user.userAddress1 }"></td>
		</tr>
		<tr height="40px">
			<td>상세주소:</td>
			<td><input type="text" value="${user.userAddress2 }"></td>
		</tr>
		<tr>
			<td><input type="submit" value="정보변경"></td>
		</tr>

	</table>


	<form name="test_form">
		<table>
			<tr>
				<td colspan="2"><font color="green">내 카트</font></td>
			</tr>
			<tr>
				<td></td>
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
				<td><input type="button" value="선택삭제" class="btn btn-warning"
					onclick="submitForm1()"> <input type="button"
					class="btn btn-danger" value="바로구매" onclick="submitForm2()"></td>
			</tr>
			<tr>
				<td colspan="2"><font color="green">결제내역</font></td>
			</tr>

			<tr>
				<td>상품명</td>
				<td>가격</td>
				<td>상품갯수</td>
				<td>소계</td>
				<td>주문일자</td>
			</tr>
			<c:forEach items="${mypurchase }" var="purchaseSet">
				<tr>

					<td><c:out value="${purchaseSet.itemName }" /></td>
					<td><c:out value="${purchaseSet.price }" /></td>
					<td><c:out value="${purchaseSet.numOfProduct }" /></td>
					<td><c:out
							value="${purchaseSet.price*purchaseSet.numOfProduct }" /></td>
					<td><fmt:formatDate value="${purchaseSet.timeOfPurchase}" pattern="yyyy.MM.dd" /></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>