<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호 검색</title>
<%@ include file="../../jsp/jsp_header.jsp"%>
</head>
<body>
	<form action="postcode.html" method="get">
		<table border="0">
			<tr>
				<td colspan="2">찾으려는 '읍/면/동' 주소를 입력하세요.</td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" name="eupmyeondong"><input
					type="submit" value="검색"></td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td>기본주소</td>
			</tr>
			<c:forEach var="post" items="${postcode }">
				<tr>
					<td colspan="2"><a
						href="javascript:filladd('${post.zipcode }', '${post.sido }&nbsp; ${post.sigungu } &nbsp;
					${post.eupmyeondong } &nbsp; ${post.ri } &nbsp;
					${post.beonji } &nbsp; ${post.bldg }')">${post.zipcode }&nbsp;&nbsp;${post.sido }&nbsp;
							${post.sigungu } &nbsp; ${post.eupmyeondong } &nbsp; ${post.ri }
							&nbsp; ${post.beonji } &nbsp; ${post.bldg }</a></td>
				</tr>
			</c:forEach>
		</table>
	</form>
	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>