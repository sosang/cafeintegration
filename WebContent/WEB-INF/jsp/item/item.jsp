<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/CSSTableGenerator.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>구매목록</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div class="row">
		<c:forEach items="${itemList }" var="item">
			<div class="block"
				onclick="location.href='../detail/detail.html?itemNo=${item.itemNo}'"
				style='cursor: pointer;'>
				<ul>
					<li><img src="../img/${item.photo }"></li>
					<li>${item.itemName }</li>
					<li>${item.price }</li>
					<li>${item.roastingDate }</li>
				</ul>
			</div>
		</c:forEach>
	</div>
</body>
</html>