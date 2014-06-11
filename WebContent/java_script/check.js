//비밀번호 확인 이벤트(jQuery사용)
$(document).ready(function () {
	   $("#confirmPasswd").onkeyup(checkPasswordMatch);
//	   $("#userPhone2").onkeyup(moveFocus);
	});


function checkPasswordMatch() {
    var password1 = $("#userPasswd").val();
    var password2 = $("#confirmPasswd").val();
    if (password1 == password2){
        $("#validate").html("<font color='blue'>비밀번호 일치</font>");
    	$("#userAlias").focus();
    }else{
        $("#validate").html("<font color='red'>비밀번호 불일치</font>");
    }
}

function moveFocus(){
	
	var phone2 =$("#userPhone2").val();
	var phone3 =$("#userPhone3").val();
	if(phone2.length == maxlength){
		$("#userPhone3").focus;
	}else if(phone3.length == maxlength){
		$("#postcodeBtn").focus;
	}
}

//ajax 이메일 중복 검사
function getXMLHttpRequest() {
	if (window.ActiveXObject) {
		try {
			return new ActiveXObject("Msxml2.XMLHTTP");
		} catch(e) {
			try {
				return new ActiveXObject("Microsoft.XMLHTTP");
			} catch(e1) { return null; }
		}
	} else if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	} else {
		return null;
	}
}
var httpRequest = null;

function sendRequest(url, params, callback, method) {
	httpRequest = getXMLHttpRequest();
	var httpMethod = method ? method : 'GET';
	if (httpMethod != 'GET' && httpMethod != 'POST') {
		httpMethod = 'GET';
	}
	var httpParams = (params == null || params == '') ? null : params;
	var httpUrl = url;
	if (httpMethod == 'GET' && httpParams != null) {
		httpUrl = httpUrl + "?" + httpParams;
	}
	httpRequest.open(httpMethod, httpUrl, true);
	httpRequest.setRequestHeader(
		'Content-Type', 'application/x-www-form-urlencoded');
	httpRequest.onreadystatechange = callback;
	httpRequest.send(httpMethod == 'POST' ? httpParams : null);
}

//Email check

function emailCheckAjax() {
	var lastKeywordEmail = '';
	var keywordEmail = document.getElementById("userEmail").value;
	var listView = document.getElementById('emailCheckResult');

	if (keywordEmail == '') {
		lastKeywordEmail = '';
		listView.innerHTML = "이메일을 입력해주세요.";
		listView.style.color = "red";
	} else if (keywordEmail != lastKeywordEmail) {
		lastKeywordEmail = keywordEmail;
		if (keywordEmail != '' ) {
			var params = "userEmail=" + encodeURIComponent(keywordEmail);
			sendRequest("../memberentry/emailCheck.html", params,
					displayLoginResult, 'POST');
		} else {
		}
	}
}

function displayLoginResult() {
	if (httpRequest.readyState == 4) {
		if (httpRequest.status == 200) {
			var resultText = httpRequest.responseText;
			var listView = document.getElementById('emailCheckResult');
			alert(resultText);
			if (resultText == 0) {
				listView.innerHTML = "멋진 이메일입니다. 반갑습니다.";
				listView.style.color = "blue";
			} else {
				listView.innerHTML = "이미 가입한 이메일입니다. <a href='../login/login.html'>로그인으로 이동</a>";
				listView.style.color = "red";
			}
		} else {
			alert("에러 발생: " + httpRequest.status);
		}
	}
}