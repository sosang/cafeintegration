<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
	<c:if test="${empty commentsListRev}">
		댓글이 없습니다.
	</c:if>

<table class="tableType table-striped">
	<c:forEach items="${commentsListRev}"  var="comments">
		<tr>
			<td width="10%">${comments.userAlias}</td>
			<td width="60%"><pre style="background-color:#fff; border: 0px" >${comments.bdRevCommentsContent}</pre></td>
			<td width="10%">${comments.bdRevCommentsDate}</td>
			<td width="10%">${comments.bdRevCommentsIp}</td>

				<c:if test="${USER_KEY.userEmail == comments.userEmail || ADMIN_KEY.adminEmail == comments.userEmail}">
				<td width="10%">
					<input type="hidden" name="bdNoRevComments"
						value="${comments.bdNoRevComments}" />
						<a class="btn btn-danger" onclick="reallyRev(${comments.bdNoRevComments})"><i class="fa fa-trash-o fa-lg"></i> 삭제하기</a>
					<%-- <img id="checkPass"
						src="<%=request.getContextPath() %>/images/icon/xIcon.png"
						onclick="reallyRev(${comments.bdNoRevComments})" class="pointer"
						width="20" height="20"> --%>
						<%-- <button class="btn" onclick="reallyRev(${comments.bdNoRevComments})"><i class="fa fa-trash-o fa-fw"></i>Delete</button> --%>
				</td></c:if>
		</tr>


	</c:forEach>
</table>
<c:if test="${!empty USER_KEY || !empty ADMIN_KEY}">
		<form method="post"
			action='boardRevWriCom.html?pageNo=${pageNo }&bdNoRev=${boardReviews.bdNoRev}'>
			<table class="tableType">
				<tr style="height: 65px">
					<td width="10%">${USER_KEY.userAlias }</td>

					<td width="80%">
					<input type="hidden" name="bdNoRev"	value="${boardReviews.bdNoRev }" /> 
					<input type="hidden" name="pageNo" value="${pageNo }" /> 
					<textarea rows="3" style="width: 100%" name="bdRevCommentsContent" draggable="false"></textarea>
					</td>

					<td width="10%"><input class="btn btn-info" type="submit" value="reply"
						style="height: 100%; padding-left: 10px; padding-right: 10px" />
					</td>


				</tr>
			</table>
		</form>
	</c:if>
<c:if test="${empty USER_KEY}">
	<b>글을 쓰시려면... <a href="../login/login.html">로그인</a></b><br>
</c:if>
