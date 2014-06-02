<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>구매하기</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<div class="purchase">
			<form action="../end/end.html">
				<table style="font-size: 10pt;">
					<tr>
						<td colspan="2"><font color="green">바구니에는 다음의 상품이
								들어있다.</font></td>
					</tr>
					<tr>
						<td>상품명</td>
						<td>가격</td>
						<td>상품갯수</td>
						<td>소계</td>
					</tr>

					<tr>
						<td>${purchaseLine.itemName }</td>
						<td>${purchaseLine.price }</td>
						<td>${purchaseLine.numOfProduct }</td>
						<td>${purchaseLine.price*purchaseLine.numOfProduct }</td>
					</tr>

					<tr>
						<td><br> <input type="hidden" name="userEmail"
							value="cafe4"><br></td>
					<tr>
					<tr>
						<td colspan="4">2.주문자정보</td>
					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text"></td>
					</tr>
					<tr>
						<td>우편번호</td>
						<td><input type="text"></td>
						<td>핸드폰번호</td>
						<td><input type="text"></td>
					</tr>
					<tr>
						<td>주소</td>
						<td><input type="text"></td>
						<td colspan="2"><input type="text"></td>
					</tr>
					<tr>
						<td>email</td>
						<td colspan="3"><input type="text"></td>
					</tr>
					<tr>
						<td><br> <br></td>
					<tr>
					<tr>
						<td colspan="4">3.배송지 정보</td>

					</tr>
					<tr>
						<td>이름</td>
						<td><input type="text" name="receiver"></td>
					</tr>
					<tr>
						<td>우편번호</td>
						<td><input type="text" name="recpostcode"></td>
						<td>핸드폰번호</td>
						<td><input type="text" name="recphone"></td>
					</tr>
					<tr>
						<td>주소</td>
						<td colspan="3"><input type="text" width="50pt"
							name="recaddr"></td>


					</tr>
					<tr>
						<td>배송메시지</td>
						<td colspan="3"><textarea rows="5" cols="50"
								style="resize: none" name="remarks"></textarea></td>
					</tr>
				</table>
				<input type="submit" value="결제하기">

			</form>

		</div>
		<br> <br>
	</div>
</body>
</html>