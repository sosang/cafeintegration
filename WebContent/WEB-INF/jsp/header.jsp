<%@page import="javax.xml.ws.http.HTTPException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <div align="right">
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
</div> --%>
	<div class="navbar navbar-inverse navbar-static-top"
			role="navigation">

			<div class="navbar-header">
				<button class="navbar-toggle collapsed" type="button"
					data-toggle="collapse" data-target=".bs-navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div id="logo">
				<a href="index.html"><img src="../images/cafe_logo.png"
					class="logo img-responsive"></a>
			</div>
			<nav class="collapse navbar-collapse bs-navbar-collapse"
				role="navigation">

				<ul class="nav navbar-nav">
					<li class="active"><a href="../index/index.html">홈으로</a></li>
					<li><a href="#">about</a></li>
					<li><a href="../purchase/purchase.html?userEmail=cafe4">구매</a></li>
					<li><a href="#">체험</a></li>
					<li class="dropdown"><a href="../board/boardNotice.html?pageNo=1" class="dropdown-toggle"
						data-toggle="dropdown">게시판 <b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="../board/boardNotice.html?pageNo=1">공지</a></li>
							<li><a href="../board/boardFaq.html?pageNo=1">FAQ</a></li>
							<li><a href="../board/boardQa.html?pageNo=1">자유게시판</a></li>
							<li><a href="../board/boardReviews.html?pageNo=1">체험후기</a></li>
						</ul></li>
				</ul>
				<form class="navbar-form navbar-left" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-warning">Submit</button>

				</form>
				<span id="loginButtons">
				<c:choose>
					<c:when test = "${USER_KEY ne null}">
				        ${USER_KEY.userEmail }님! 환영합니다.&nbsp;&nbsp;&nbsp; 
						<a href="../mypage/mypage.html" class="btn btn-primary login-button">마이페이지</a>
						<a href="../login/logout.html" class="btn btn-info member-button">로그아웃</a>
				    </c:when>
				    <c:when test = "${ADMIN_KEY ne null}">
				        ${ADMIN_KEY.adminEmail }님! 환영합니다.&nbsp;&nbsp;&nbsp; 
						<a href="../mypage/mypage.html" class="btn btn-primary login-button">회원현황</a>
						<a href="../mypage/mypage.html" class="btn btn-primary login-button">상품현황</a>
						<a href="../mypage/mypage.html" class="btn btn-primary login-button">통계</a>
						<a href="../login/logout.html" class="btn btn-info member-button">로그아웃</a>
				    </c:when>
				    <c:otherwise>
						<a href="../login/login.html" class="btn btn-primary login-button">로그인</a>
						<a href="../memberentry/memberEntry.html" class="btn btn-info member-button">회원가입</a>
					</c:otherwise>
				</c:choose>
					
				</span>
			</nav>
		</div>
