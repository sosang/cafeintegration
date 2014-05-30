//비밀번호 확인 이벤트(jQuery사용)
$(document).ready(function () {
	   $("#confirmPasswd").keyup(checkPasswordMatch);
	});


function checkPasswordMatch() {
    var password1 = $("#userPasswd").val();
    var password2 = $("#confirmPasswd").val();

    if (password1 == password2)
        $("#validate").html("<font color='blue'>비밀번호 일치</font>");
    else
        $("#validate").html("<font color='red'>비밀번호 불일치</font>");
}
