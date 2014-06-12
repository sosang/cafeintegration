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

<div id="fb-root"></div>


<div id="fb-root"></div>

      <script>
          window.fbAsyncInit = function() {
            FB.init({
              appId      : '662631310479429', // 앱 ID
              status     : true,          // 로그인 상태를 확인
              cookie     : true,          // 쿠키허용
              xfbml      : true           // parse XFBML
            });
            FB.Event.subscribe('auth.logout', function(response) {
                document.location.reload();
            });
           
            FB.getLoginStatus(function(response) {
                if (response.status === 'connected') {
                    
                    FB.api('/me', function(user) {
                        if (user) {
                            var image = document.getElementById('image');
                            image.src = 'http://graph.facebook.com/' + user.id + '/picture';
                            var name = document.getElementById('name');
                            /* name.innerHTML = user.name */
                            name.value = user.name
                            var id = document.getElementById('id');
                            id.value = user.id
                            email = document.getElementById('email');
                            email.value = user.email
                            
                        }
                        
                    });    
                     
                } else if (response.status === 'not_authorized') {

                } else {
                    
                }
            });


            FB.Event.subscribe('auth.login', function(response) {
                document.location.reload();
            });
          };
        
          // Load the SDK Asynchronously
          (function(d){
             var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
             if (d.getElementById(id)) {return;}
             js = d.createElement('script'); js.id = id; js.async = true;
             js.src = "//connect.facebook.net/ko_KR/all.js";
             ref.parentNode.insertBefore(js, ref);
           }(document));
          
          (function(d, s, id) {
        	  var js, fjs = d.getElementsByTagName(s)[0];
        	  if (d.getElementById(id)) return;
        	  js = d.createElement(s); js.id = id;
        	  js.src = "//connect.facebook.net/ko_KR/sdk.js#xfbml=1&appId=662631310479429&version=v2.0";
        	  fjs.parentNode.insertBefore(js, fjs);
        	}(document, 'script', 'facebook-jssdk'));
        

</script>
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
<form class="navbar-form navbar-right">
				<c:choose>

						<c:when test="${USER_KEY ne null}">

							<span id="loginc"><font color="white">${USER_KEY.userEmail }님이
									접속됨</font> </span>
									<div class="fb-like" data-href="https://developers.facebook.com/docs/plugins/" data-layout="button" data-action="like" data-show-faces="true" data-share="true"></div>
							<a href="../login/logout.html"
								class="btn btn-primary login-button">Logout</a>

					</c:when>
					 <c:when test = "${ADMIN_KEY ne null}">
				        ${ADMIN_KEY.adminEmail }님! 환영합니다.&nbsp;&nbsp;&nbsp; 
						<a href="../admin/member.html?pageNo=1" class="btn btn-primary login-button">회원현황</a>
						<a href="../admin/itemList.html" class="btn btn-primary login-button">상품현황</a>
						<a href="../mypage/mypage.html" class="btn btn-primary login-button">통계</a>
						<a href="../login/logout.html" class="btn btn-info member-button">로그아웃</a>
				    </c:when>
				    <c:otherwise >

							<font color="white">&nbsp;로그인을 해주세요.</font> <a
								href="../login/login.html" class="btn btn-primary login-button">Login</a>
							<a href="../memberentry/memberEntry.html"
								class="btn btn-info member-button">SignUp</a>
								<div class="fb-like" data-href="https://developers.facebook.com/docs/plugins/" data-layout="button" data-action="like" data-show-faces="true" data-share="true"></div>


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