$(document).ready(function() {
    $('input:radio[name=r_terms]').change(function() {
        if (this.value == 'v_agree') {
            $('#terms_span').html("<a href='../memberentry/memberEntry.html' class='btn btn-info member-button'>다음으로</a>");
        }
        else if (this.value == 'v_disagree') {
        	$('#terms_span').html("동의하시지 않으면 회원으로 가입하실 수 없습니다. &nbsp; <a href='../index/index.html' id='terms_home' class='btn btn-default'>홈으로</a>");
        }
    });
});