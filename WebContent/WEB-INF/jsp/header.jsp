<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
</head>

<body id="top" class="fluid">
	<div class=" navbar-inverse " role="navigation">

		<div class="navbar-header">
			<button class="navbar-toggle collapsed" type="button"
				data-toggle="collapse" data-target=".bs-navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div id="logo">
			<a href="../index/index.html"><img src="../images/cafe_logo.png"
				class="logo img-responsive"></a>
		</div>
		<nav class="collapse navbar-collapse bs-navbar-collapse"
			role="navigation">

			<ul class="nav navbar-nav">
				<li class="active"><a href="../index/index.html">홈으로</a></li>
				<li><a href="#">about</a></li>
				<li><a href="../item/item.html">구매</a></li>
				<li><a href="#">체험</a></li>
				<li class="dropdown"><a href="" class="dropdown-toggle"
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
			<form class=" navbar-form navbar-right">
				<c:choose>

					<c:when test="${USER_KEY == null }">

						<font color="white">&nbsp;로그인을 해주세요.</font>
						<a href="../login/login.html" class="btn btn-primary login-button">Login</a>
						<a href="../memberentry/memberEntry.html"
							class="btn btn-info member-button">SignUp</a>


					</c:when>

					<c:when test="${USER_KEY != null }">

						<span id="loginc"><font color="white">${USER_KEY.userEmail }님이
								접속됨</font> </span>
								<a href="../mypage/mypage.html"
							class="btn btn-warning ">MyPage</a>
						<a href="../login/logout.html"
							class="btn btn-primary login-button">Logout</a>

					</c:when>

				</c:choose>

			</form>


			<!-- <span id="loginButtons"><a href="../login/login.html"
					class="btn btn-primary login-button">로그인</a>
					<a href="../memberentry/memberEntry.html" class="btn btn-info member-button">회원가입</a></span> -->
		</nav>

	</div>



	<footer> </footer>

</body>
</html>