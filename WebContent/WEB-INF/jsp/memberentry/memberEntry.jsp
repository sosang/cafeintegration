<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
	<%-- <!-- cart header -->
<%@ include file="" %> --%>

	<h2>회원 가입</h2>

	<form:form modelAttribute="memberVo" method="post"
		action="../memberentry/memberEntry.html" name="memberVo">
		
		<spring:hasBindErrors name="memberVo">
			<font color="red"><c:forEach items="${errors.globalErrors }"
					var="error">
					<spring:message code="${error.code }" />
				</c:forEach> </font>
		</spring:hasBindErrors>
		<table>
			<tr height="40px">
				<td>회원 email</td>
				<td><form:input path="userEmail" cssClass="userEmail"
						maxlength="30" type="email" placeholder="이메일을 입력하세요" /><input type="button" class="button" value="중복확인"
					id="emailCheck" onClick="checkEmailDuplication"/><span id="emailCheckResult"></span><font
					color="red"><form:errors path="userEmail" /> </font></td>
			</tr>
			<tr height="40px">
				<td>비밀번호</td>
				<td><input type="password" name="userPasswd" id="userPasswd"
					maxlength="15" /><font color="red"><form:errors
							path="userPasswd" /> </font></td>
			</tr>

			<tr height="40px">
				<td>비밀번호 확인</td>
				<td><input type="password" name="confirmPasswd"
					id="confirmPasswd" keyup="checkPasswordMatch()" /><span id="validate"></span></td>
			</tr>
			<tr height="40px">
				<td>별명</td>
				<td><form:input path="userAlias" cssClass="userAlias"
						maxlength="30" /><font color="red"><form:errors
							path="userAlias" /> </font></td>
			</tr>
			<tr height="40px">
				<td>전화번호</td>
				<td><form:select path="userPhone1" cssClass="userPhone">
						<form:option value="010" label="010" />
						<form:option value="011" label="011" />
						<form:option value="016" label="016" />
						<form:option value="019" lable="019" />
					</form:select> - <form:input path="userPhone2" cssClass="userPhone" maxlength="5" size="5"/>
					- <form:input path="userPhone3" cssClass="userPhone" maxlength="5" size="5"/><font
					color="red"><form:errors path="userPhone3" /> </font></td>
			</tr>


			<tr height="40px">
				<td>우편번호</td>
				<td><form:input path="userPostcode" cssClass="userPostcode" 
						maxlength="10" size="10"  /><input type="button" class="button"
					value="우편번호 찾기" onclick="openwin()"/><font color="red"><form:errors
							path="userPostcode" /> </font></td>
			</tr>
			<tr height="40px">
				<td>기본 주소</td>
				<td><form:input path="userAddress1" cssClass="userAddress1"
						maxlength="20" size="25" /><font color="red"><form:errors
							path="userAddress1" /> </font></td>
			</tr>
			<tr height="40px">
				<td>상세 주소</td>
				<td><form:input path="userAddress2" cssClass="userAddress2"
						maxlength="20" size="25" /><font color="red"><form:errors
							path="userAddress2" /> </font></td>
			</tr>
		</table>
		<table>
			<tr>
				<td height="40px" align="center"><input type="submit"
					name="btnSubmit" value="회원가입"></td>
				<td height="40px" align="center"><input type="reset"
					name="btnReset" value="다시 입력"></td>
		</table>
	</form:form>
<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>