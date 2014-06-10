<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/CSScart.css">

</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%-- 	<c:choose> --%>
<%-- 		<c:when test="${cart.userEmail!=null }"> --%>
			<div align="center" class="body">
				<div class="cart">

					<table class="carttable" style="font: 30px;">
						<tr>
							<th colspan="7"><font color="green" size="14px">장바구니
									상품 목록</font></th>
						</tr>
						<tr>
							<th width=30></th>
							<th>번호</th>
							<th width="300">상품명</th>
							<th width="150">판매가</th>
							<th>수량</th>
							<th width="150">소계</th>
							<th width=30></th>
						</tr>


						<c:forEach items="${cart }" var="itemSet">
							<tr>
								<td></td>
								<td id="centerid"><h5></h5></td>
								<td><img width="80px" height="50px"
									src="../img/${itemSet.photo }"> &nbsp;&nbsp;&nbsp;<c:out
										value="${itemSet.itemName }" /></td>
								<td id="centerid"><c:out value="${itemSet.price }" /></td>
								<td id="centerid"><c:out
										value="${itemSet.cartNumOfProduct }" /></td>
								<td id="centerid"><c:out value="${itemSet.cartSubTotal}" />원</td>
								<td></td>
							</tr>
						</c:forEach>
					</table>

					<input type="submit" value="바로구매" class="btn btn-danger"
						onclick="location.href='../purchase/purchaseCart.html?indirect=indirect'">
					<input type="button" value="장바구니비우기" class="btn btn-default"
						onclick="location.href='../cart/cartClear.html'">
					<input type="button" value="상품목록" class="btn btn-link"
						onclick="location.href='../item/item.html'">

				</div>

			</div>
<%-- 		</c:when> --%>

<%-- 		<c:when test="${cart.userEmail==null }"> --%>


<!-- 			<div align="center" class="body"> -->
<%-- 				<form:form modelAttribute="memberVo" class="signin" method="post" --%>
<%-- 					action="../login/login.html"> --%>
<%-- 					<spring:hasBindErrors name="memberVo"> --%>
<%-- 						<font color="red"> <c:forEach --%>
<%-- 								items="${errors.globalErrors }" var="error"> --%>
<%-- 								<spring:message code="${error.code}" /> --%>
<%-- 							</c:forEach> --%>
<!-- 						</font> -->
<%-- 					</spring:hasBindErrors> --%>

<!-- 					<table> -->

<!-- 						<tr height="40px"> -->

<%-- 							<td width="255px"><form:input path="userEmail" --%>
<%-- 									class="form-control" placeholder="Email address" /> <font --%>
<%-- 								color="red"><form:errors path="userEmail" /></font></td> --%>
<!-- 						</tr> -->
<!-- 						<tr> -->

<%-- 							<td><form:password path="userPasswd" class="form-control" --%>
<%-- 									placeholder="Password" /> <font color="red"><form:errors --%>
<%-- 										path="userPasswd" /></font></td> --%>
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td width="150px"><input type="submit" value="Login" -->
<!-- 								class="btn btn-primary login-button form-control"></td> -->

<!-- 						</tr> -->

<!-- 					</table> -->

<%-- 				</form:form> --%>
<!-- 			</div> -->

<%-- 		</c:when> --%>
<%-- 	</c:choose> --%>
</body>
</html>