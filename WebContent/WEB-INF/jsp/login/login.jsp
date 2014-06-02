<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,  initial-scale=1">
<%@page import="utils.WebConstants"%>
<title>로그인</title>
 
    
        
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="../../assets/js/html5shiv.js"></script>
      <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	
		<%@ include file="/WEB-INF/jsp/header.jsp"%>
	
	<c:choose>
	
		<c:when test="${loginMemberVo.userEmail == null }">
			
			<div align="center" class="body">
				<form:form modelAttribute="memberVo" class="signin" method="post"
					action="../login/login.html">
					<spring:hasBindErrors name="memberVo">
						<font color="red"> <c:forEach
								items="${errors.globalErrors }" var="error">
								<spring:message code="${error.code}" />
							</c:forEach>
						</font>
					</spring:hasBindErrors>

					<table>

						<tr height="40px">
					
							<td width ="255px"><form:input path="userEmail"  class="form-control"  placeholder="Email address" /> <font
								color="red"><form:errors path="userEmail" /></font></td>
								</tr>
								<tr>
							
							<td><form:password path="userPasswd" class="form-control" placeholder="Password" />
								<font color="red"><form:errors path="userPasswd" /></font></td>
								</tr>
								<tr>
							<td width="150px">
							<input type="submit"   value="Login" class="btn btn-primary login-button">
							<a href="../memberentry/memberEntry.html"   class="btn btn-info member-button">SignUp</a>
							</td>
						
						</tr>

					</table>

				</form:form>
			</div>
		</c:when>
		
		

		<c:when test="${loginMemberVo.userEmail != null }">


					<table>

						 <tr height="40px">
							<td align="center">${loginMemberVo.userEmail }님이 접속됨</td>
							<td><a href="../userentryform/member.html">SignUp</a></td>
							<td><a href="../login/logout.html">Logout</a></td>
						</tr>

						<tr height="40px">

						</tr> 

					</table>

		</c:when>

	</c:choose>


	<footer>

	<%@ include file="/WEB-INF/jsp/js_footer.jsp" %>
	${USER_KEY.userEmail } = ${USER_KEY.userEmail }

	</footer>

</body>
</html>


