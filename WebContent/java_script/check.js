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