<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호 검색</title>
<%@ include file="../../jsp/jsp_header.jsp"%>
<link href="<%=request.getContextPath()%>/css/post.css"
	rel="stylesheet">
</head>
<body>
	<div class="content" id="postTable">
		<form action="postcode.html" method="post">
			<div class="form-group">
				<input type="text" class="form-control" name="eupmyeondong"
					placeholder="동을 제외한 지역명으로 검색: " required autofocus>
			</div>
			<button type="submit" class="btn btn-warning">찾기</button>
		</form><br>
			<c:choose>
				<c:when test="${not empty postcode}">
					<table border="0">

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
				</c:when>
				<c:when test = "${empty param.eupmyeondong }">

				</c:when>
				<c:otherwise>
					<div class="alert alert-danger">
						<h4>앗, 별에서 오신 고갱님???</h4>
						</div>
						<p>찾으시는 "<strong><u><c:out value="${param.eupmyeondong}"/>동</u></strong>"은 없나봐요. <br/>확인해보시고 다시 한 번 도전~!</p>

				</c:otherwise>
			</c:choose>


	</div>

	<!-- 우편번호 넘기기 -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/java_script/postcode.js"></script>

	<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>