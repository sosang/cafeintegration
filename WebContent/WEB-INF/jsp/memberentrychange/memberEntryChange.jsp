<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,  initial-scale=1">
<title>회원 정보변경</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>

</head>

<body>
	<%@ include file="/WEB-INF/jsp/header.jsp"%>

	<div id="memberEntryBox">
		<h2 class="bg-primary text-center">회원 정보 수정</h2>

		<form:form modelAttribute="memberVo" method="post"
			action="../memberentrychange/memberEntryChange.html" name="memberVo"
			class="form-horizontal" role="form">
			<spring:hasBindErrors name="memberVo">
				<font color="red"><c:forEach items="${errors.globalErrors }"
						var="error">
						<spring:message code="${error.code }" />
					</c:forEach> </font>
			</spring:hasBindErrors>

			<div class="form-horizontal input-size">
				<label>사용자 Email</label><br> <font size="3px">${myinfo.userEmail }</font>

			</div>

			<div id="pwdDiv" class="form-horizontal input-size">
				<label>Password</label> <input type="password" name="userPasswd"
					class="form-control" id="userPasswd"
					placeholder="사용하려는 Password를 입력하세요.">
			</div>

			<div id="confirmDiv" class="form-horizontal input-size">
				<label>Password 확인</label> <input type="password"
					class="form-control" id="confirmPasswd"
					onkeyup="checkPasswordMatch()" placeholder="Password를 한 번 더 입력하세요.">
				<span id="validate"></span>
			</div>


			<div class="form-horizontal input-size">
				<label for="userAlias">사용자 별명</label><br> <font size="3px">${myinfo.userAlias }</font><br>
				<font size="2px" style="color: red">사용자 별명은 변경할 수 없습니다.</font>
			</div>

			<div class="control-group input-size">
				<label class="controls">전화번호</label>
				<div id="userPhone1">
					<form:select path="userPhone1" cssClass="selectpicker"
						data-sytle="btn-info" height="40px" value="${myinfo.userPhone1 }">
						<form:option value="010" label="010" />
						<form:option value="011" label="011" />
						<form:option value="016" label="016" />
						<form:option value="019" lable="019" />
					</form:select>
					-
					<form:input path="userPhone2" type="phone"
						css="userPhone input-small" maxlength="4" size="5" id="userPhone2"
						onkeyup="moveFocus" value="${myinfo.userPhone2 }" />
					-
					<form:input path="userPhone3" type="phone"
						cssClass="userPhone input-small" maxlength="4" size="5"
						value="${myinfo.userPhone3 }" />
					<font color="red"><form:errors path="userPhone3" /></font>
				</div>
			</div>

			<div class="form-horizontal input-size">
				<label>우편번호</label>
				<div id="userPostcode">
					<form:input path="userPostcode" cssClass="userPostcode input-small"
						maxlength="10" size="10" readonly="true"
						value="${myinfo.userPostcode }" />
					&nbsp;<input type="button" class="button" id="postcodeBtn"
						value="우편번호 찾기" onclick="openwin()" /><font color="red"><form:errors
							path="userPostcode" /> </font>
				</div>
			</div>

			<div class="form-horizontal input-size">
				<label>기본 주소</label>
				<form:input path="userAddress1" cssClass="userAddress1 form-control"
					type="text" id="userAddress1" value="${myinfo.userAddress1 }" />
				<font color="red"><form:errors path="userAddress1" /> </font>
			</div>
			<div class="form-horizontal input-size">
				<label>상세 주소</label>
				<form:input path="userAddress2" cssClass="userAddress2 form-control"
					id="userAddress2" value="${myinfo.userAddress2 }" />
				<font color="red"><form:errors path="userAddress2" /> </font>
			</div>

			<button type="submit" id="EntryBtn" class="btn btn-primary">정보
				수정</button>
			<button type="reset" class="btn btn-default">다시 입력</button>

		</form:form>
		<br> <br>
	</div>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
	<%@ include file="/WEB-INF/jsp/footer.jsp"%>
</body>
</html>