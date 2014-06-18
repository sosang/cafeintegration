<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,  initial-scale=1">
<title>로그인</title>
<style type="text/css">
.form-group{
	width: 300px;
	float: left;
}
.foo{
	margin: 0 auto;
	width: 400px;
	height: 100px;
}
#but{
	margin-top:-49px;
	width: 90px;
	height: 83px;
	float: right;
}
</style>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<c:choose>
	<c:when test="${ADMIN_KEY.adminEmail ne null }">
	${ADMIN_KEY.adminEmail }님 하이요
    </c:when>
    <c:otherwise>
    
		<form action="../admin/loginSuccess.html" method="post">
		<div class="foo">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="adminEmail을 입력하세요" name="adminEmail">
			</div>
			<div class="form-group">
				<input type="password" class="form-control" placeholder="adminPassword를 입력하세요" name="adminPasswd">
			</div>
		
			<input type="submit" value="로긴" id="but" class="btn btn-primary login-button"/>
		</div>
		</form>
	
	</c:otherwise>
</c:choose>
<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>