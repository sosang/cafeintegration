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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/CSScart.css">

<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>

</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>

	<div id="myInfo"
		style="width: 280px; height: 210px; border: solid 2px; margin: 50px; padding: 5px; float: left">
		<p>
			<img
				src="http://th06.deviantart.net/fs71/PRE/f/2011/310/5/a/giant_nyan_cat_by_daieny-d4fc8u1.png"
				width="40px" height="30px" style="float: left; margin: 5px"> <font
				size="2">${user.userAlias }님</font><br> <font size="2">
				cafe4.0에 오신것을 환영합니다.</font>
		</p>
		<p>
			<img src="../img/phone.png" style="float: left; margin: 2px"><font
				size="2">${user.userPhone1 }-${user.userPhone2 }-${user.userPhone3 }</font>
		</p>
		<p>
			<img src="../img/email.png" style="float: left; margin: 2px"><font
				size="2">${user.userEmail }</font>
		</p>
		<br>
		<p style="text-align: center">------------------------------</p>

		<p style="text-align: center">
			<input type="button" value="정보 수정">
		</p>
	</div>
	<!-- 	회원정보 -->
	<!-- 	<table> -->
	<!-- 		<tr height="40px"> -->
	<!-- 			<td>별명:</td> -->
	<%-- 			<td><input type="text" value="${user.userAlias }"></td> --%>
	<!-- 		</tr> -->
	<!-- 		<tr height="40px"> -->
	<!-- 			<td>전화번호</td> -->
	<!-- 			<td><select name="userPhone"> -->
	<!-- 					<option value="010">010</option> -->
	<!-- 					<option value="011">011</option> -->
	<!-- 					<option value="016">016</option> -->
	<!-- 					<option value="019">019</option> -->
	<!-- 			</select> - <input type="text" maxlength="5" size="5" -->
	<%-- 				value="${user.userPhone2 }" /> - <input type="text" maxlength="5" --%>
	<%-- 				size="5" value="${user.userPhone3 }" /></td> --%>
	<!-- 		</tr> -->

	<!-- 		<tr height="40px"> -->
	<!-- 			<td>우편번호:</td> -->
	<%-- 			<td><input type="text" value="${user.userPostcode }"></td> --%>
	<!-- 		</tr> -->
	<!-- 		<tr height="40px"> -->
	<!-- 			<td>기본주소:</td> -->
	<%-- 			<td><input type="text" value="${user.userAddress1 }"></td> --%>
	<!-- 		</tr> -->
	<!-- 		<tr height="40px"> -->
	<!-- 			<td>상세주소:</td> -->
	<%-- 			<td><input type="text" value="${user.userAddress2 }"></td> --%>
	<!-- 		</tr> -->
	<!-- 		<tr> -->
	<!-- 			<td><input type="submit" value="정보변경"></td> -->
	<!-- 		</tr> -->

	<!-- 	</table> -->

	<div align="center" class="body">
		<div class="cart">
			<form name="test_form">


				<table class="carttable" style="font: 30px;">
					<tr>
						<th colspan="5"><font color="green">내 카트</font></th>
					</tr>
					<tr>
						<th width="30"></th>
						<th width="300">상품명</th>
						<th width="150">가격</th>
						<th>상품갯수</th>
						<th width="150">소계</th>
					</tr>
					<c:forEach items="${mycart }" var="cartSet">

						<tr>
							<td id="centerid"><input type="checkbox"
								value="${cartSet.itemNo }" name="checkCart" /></td>
							<td id="centerid"><c:out value="${cartSet.itemName }" /></td>
							<td id="centerid"><c:out value="${cartSet.price }" /></td>
							<td id="centerid"><c:out
									value="${cartSet.cartNumOfProduct }" /></td>
							<td id="centerid"><c:out value="${cartSet.cartSubTotal }" />원</td>
						</tr>
					</c:forEach>
					<tr style="text-align: center">
						<td colspan="5"><input type="button" value="선택삭제"
							class="btn btn-warning" onclick="submitForm1()"> <input
							type="button" class="btn btn-danger" value="바로구매"
							onclick="submitForm2()"></td>
					</tr>
				</table>
			</form>
			<table class="carttable" style="font: 30px;">
				<tr>
					<th colspan="5"><font color="green">결제내역</font></th>
				</tr>

				<tr>
					<th width="300">상품명</th>
					<th width="150">가격</th>
					<th>상품갯수</th>
					<th width="150"	>소계</th>
					<th>주문일자</th>
				</tr>
				<c:forEach items="${mypurchase }" var="purchaseSet">
					<tr>

						<td id="centerid"><c:out value="${purchaseSet.itemName }" /></td>
						<td id="centerid"><c:out value="${purchaseSet.price }" /></td>
						<td id="centerid"><c:out value="${purchaseSet.numOfProduct }" /></td>
						<td id="centerid"><c:out
								value="${purchaseSet.price*purchaseSet.numOfProduct }" /></td>
						<td id="centerid"><fmt:formatDate
								value="${purchaseSet.timeOfPurchase}" pattern="yyyy.MM.dd" /></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>