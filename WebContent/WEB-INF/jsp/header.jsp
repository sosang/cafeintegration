<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<link rel="stylesheet" href="../css/basic.css" />
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
		<!-- <div id="logo">
			<a href="../index/index.html"><img src="../images/cafe_logo.png"
				class="logo img-responsive"></a>
		</div> -->
		<nav class="collapse navbar-collapse bs-navbar-collapse"
			role="navigation">

			<ul class="nav navbar-nav">
				<li ><a href="../index/index.html" style="padding: 5px"><img  src="../images/cafe_logo.png"
				class="logo img-responsive" width="70" height="70"></a></li>
				<li><a href="../aboutUs/aboutUs.html">카페 4.0 소개</a></li>
				<li><a href="../item/item.html">구매</a></li>
		<!--	<li><a href="#">체험</a></li> -->
				<li class="dropdown"><a href="" class="dropdown-toggle"
					data-toggle="dropdown">게시판 <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="../board/boardNotice.html?pageNo=1">공지</a></li>
						<li><a href="../board/boardFaq.html?pageNo=1">FAQ</a></li>
						<li><a href="../board/boardQa.html?pageNo=1">자유게시판</a></li>
						<li><a href="../board/boardReviews.html?pageNo=1">체험후기</a></li>
					</ul></li>
			</ul>
			<!-- <form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-warning ">검색</button>
			</form> -->
<form class="navbar-form navbar-right">
				<c:choose>
						<c:when test="${USER_KEY ne null}">

							<span id="loginc"><font color="white"><strong>${USER_KEY.userAlias }</strong>님 접속중</font> </span>
									<a href="../mypage/mypage.html" class="btn btn-warning" >마이 페이지</a>
							<a href="../login/logout.html"
								class="btn btn-primary login-button">로그아웃</a>

					</c:when>
					 <c:when test = "${ADMIN_KEY ne null}">
						<a href="../admin/member.html?pageNo=1" class="btn btn-primary login-button">회원현황</a>
						<a href="../admin/itemList.html" class="btn btn-primary login-button">상품현황</a>
						<a href="../admin/boardNoticeList.html?pageNo=1" class="btn btn-primary login-button">공지쓰기</a>
						<a href="../admin/boardFaqList.html?pageNo=1" class="btn btn-primary login-button">FAQ쓰기</a>
						<a href="#" class="btn btn-primary login-button">통계</a>
						<a href="../login/logout.html" class="btn btn-info member-button">로그아웃</a>
				    </c:when>
				    <c:otherwise >
							<a href="../login/login.html" class="btn btn-primary login-button">로그인</a>
							<a href="../memberentry/memberEntry.html"
								class="btn btn-info member-button">회원가입</a>
					</c:otherwise>
				</c:choose>
</form>

			<!-- <span id="loginButtons"><a href="../login/login.html"
					class="btn btn-primary login-button">로그인</a>
					<a href="../memberentry/memberEntry.html" class="btn btn-info member-button">회원가입</a></span> -->
		</nav>

	</div>

	<footer>

	</footer>

</body>
</html>
