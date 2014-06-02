<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<table>
	<c:forEach items="${commentsListRev}" var="comments">
		<tr>
			<td width="100">${comments.userAlias}</td>
			<td width="600">${comments.bdRevCommentsContent}</td>
			<td width="100">${comments.bdRevCommentsDate}</td>
			<td width="100">${comments.bdRevCommentsIp}</td>
			<td width="60">
				<c:if test="${USER_KEY.userEmail == comments.userEmail }">
					<input type="hidden" name="bdNoRevComments" value="${comments.bdNoRevComments}"/>
					<img id="checkPass" src="<%=request.getContextPath() %>/images/icon/x.png" onclick="reallyRev(${comments.bdNoRevComments})" class="pointer" width="30" height="30">
				</c:if>
			</td>
		</tr>
		<tr height="1">
			<td colspan="5"><hr></td>
		</tr>
	</c:forEach>
	<c:if test="${!empty USER_KEY}">
		<table width="960" border="0">
			<tr>
				<td>
				<form method="post" action='boardRevWriCom.html?pageNo=${pageNo }&bdNoRev=${boardReviews.bdNoRev}'>
					<input type="hidden" name="bdNoRev" value="${boardReviews.bdNoRev }"/>
					<input type="hidden" name="pageNo" value="${pageNo }"/>
					<textarea rows="3" cols="100" name="bdRevCommentsContent" draggable="false"></textarea>
					<input type="submit" value="comments" />
				</form>
				</td>
			</tr>
		</table>
	</c:if>
</table>
<c:if test="${empty USER_KEY}">
	<b>To write --> <a href="../login/login.html">Login</a></b>
</c:if>