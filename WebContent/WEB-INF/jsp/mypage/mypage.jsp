<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ����</title>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	ȸ������

	<form:form action="../mypage/mypageDelete.html">
		<table>
			<tr>
				<td colspan="2"><font color="green">�� īƮ</font></td>
			</tr>
			<tr>
				<td>��ǰ��</td>
				<td>����</td>
				<td>��ǰ����</td>
				<td>�Ұ�</td>
			</tr>
			<c:forEach items="${mycart }" var="cartSet">

				<tr>
					<td><input type="checkbox" value="${cartSet.itemNo }"
						name="checkCart" /></td>
					<td><c:out value="${cartSet.itemName }" /></td>
					<td><c:out value="${cartSet.price }" /></td>
					<td><c:out value="${cartSet.cartNumOfProduct }" /></td>
					<td><c:out value="${cartSet.cartSubTotal }" />��</td>
				</tr>
			</c:forEach>

			<tr>
				<td colspan="2"><font color="green">��������</font></td>
			</tr>
			<tr>
				<td><input type="submit" value="���û���"></td>
				<td><input type="button" value="�ٷα���"></td>
			</tr>
			<tr>
				<td>��ǰ��</td>
				<td>����</td>
				<td>��ǰ����</td>
				<td>�Ұ�</td>
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
</body>
</html>