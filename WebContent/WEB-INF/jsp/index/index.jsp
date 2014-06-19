<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,  initial-scale=1">
<title>카페테스트</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>
<!--[if lt IE 7]> <html class="ie6 oldie"> <![endif]-->
<!--[if IE 7]>    <html class="ie7 oldie"> <![endif]-->
<!--[if IE 8]>    <html class="ie8 oldie"> <![endif]-->
<!--[if gt IE 8]><!-->
</head>
<body id="top">
<header id="top" class="fluid">
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

</header>
<div>

		<div class="content" id="content" role="main">

			<!-- =================== carousel(이미지 슬라이딩 효과 시작) ======================= -->

			<div id="cafeCarousel" class="carousel slide carousel-fade"
				data-ride="carousel">

				<!-- ========== Wrapper for slides======= -->
				<div class="carousel-inner">
					<div class="item active">
						<img src="../images/bg/sortingBeans_re.jpg" alt="intro"
							class="img-responsive intro" />
						<div class="carousel-caption">
							<h1>Cafe 4.0</h1>
							<h4>
								세 명의 아름다운 젊은이와 어정쩡한 중년이 모여 커피향과 아름다운 체험을 모토로 느낌있는 Cafe를 만들었습니다.<br>
								커피를 매개로 풍성한 맛과 향기가 있는 사람들의 어울림. Cafe 4.0으로 모십니다.
							</h4>
						</div>
					</div>
					<div class="item">
						<img src="../images/bg/woodenBike_re.jpg" alt="woodenBike"
							class="img-responsive intro" />
						<div class="carousel-caption">
							<h1>나무 자전거</h1>
							<h4>
								르완다에서도 자주 보기 어려운 나무 자전거입니다.<br> 넓지 않은 고산지대의 소로길에 운반수단으로
								적합하지요.<br> Cafe 4.0은 어린 소년의 저 꿈을 지원합니다.
							</h4>
						</div>
					</div>
					<div class="item">
						<img src="../images/bg/pickingCoffeCherries_re.jpg"
							alt="cherryPicking" class="img-responsive intro" />
						<div class="carousel-caption">
							<h1>르완다</h1>
							<h4>
								천개의 고원으로 유명한 아프리카 고산지 르완다~!<br> 르완다는 아프리카 대륙의 중부 내륙에 위친한 작은
								나라입니다.<br> 식민지배를 용이하게 하고 종족간 갈등 유도한 결과 내부 대립에 이어 참혹한 학살
								이미지가 강한 곳입니다.<br> 하지만, 요즘 르완다는 아프리카 대륙 전체에서도 치안이 잘 유지되는
								곳입니다.<br> 아울러 우리와 유사한 역사적 경험과 부족한 자원을 지혜로 극복하고 점점 아프리카에서
								두각을 나타내는 곳이기도 합니다.
							</h4>
						</div>
					</div>
					<div class="item">
						<img src="../images/bg/coffeeParchments_re.jpg" alt="parchments"
							class="img-responsive intro" />
						<div class="carousel-caption">
							<h1>파치먼트 상태</h1>
							<h4>
								커피는 나무에서 열매상태인 커피 체리를 따고,<br> 수세식 방식의 경우, 겉 껍질을 벗겨 워싱스테이션이란
								곳에서 씻고는 속 껍질 채로 말립니다.<br> 그 말린 콩을 '파치먼트'라고 하고, 르완다 워싱스테이션에서
								흔히 볼 수 있는 파치먼트를 말리고 있는 사진입니다.
							</h4>
						</div>
					</div>
				</div>

				<!-- ======= Indicators(이미지 아랫 위치한 페이지 넘김 클릭하는 부분)  ======= -->
				<div id="carousel-indicators">
					<ol class="carousel-indicators cafeCarousel-indicator">
						<li data-target="#cafeCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#cafeCarousel" data-slide-to="1"></li>
						<li data-target="#cafeCarousel" data-slide-to="2"></li>
						<li data-target="#cafeCarousel" data-slide-to="3"></li>
					</ol>
				</div>
				<!-- ============== Controls(좌,우 넘김 클릭하는 부분) ============= -->
				<a class="left carousel-control" href="#cafeCarousel"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left"></span>
				</a> <a class="right carousel-control" href="#cafeCarousel"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right"></span>
				</a>
			</div>

		</div>
</div>
	<section id="aboutUs">
 		<div class="aboutUs_bg"><img src="../img/TypeOfCoffee.jpg" class="img-responsive"></div>
	</section>
		<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
		<%@ include file="/WEB-INF/jsp/footer.jsp"%>

</body>
</html>