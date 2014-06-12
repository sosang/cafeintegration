<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,  initial-scale=1">
</head>
<title>카페테스트</title>
<%@ include file="/WEB-INF/jsp/jsp_header.jsp"%>

<!--[if lt IE 7]> <html class="ie6 oldie"> <![endif]-->
<!--[if IE 7]>    <html class="ie7 oldie"> <![endif]-->
<!--[if IE 8]>    <html class="ie8 oldie"> <![endif]-->
<!--[if gt IE 8]><!-->
<body id="top">
<header>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
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
</header>
	<section>
	<div class="right-banner"><h1>오른쪽 배너</h1></div>
	</section>
<%@ include file="/WEB-INF/jsp/js_footer.jsp"%>
</body>
</html>