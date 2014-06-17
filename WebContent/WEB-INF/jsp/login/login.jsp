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

			<div style="margin-top: 150px" align="center" class="body">
				<form:form modelAttribute="memberVo" class="signin" method="post"
					action="../login/login.html">
					<spring:hasBindErrors name="memberVo" >
						<font color="red"> <c:forEach
								items="${errors.globalErrors }" var="error">
								<spring:message code="${error.code}" />
							</c:forEach>
						</font>
					</spring:hasBindErrors>

					<table >

						<tr height="40px">

							<td width="255px"><form:input path="userEmail"
									class="form-control" placeholder="Email을 입력해주세요." /> <font
								color="red"><form:errors path="userEmail" /></font></td>
						</tr>
						
						<tr>
							<td><form:password path="userPasswd" class="form-control"
									placeholder="Password가 필요합니다." /> <font color="red"><form:errors
										path="userPasswd" /></font></td>
						</tr>
						
						<tr>
							<td width="150px"><input type="submit" value="로그인"
								class="btn btn-primary login-button form-control"></td>
						</tr>
						
						<tr>
							<td width="150px"><a href="../memberentry/memberEntry.html"
								class="btn btn-info member-button form-control">회원가입</a></td>
						</tr>
						<tr>
							<td width="150px" align="right"><a href="findPassword.html"><font size="2"><i class="fa fa-key"></i> 비밀번호 찾기</font></a></td>
						</tr>

					</table>

				</form:form>
			</div>
		</c:when>



		<c:when test="${loginMemberVo.userEmail != null }">


			<table>

				<tr height="40px">
					<td align="center">${loginMemberVo.userEmail }님이접속됨</td>
					<td><a href="../userentryform/member.html">SignUp</a></td>
					<td><a href="../login/logout.html">Logout</a></td>
				</tr>

				<tr height="40px">

				</tr>

			</table>

		</c:when>

	</c:choose>


		<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>


</body>
</html>

