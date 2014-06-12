$(function(){

	// 모든 패널이 펼쳐져있는 상태이기 때문에 dd의 첫번재를 제외한곳은 안보이게 설정합니다.
	$("dd").css("display","none");

	//dl 의 dt 를 클릭했을때
	$("dl dt").click(function(){

		$("dd").slideUp("slow");
		// 만약 클릭한 태그 다음에 있는 dd 태그의 속성이 none 이면
		if($("+dd",this).css("display")=="none"){

			// dd 태그에 대해서 슬라이드업을 합니다. 즉 패널이 닫히는겁니다.
			$("dd").slideUp("slow");

			// 이어서 이접한 dd 에 슬라이드 다운을 합니다.
			$("+dd",this).slideDown("slow");
		}
	});
});
