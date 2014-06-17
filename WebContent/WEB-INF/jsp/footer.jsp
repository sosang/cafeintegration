<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<br>
<footer id="fixedBottom" >
	<div id="footer">
		<div class="footerContainer">
			<h3 ><i class="fa fa-coffee"></i> 카페 4.0은</h3>
			<p >멋진 개발자가 되고 싶은 커피 매니아 '최동선'님의 향기로운 온라인 카페입니다.<br> 점점 더 업그레이드 될 예정이며, 최동선님의 멋진 비주얼에 어울리는 카페로 항상 거듭나겠습니다.</p>
			<address>
				<font class="tFooter"><i class="fa fa-envelope"></i> 개발자:</font> <a class="aFooter" href="mailto:freenice12@gmail.com">freenice12</a>, <a class="aFooter" href="mailto:elpida1737@gmail.com">elpida1737</a>, <a class="aFooter" href="mailto:chldongsun@gmail.com">chldongsun</a>, <a class="aFooter" href="mailto:sosang92@gmail.com">sosang92</a> 
			</address>
			<p >이 사이트는 <font class="tFooter">Apache 2.0</font> 규약에 따라 공개된 <a class="aFooter" href="http://getbootstrap.com">Bootstrap</a>, <a class="aFooter" href="https://github.com">github</a>, <a class="aFooter" href="jquery.com">jQuery</a> 등의 소스 코드를 참조하였습니다.
			</p>
			<font size="0.5em">© 2014 cafe 4.0. All rights reserved.</font>
			
		</div>
	</div>
</footer>

<!-- footer 맨 밑에 위치 시키는 쿼리 -->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
   	$(document).ready(function() {
   	  var bodyHeight = $("body").height();
   	  var vwptHeight = $(window).height();
   	  if (vwptHeight > bodyHeight) {
   	    $("footer#fixedBottom").css("position","absolute").css("bottom",0);
   	  }
   	});
   	</script>
</html>