<%@page import="javax.xml.ws.http.HTTPException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header id="top" class="fluid">
<div align="right">
	<c:choose>
	<c:when test = "${USER_KEY ne null}">
        ${USER_KEY.userEmail }님! 환영합니다.&nbsp;&nbsp;&nbsp; 
		<a href="../mypage">마이페이지</a>
		<a href="../login/logout.html">로그아웃</a>
    </c:when>
    <c:otherwise>
		<a href="../login/login.html">로그인</a>
	</c:otherwise>
	</c:choose>
</div>
	<p>&nbsp;</p>
	<nav id="menuNav" class="fluid">
		<ul id="menu" class="fluid fluidList">
			<a href="../index/index.html"><li
				class="fluid menuItem zeroMargin_tablet zeroMargin_desktop">홈</li></a>
			<a href="../exp/exp_main.html"><li class="fluid menuItem">구매</li></a>
			<a href="../pur/pur_main.html"><li class="fluid menuItem">체험</li></a>
			<li class="fluid menuItem">게시판
				<ul>
					<a href="../board/boardNotice.html?pageNo=1">
						<li	class="fluid menuItem">공지사항</li>
					</a>
					<a href="../board/boardQa.html?pageNo=1">
						<li	class="fluid menuItem">Q↔A</li>
					</a>
				</ul>
			</li>


		</ul>
	</nav>
</header>
<hr>