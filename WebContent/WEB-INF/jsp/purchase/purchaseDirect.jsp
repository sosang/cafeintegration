<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>구매하기</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/CSScart.css">
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
						<td colspan="4">1.주문자정보</td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text"></td>
					</tr>
					<tr>
						<td>우편번호</td>
						<td>${myinfo.userPostcode }</td>
						<td>핸드폰번호</td>
						<td>${myinfo.userPhone1 }-${myinfo.userPhone2 }-${myinfo.userPhone3 }</td>
					</tr>
					<tr>
						<td>주소</td>
						<td>${myinfo.userAddress1 }${myinfo.userAddress2 }</td>

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
						<td><input type="radio" value="newAddress"
							name="Addresscheck">아래주소로 신규등록&nbsp;&nbsp;<input
							type="radio" value="sameAddress" name="Addresscheck">주문자와
							동일</td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="receiver" required="required"></td>
					</tr>
					<tr>
						<td>우편번호</td>
						<td><input type="text" name="recpostcode" required="required"></td>
						<td>핸드폰번호</td>
						<td><input type="text" name="recphone" required="required"></td>
					</tr>

					<tr>
						<td>주소</td>
						<td><input type="text" width="50pt" name="recaddr"
							required="required"></td>
						<td colspan="2"><input type="text" width="50pt"
							name="recaddr2" required="required"></td>
					</tr>

					<tr>
						<td>배송메시지</td>
						<td colspan="3"><textarea rows="5" cols="50"
								style="resize: none" name="remarks" required="required"></textarea></td>
					</tr>
				</table>
				<input class="btn" type="submit" value="결제하기"> <a
					class="btn btn-warning" href="../index/index.html">홈</a>

			</form>

		</div>
		<br> <br>
	</div>
</body>
</html>