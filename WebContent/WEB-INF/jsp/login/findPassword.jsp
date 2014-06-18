<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,  initial-scale=1">
<title>비밀번호 찾기</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>
		<div id="memberEntryBox">
			<h2 class="bg-primary text-center">회원 가입</h2>

		<form:form modelAttribute="memberVo" method="post"
			action="findPassword.html" name="memberVo"
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
					id="userEmail" placeholder="email을 입력하세요." autofocus="true" required="true"/>
				<input type="button" class="btn button button-doubleCheck" value="메일체크" onclick="myEmailCheckAjax()" /> 
					<span id="emailCheckResult"></span>
			</div>


			<div class="control-group input-size">
				<label>비밀번호 찾기용 질의/응답</label>
				<div class="controls" id="passwdInquiryAndAnswer">
					<form:select path="passwdInquiry" cssClass="selectpicker" 
						height="40px" id="passwdInquiry">
						<form:option value="" label="질문 선택" disabled="disabled"/>
						<form:option selected="selected" value="태어난 고향(도시명만 입력)은?" label="태어난 고향(도시명만 입력)은?" />
						<form:option value="어머니 성함은?" label="어머니 성함은?" />
						<form:option value="아버지 성함은?" label="아버지 성함은?" />
						<form:option value="좋아하는 동물은?" label="좋아하는 동물은?" />
						<form:option value="좋아하는 책은?" label="좋아하는 책은?" />
					</form:select>
					
					<form:input path="passwdAnswer"  cssClass="input-xlarge"
						maxlength="25" size="30" id="passwdAnswer" placeholder="질문의 답을 입력해주세요!" required="true"/>
				</div>
			</div>
			<input type="submit" class="btn btn-primary login-button form-control" value="비밀번호 찾기!">
		</form:form>
	</div>
	<c:if test="${!empty yourPwd }">
		<div class="form-horizontal input-size">
			<label>사용자 Email</label>
			<input type="button" class="button button-doubleCheck" value="${yourPwd }" /> 
				<span id="emailCheckResult"></span>
		</div>
	</c:if>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>