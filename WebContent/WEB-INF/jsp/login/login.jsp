<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<div align="center" class="body">
	<c:choose>
	<c:when test="${USER_KEY.userEmail ne null}">
	${USER_KEY.userEmail }
	</c:when>
	<c:otherwise>
		<h2>로그인 화면</h2>
		<form:form modelAttribute="memberVo" method="post"
			action="../login/login.html">
			<spring:hasBindErrors name="memberVo">
				<font color="red"> <c:forEach items="${errors.globalErrors }"
						var="error">
						<spring:message code="${error.code}" />
					</c:forEach>
				</font>
			</spring:hasBindErrors>

			<table>

				<tr height="40px">
					<td>유저Email</td>
					<td><form:input path="userEmail" cssClass="userEmail" /> <font
						color="red"><form:errors path="userEmail" /></font></td>
				</tr>
				<tr>
					<td>패스워드</td>
				
					<td><form:password path="userPasswd" cssClass="userPasswd" />
						<font color="red"><form:errors path="userPasswd" /></font>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="로그인"></td>
				</tr>

				<tr height="40px">

				</tr>

			</table>

		</form:form>
	</c:otherwise>
	</c:choose>
	</div>
<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>

