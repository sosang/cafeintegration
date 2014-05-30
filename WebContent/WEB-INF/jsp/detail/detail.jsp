<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 상세 화면</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
		<h2>상품 상세 화면</h2>
		<table>
			<tr>
				<td><img src="../img/${item.photo}"></td>
				<td align="center">
					<table>
						<tr height="50">
							<td width="80">상품명</td>
							<td width="160">${item.itemName}</td>
						</tr>
						<tr height="50">
							<td width="80">원산지</td>
							<td width="160">${item.origin }</td>
						</tr>
						<tr height="50">
							<td width="80">가격</td>
							<td width="160">${item.price}원</td>
						</tr>
						<tr height="50">
							<td width="80">비고</td>
							<td width="160">${item.itemInfo}</td>
						</tr>
						<tr height="50">
							<td width="80">로스팅정도</td>
							<td width="160">${item.roastingLevel }</td>
						</tr>
						<tr height="50">
							<td width="80">로스팅날짜</td>
							<td width="160">${item.roastingDate }</td>
						</tr>
						<tr height="50">
							<td width="80">프로세싱</td>
							<td width="160">${item.processing}</td>
						</tr>
						<tr>
							<td colspan="2" align="center" width="230">
								<form action="../cart/cartAdd.html">
									<input type="hidden" name="itemNo" value="${item.itemNo }">
									<input type="hidden" name="price" value="${item.price }">
									<input type="hidden" name="userEmail" value="cafe4">
									
									<table>
										<tr>
											<td><select name="cartNumOfProduct">
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
													<option value="4">4</option>
													<option value="5">5</option>
													<option value="6">6</option>
													<option value="7">7</option>
													<option value="8">8</option>
													<option value="9">9</option>
											</select>&nbsp;개</td>
											<td><input type="submit" value="장바구니"><input
												type="button" value="바로구매"
												onclick="location.href='../purchase/purchase.html?itemNo=${item.itemNo}&price=${item.price }&cartNumOfProduct=${cartNumOfProduct.value }&cartSubTotal=${item.price*cartNumOfProduct.value }'"></td>
										</tr>
									</table>
								</form>
						</tr>
						<tr>
							<td colspan="2" align="center" width="240"><a
								href="../item/item.html">ㅁ 상품목록으로 돌아가기</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>