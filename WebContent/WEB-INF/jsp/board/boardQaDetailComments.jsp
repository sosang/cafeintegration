<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
	<c:if test="${!empty USER_KEY}">
		<form method="post" action='boardQaWriCom.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}'>
			<table class='tableType'>
				<tr style="height: 65px">
					<td width="10%">
						${USER_KEY.userEmail }
					</td>
					<td width="80%">
						<input type="hidden" name="bdNoQa" value="${boardQa.bdNoQa }"/>
						<input type="hidden" name="pageNo" value="${pageNo }"/>
						<textarea rows="3" style="width: 100%" name="bdQaCommentsContent" draggable="false"></textarea>
					</td>
					<td width="10%">
						<input type="submit" value="reply" style="height: 100%; padding-left: 10px; padding-right: 10px"/>
					</td>
				</tr>
			</table>
		</form>
	</c:if>
<table class="tableType">
	<c:forEach items="${commentsListQa}" var="comments">
		<tr>
			<td width="10%">${comments.userAlias}</td>
			<td width="60%"><pre style="background-color:#fff; border: 0px" >${comments.bdQaCommentsContent}</pre></td>
			<td width="10%">${comments.bdQaCommentsDate}</td>
			<td width="10%">${comments.bdQaCommentsIp}</td>
				<c:if test="${USER_KEY.userEmail == comments.userEmail }">
			<td width="10%">
					<input type="hidden" name="bdNoQaComments" value="${comments.bdNoQaComments}"/>
					<%-- <img id="checkPass" src="<%=request.getContextPath() %>/images/icon/xIcon.png" onclick="really(${comments.bdNoQaComments})" class="pointer" width="30" height="30"> --%>
					<button class="btn" onclick="really(${comments.bdNoQaComments})">Delete</button>			
			</td>
				</c:if>
		</tr>
		
	</c:forEach>
</table>
<c:if test="${empty USER_KEY}">
	<b>To write --> <a href="../login/login.html">Login</a></b>
</c:if>