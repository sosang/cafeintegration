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
	<div id="memberEntryBox">
		<h2 class="bg-info text-center" color="white">회원 가입</h2>

		<form:form modelAttribute="memberVo" method="post"
			action="../memberentry/memberEntry.html" name="memberVo"
			class="form-horizontal" role="form">
			<spring:hasBindErrors name="memberVo">
				<font color="red"><c:forEach items="${errors.globalErrors }"
						var="error">
						<spring:message code="${error.code }" />
					</c:forEach> </font>
			</spring:hasBindErrors>
			<div class="form-horizontal input-size">
				<label>사용자 Email</label>
				<form:input path="userEmail" type="email" cssClass="form-control"
					id="userEmail" placeholder="email을 입력하세요."  autofocus="true"/>
				<a href="checkEmail.html"><input type="button" class="button" value="중복확인" onclick="emailCheckAjax()"/></a>
				<span id="emailCheckResult">
				 	<c:choose>
						<c:when test="${not empty userEmail}"> 이미 가입한 이메일입니다 <a
								href="../login/login.html">로그인으로 이동 </a>
						</c:when>
						<c:when test = "${empty param.userEmail }">
						</c:when>
						<c:otherwise>반갑습니다. 멋진 이메일이네요.</c:otherwise>
					</c:choose> 
				</span>
			</div>
			<div id = "pwdDiv" class="form-horizontal input-size">
				<label>Password</label> 
				<input type="password"
					name="userPasswd" class="form-control" id="userPasswd"
					placeholder="사용하려는 Password를 입력하세요.">
			</div>
			<div id="confirmDiv" class="form-horizontal input-size">
				<label>Password 확인</label> <input
					type="password" class="form-control" id="confirmPasswd" onkeyup="checkPasswordMatch()"
					placeholder="Password를 한 번 더 입력하세요."><span id="validate"></span>
			</div>
			<div class="form-horizontal input-size">
				<label for="userAlias">사용자 별명</label>
				<form:input path="userAlias" id="userAlias"
					cssClass="userAlias form-control" maxlength="30" />
				<font color="red"><form:errors path="userAlias" /></font>
			</div>



			<div class="form-horizontal input-size">
			<label>전화번호</label>
			 	<div id="userPhone1"><form:select path="userPhone1"
						cssClass="userPhone span12" height="40px">
						<form:option value="010" label="010" />
						<form:option value="011" label="011" />
						<form:option value="016" label="016" />
						<form:option value="019" lable="019" />
					</form:select> - <form:input path="userPhone2" cssClass="userPhone span12"
						maxlength="4" size="5" id="userPhone2" onkeyup="moveFocus" /> - <form:input path="userPhone3" 
						cssClass="userPhone span12" maxlength="4" size="5"/><font
					color="red"><form:errors path="userPhone3" /></font>
					</div>
			</div>


			<div class="form-horizontal input-size">
			<label>우편번호</label>
				<div id="userPostcode"><form:input path="userPostcode"
						cssClass="userPostcode col-xs-2" maxlength="10"
						size="10" readonly="true"/>&nbsp;<input type="button" class="button" id="postcodeBtn" value="우편번호 찾기"
					onclick="openwin()" /><font color="red"><form:errors
							path="userPostcode" /> </font></div>
			</div>

			<div class="form-horizontal input-size">
			<label>기본 주소</label>
			<form:input path="userAddress1"
						cssClass="userAddress1 form-control" type="text" id="userAddress1" /><font
					color="red"><form:errors path="userAddress1" /> </font>
			</div>
			<div class="form-horizontal input-size">
			<label>상세 주소</label>
			<form:input path="userAddress2"
						cssClass="userAddress2 form-control" id="userAddress2" /><font
					color="red"><form:errors path="userAddress2" /> </font>
			</div>

			<button type="submit" class="btn btn-primary">회원 가입</button>
			<button type="reset" class="btn btn-default">다시 입력</button>

	</form:form>
	</div>
		<script type="text/javascript"
		src="<%=request.getContextPath()%>/java_script/check.js"></script>
	
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>