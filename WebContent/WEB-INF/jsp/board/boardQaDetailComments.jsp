<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
	<c:if test="${empty commentsListQa }">
		댓글이 없습니다.
	</c:if>
<table class="tableType table-striped">
	<c:forEach items="${commentsListQa}" var="comments">
		<tr style="height: 65px">
			<td width="10%">${comments.userAlias}</td>
			<td width="60%" ><pre style="background-color:#fff; border: 0px" >${comments.bdQaCommentsContent}</pre></td>
			<td width="10%">${comments.bdQaCommentsDate}</td>
			<td width="10%">${comments.bdQaCommentsIp}</td>
			<td width="10%">
				<c:if test="${USER_KEY.userEmail == comments.userEmail || ADMIN_KEY.adminEmail == comments.userEmail }">
					<input type="hidden" name="bdNoQaComments" value="${comments.bdNoQaComments}"/>
					<a class="btn btn-danger" onclick="really(${comments.bdNoQaComments})"><i class="fa fa-trash-o fa-lg"></i> 삭제하기</a>
					<%-- <button class="btn" onclick="reallyRev(${comments.bdNoQaComments})"><i class="fa fa-trash-o fa-fw"></i>Delete</button>
					<img id="checkPass" src="<%=request.getContextPath() %>/images/icon/x.png" onclick="really(${comments.bdNoQaComments})" class="pointer" width="30" height="30"> --%>
				</c:if>
			</td>
		</tr>
	</c:forEach>
	<c:if test="${!empty USER_KEY || !empty ADMIN_KEY}">
		<table class="tableType" width="960" border="0">
			<tr>
				<td>
				<form method="post" action='boardQaWriCom.html?pageNo=${pageNo }&bdNoQa=${boardQa.bdNoQa}'>
					<input type="hidden" name="bdNoQa" value="${boardQa.bdNoQa }"/>
					<input type="hidden" name="pageNo" value="${pageNo }"/>
					<textarea  rows="3" cols="100" name="bdQaCommentsContent" draggable="false"></textarea>
					<input class="btn btn-info" type="submit" value="comments" />
				</form>
				</td>
			</tr>
		</table>
	</c:if>
</table>
<c:if test="${empty USER_KEY && empty ADMIN_KEY}">
	<b>글을 쓰시려면... <a href="../login/login.html">로그인</a></b><br>
</c:if>
