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

 <div class="memberResult">
		<div class="panel panel-default">
			  
			  <div class="panel-heading"><b><font color="red">회원등록이 완료되었습니다.</font></b></div>
			 
			  <!-- 가입 회원 정보 -->
			  <ul class="list-group">
			    <li class="list-group-item"><label>회원 email: </label>&nbsp;${member.userEmail }</li>
			    <li class="list-group-item"><label>비밀번호: </label>&nbsp;${member.userPasswd }</li>
			    <li class="list-group-item"><label>별명: </label>&nbsp;${member.userAlias }</li>
			    <li class="list-group-item"><label>전화번호: </label>&nbsp;${member.userPhone1 }-${member.userPhone2 }-${member.userPhone3 }</li>
			    <li class="list-group-item"><label>우편번호: </label>&nbsp;${member.userPostcode }</li>
			    <li class="list-group-item"><label>주소: </label>&nbsp;${member.userAddress1 }  ${member.userAddress2 }</li>
			  </ul>
		</div>

<a href="../index/index.html" class="btn btn-default btn-sm">홈으로</a><br>
</div>

<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>