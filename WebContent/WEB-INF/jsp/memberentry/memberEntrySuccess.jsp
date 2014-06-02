<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원등록 완료 화면</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<%--헤더파일 추가 후, INCLUDING
<%@ include file=" " %>
 --%>	
 
 <div align="center" class="body">
		<h2>회원등록 완료 화면</h2>
		<b><font color="red">회원등록이 완료되었습니다.</font></b><br>
		<table>
			<tr height="40px">
				<td>회원 email</td>
				<td>${member.userEmail }</td>
			</tr>
			<tr height="40px">
				<td>비밀번호</td>
				<td>${member.userPasswd }</td>
			</tr>
			<tr height="40px">
				<td>별명</td>
				<td>${member.userAlias }</td>
			</tr>
			<tr height="40px">
			<td>전화번호</td>
			<td>${member.userPhone1 }-${member.userPhone2 }-${member.userPhone3 }</td>
			<tr height="40px">
				<td>우편번호</td>
				<td>${member.userPostcode }</td>
			</tr>
			<tr height="40px">
				<td>기본 주소</td>
				<td>${member.userAddress1 }</td>
			</tr>
			<tr height="40px">
				<td>상세 주소</td>
				<td>${member.userAddress2 }</td>
			</tr>


		</table>
		<br>
<a href="../index/index.html">■ 목록으로</a><br>
	</div>
<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>