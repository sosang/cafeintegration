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
		form.action = '../purchase/purchaseDirect2.html';
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
			<i class="fa fa-mobile"></i> <font
				size="2">${user.userPhone1 }-${user.userPhone2 }-${user.userPhone3 }</font>
		</p>
		<p>
			<i class="fa fa-envelope-o"></i><font
				size="2">${user.userEmail }</font>
		</p>
		<br>
		<p style="text-align: center">------------------------------</p>

		<p style="text-align: center">
			<input type="button" value="정보 수정" class="btn btn-primary">
		</p>
	</div>

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
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>
