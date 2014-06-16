$(document).ready(function () {
		/*	비밀번호 확인 이벤트(jQuery사용)*/
	   $("#confirmPasswd").onkeyup(checkPasswordMatch);

	});

//비밀번호 활성화
function checkPasswordMatch() {
    var password1 = $("#userPasswd").val();
    var password2 = $("#confirmPasswd").val();
    if (password1 == password2){
        $("#validate").html("<font color='blue'>비밀번호 일치</font>");
    	$("#passwdInquiry").focus();
    }else{
        $("#validate").html("<font color='red'>비밀번호 불일치</font>");
    }
}

//비밀번호 확인 및 동일성 확인시 다음 input으로 focus 자동이동
function moveFocus(){

	var phone2 =$("#userPhone2").val();
	var phone3 =$("#userPhone3").val();
	if(phone2.length == maxlength){
		$("#userPhone3").focus;
	}else if(phone3.length == maxlength){
		$("#postcodeBtn").focus;
	}
}

/* ======== ajax 활용 이메일/별명 중복성 검사 ==========*/
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

// userEmailCheck java script
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
					displayEmailResult, 'POST');
		} else {
		}
	}
}

function displayEmailResult() {
	if (httpRequest.readyState == 4) {
		if (httpRequest.status == 200) {
			var resultText = httpRequest.responseText;
			var listView = document.getElementById('emailCheckResult');
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

//userAliasCheck java script
function aliasCheckAjax() {
	var lastKeywordAlias = '';
	var keywordAlias = document.getElementById("userAlias").value;
	var aliasView = document.getElementById('aliasCheckResult');

	if (keywordAlias == '') {
		lastKeywordAlias = '';
		aliasView.innerHTML = "별명을 입력해주세요.";
		aliasView.style.color = "red";
	} else if (keywordAlias != lastKeywordAlias) {
		lastKeywordAlias = keywordAlias;
		if (keywordAlias != '' ) {
			var params = "userAlias=" + encodeURIComponent(keywordAlias);
			sendRequest("../memberentry/aliasCheck.html", params,
					displayAliasResult, 'POST');
		} else {
		}
	}
}

function displayAliasResult() {
	if (httpRequest.readyState == 4) {
		if (httpRequest.status == 200) {
			var resultText = httpRequest.responseText;
			var aliasView = document.getElementById('aliasCheckResult');
			if (resultText == 0) {
				aliasView.innerHTML = "와~ 멋진데요? 맘껏 사용하셔도 되겠습니다.";
				aliasView.style.color = "blue";
			} else {
				aliasView.innerHTML = "<strong>아뿔싸, 누가 쓰고 있네요. 다른 멋진 이름을 찾아볼까요?</strong>";
				aliasView.style.color = "red";
			}
		} else {
			alert("에러 발생: " + httpRequest.status);
		}
	}
}

/*회원가입시 약관 동의한 경우 submit 버튼 활성화*/
	$("#terms_check").change(function () {
	   $("#EntryBtn").attr("disabled", !this.checked);
	});

/*회원 가입시 약관보기 modal*/

   	$("#terms_modal").clicked(function(){
	   	    $( "#dialog" ).dialog();
   	});
