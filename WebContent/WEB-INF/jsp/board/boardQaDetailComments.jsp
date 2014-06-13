<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<table>
<c:if test="${!empty USER_KEY}">
		<table width="960" border="0">
			<tr>
				<td>
				<form method="post" action='boardQaWriCom.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}'>
					<input type="hidden" name="bdNoQa" value="${boardQa.bdNoQa }"/>
					<input type="hidden" name="pageNo" value="${pageNo }"/>
					<textarea rows="3" cols="100" name="bdQaCommentsContent" draggable="false"></textarea>
					<input type="submit" value="comments" />
				</form>
				</td>
			</tr>
		</table>
	</c:if>
	<c:forEach items="${commentsListQa}" var="comments">
		<tr>
			<td width="100">${comments.userAlias}</td>
			<td width="600">${comments.bdQaCommentsContent}</td>
			<td width="100">${comments.bdQaCommentsDate}</td>
			<td width="100">${comments.bdQaCommentsIp}</td>
			<td width="60">
				<c:if test="${USER_KEY.userEmail == comments.userEmail }">
					<input type="hidden" name="bdNoQaComments" value="${comments.bdNoQaComments}"/>
					<img id="checkPass" src="<%=request.getContextPath() %>/images/icon/x.png" onclick="really(${comments.bdNoQaComments})" class="pointer" width="30" height="30">
				</c:if>
			</td>
		</tr>
		<tr height="1">
			<td colspan="5"><hr></td>
		</tr>
	</c:forEach>
	
</table>
<c:if test="${empty USER_KEY}">
	<b>To write --> <a href="../login/login.html">Login</a></b>
</c:if>