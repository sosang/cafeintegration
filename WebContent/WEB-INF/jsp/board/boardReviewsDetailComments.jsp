<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>


	<c:if test="${!empty USER_KEY}">
		<form method="post"
			action='boardRevWriCom.html?pageNo=${pageNo }&bdNoRev=${boardReviews.bdNoRev}'>
			<table class="tableType">
				<tr style="height: 65px">
					<td width="10%">${USER_KEY.userEmail }</td>

					<td width="80%">
					<input type="hidden" name="bdNoRev"	value="${boardReviews.bdNoRev }" /> 
					<input type="hidden" name="pageNo" value="${pageNo }" /> 
					<textarea rows="3" style="width: 100%" name="bdRevCommentsContent" draggable="false"></textarea>
					</td>

					<td width="10%"><input class="btn " type="submit" value="reply"
						style="height: 100%; padding-left: 10px; padding-right: 10px" />
					</td>


				</tr>
			</table>
		</form>
	</c:if>

<table class="tableType">
	<c:forEach items="${commentsListRev}"  var="comments">
		<tr>
			<td width="10%">${comments.userAlias}</td>
			<td width="60%"><pre style="background-color:#fff; border: 0px" >${comments.bdRevCommentsContent}</pre></td>
			<td width="10%">${comments.bdRevCommentsDate}</td>
			<td width="10%">${comments.bdRevCommentsIp}</td>

				<c:if test="${USER_KEY.userEmail == comments.userEmail }">
				<td width="10%">
					<input type="hidden" name="bdNoRevComments"
						value="${comments.bdNoRevComments}" />
					<%-- <img id="checkPass"
						src="<%=request.getContextPath() %>/images/icon/xIcon.png"
						onclick="reallyRev(${comments.bdNoRevComments})" class="pointer"
						width="20" height="20"> --%>
						<button class="btn" onclick="reallyRev(${comments.bdNoRevComments})">Delete</button>
				</td></c:if>
		</tr>


	</c:forEach>
</table>

<c:if test="${empty USER_KEY}">
	<b>To write --> <a href="../login/login.html">Login</a></b>
</c:if>