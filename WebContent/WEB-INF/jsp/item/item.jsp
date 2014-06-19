<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,  initial-scale=1">
<title>상품 목록</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div class="itemForm">
	<div class="topblock">
		<p>Coffee Bean</p>
	</div>
	<div align="center" class="row" >
	
	
				
		<c:forEach items="${itemList }" var="item">
		<div class="col-lg-4"
	
				onclick="location.href='../detail/detail.html?itemNo=${item.itemNo}'"
				style='cursor: pointer;'>
			<div class="thumbnail">
				<ul>
				
					<li>
					
					<img src="${item.photo}" style="max-height: 300px; max-width: 600px"></li>
					<li><p>${item.itemName }</p></li>
					<li><p>${item.price }원</p></li>
					<li><p>
							<fmt:formatDate value="${item.roastingDate}" pattern="yyyy-MM-dd" /></li>
				</ul>
				</div>
			</div>
		</c:forEach>
		</div>
</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>