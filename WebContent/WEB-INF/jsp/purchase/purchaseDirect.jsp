<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매하기</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/CSScart.css">


<script type="text/javascript">
	function sameaddress() {
		document.getElementById("receivername").value = document
				.getElementById("receiver").value;
		document.getElementById("recpostcode").value = document
				.getElementById("userPostcode").value;
		document.getElementById("recphone").value = document
				.getElementById("userPhone").value;
		document.getElementById("recaddr").value = document
				.getElementById("userAddress").value;
	}
	function changeText() {
		document.getElementById("remarksarea").value = document
				.getElementById("recmarks").value;
	}
</script>

</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<div class="purchase">
			<form action="../end/end.html">


				<table class="carttable" style="font: 30px;">
					<tr>
						<th colspan="7"><font color="green" size="14px">장바구니
								상품 목록</font></th>
					</tr>
					<tr>

						<th>번호</th>
						<th width="300">상품명</th>
						<th width="150">판매가</th>
						<th>수량</th>
						<th width="150">소계</th>
						<th width=30></th>
					</tr>



					<tr>

						<td id="centerid"><h5></h5></td>
						<td><img width="80px" height="50px"
							src="${purchaseLine .photo }"> &nbsp;&nbsp;&nbsp;<c:out
								value="${purchaseLine.itemName }" /></td>
						<td id="centerid"><c:out value="${purchaseLine.price }" /></td>
						<td id="centerid"><c:out
								value="${purchaseLine.numOfProduct }" /></td>
						<td id="centerid"><c:out
								value="${purchaseLine.price*purchaseLine.numOfProduct}" />원</td>
						<td></td>
					</tr>



					<tr>
						<td><br> <br></td>
					<tr>
					<tr>
						<td colspan="4">주문합계</td>

					</tr>
					<tr>
						<td><c:out value="${itemSet.cartSubTotal }" /></td>
					</tr>
					<tr>
						<td colspan="4">1.주문자정보</td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" id="receiver"></td>
					</tr>
					<tr>
						<td>우편번호</td>
						<td><input type="text" id="userPostcode"
							value="${myinfo.userPostcode }"></td>
						<td>핸드폰번호</td>
						<td><input type="text" id="userPhone"
							value="${myinfo.userPhone1 }-${myinfo.userPhone2 }-${myinfo.userPhone3 }"></td>
					</tr>
					<tr>
						<td>주소</td>
						<td colspan="3"><input type="text" id="userAddress"
							size="77px"
							value="${myinfo.userAddress1 }&nbsp;${myinfo.userAddress2 }"></td>

					</tr>
					<tr>
						<td>email</td>
						<td>${myinfo.userEmail }</td>
					</tr>
					<tr>
						<td><br> <br></td>
					<tr>
					<tr>
						<td colspan="4">2.배송지 정보</td>

					</tr>
					<tr>
						<td>배송지 선택</td>
						<td><input type="radio" value="newAddress" id="newAddress"
							name="Addresscheck" onclick="newaddress()">아래주소로
							신규등록&nbsp;&nbsp;<input type="radio" value="sameAddress"
							name="Addresscheck" onclick="sameaddress()">주문자와 동일</td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="receiver" id="receivername"
							required="required"></td>
					</tr>
					<tr>
						<td>우편번호</td>
						<td><input type="text" name="recpostcode" id="recpostcode"
							required="required"></td>
						<td>핸드폰번호</td>
						<td><input type="text" name="recphone" id="recphone"
							required="required"></td>
					</tr>

					<tr>
						<td>주소</td>
						<td colspan="3"><input type="text" size="77px" name="recaddr"
							id="recaddr" required="required"></td>

					</tr>

					<tr>
						<td>배송메시지</td>
						<td><select name="recmarks" id="recmarks"
							onchange="changeText()"><option value="배송 전에 연락 주세요">배송
									전에 연락주세요</option>
								<option value="경비실에 맡겨주세요">경비실에 맡겨주세요</select>
						<td>
					</tr>

					<tr>
						<td></td>
						<td colspan="3"><textarea rows="5" cols="50" id="remarksarea"
								style="resize: none" name="remarks" required="required"></textarea></td>
					</tr>
				</table>
				<input class="btn" type="submit" value="결제하기"> <a
					class="btn btn-warning" href="../index/index.html">홈</a>

			</form>

		</div>
		<br> <br>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>
