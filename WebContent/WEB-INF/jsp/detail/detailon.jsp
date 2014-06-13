<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/CSSdetail.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function submitForm1() {
		var form = document.forms['test_form'];
		form.action = '../cart/cartAdd.html';
		form.submit();
	}
	function submitForm2() {
		var form = document.forms['test_form'];
		form.action = '../purchase/purchaseDirect.html';
		form.submit();
	}
</script>
<title>상품 상세 화면</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>

	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div class="body">

		<table class="tblock">
			<tr>
				<td><img src="../img/${item.photo}"></td>
				<td align="center">
					<table>
						<tr height="50">

							<td width="160">${item.itemName}</td>
						</tr>
						<tr height="50">

							<td width="160">${item.origin }</td>
						</tr>
						<tr height="50">

							<td width="160">${item.price}원</td>
						</tr>
						<tr height="50">

							<td width="160">${item.itemInfo}</td>
						</tr>
						<tr height="50">

							<td width="160">${item.roastingLevel }</td>
						</tr>
						<tr height="50">

							<td width="160"><fmt:formatDate value="${item.roastingDate}"
									pattern="yyyy-MM-dd" /></td>
						</tr>
						<tr height="50">

							<td width="160">${item.processing}</td>
						</tr>
						<tr>
							<td colspan="2" align="center" width="230">
								<form name="test_form">
									<input type="hidden" name="itemNo" value="${item.itemNo }">
									<input type="hidden" name="price" value="${item.price }">
									<!-- 									<input type="hidden" name="userEmail" value="cafe4"> -->

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
											<td width="150px"><input type="button" value="장바구니"
												class="btn btn-warning" onclick="submitForm1()"> <input
												type="button" class="btn btn-danger" value="바로구매"
												onclick="submitForm2()"><input type="button"
												value="상품목록" class="btn btn-link"
												onclick="location.href='../item/item.html'"></td>
										</tr>
									</table>
								</form>
						</tr>

					</table>
				</td>
			</tr>
		</table>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>


</body>
</html>