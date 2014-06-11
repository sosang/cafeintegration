<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/CSSitem.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>구매목록</title>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>

	<div class="topblock">
		<p>Coffee Bean</p>
	</div>
	<div align="center" id="rownew">
		<c:forEach items="${itemList }" var="item">
			<div class="block"
				onclick="location.href='../detail/detail.html?itemNo=${item.itemNo}'"
				style='cursor: pointer;'>
				<ul>
					<li><img src="${toViewImage }"></li>
					<li><p>${item.itemName }</p></li>
					<li><p>${item.price }</p></li>
					<li><p>
							<fmt:formatDate value="${item.roastingDate}" pattern="yyyy-MM-dd" /></li>
				</ul>
			</div>
		</c:forEach>
	</div>
</body>
</html>