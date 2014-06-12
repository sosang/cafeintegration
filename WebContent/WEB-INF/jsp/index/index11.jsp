<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="fb-root"></div>

      <script>
          window.fbAsyncInit = function() {
            FB.init({
              appId      : '662631310479429', // 앱 ID
              appSecret  : 'a8446cf2bbb841df7bfd9b444da478fb',
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
                            name.innerHTML = user.name
                            var id = document.getElementById('id');
                            id.innerHTML = user.id
                            var email = document.getElementById('email');
                            email.innerHTML = user.email
                            var uid = response.authResponse.userID;
                            var accessToken = response.authResponse.accessToken;
                            accessToken.innerHTML = user.accessToken
                        }
                    });    
                     
                } else if (response.status === 'not_authorized') {

                } else {
                    
                }
            },true);

          

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
        

        </script>
        <fb:like send="true" width="450" show_faces="true" font="verdana"></fb:like>
        <fb:login-button show-faces="false"  data-show-faces="true" data-auto-logout-link="true" width="200" data-size="xlarge" max-rows="1" scope="email,user_friends"></fb:login-button>
        <p>사용자정보 출력</p>
        
<div align="left">
    <img id="image"/>
    <div id="name"></div>
    <div id="id"></div>
    <div id="email"></div>
    <div id="accessToken"></div>
    <a href="#" onclick="FB.logout();">[logout]</a><br>
</div>

</body>
</html>