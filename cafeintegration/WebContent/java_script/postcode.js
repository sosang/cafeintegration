/*====우편번호 창열기=====*/
function openwin(){
//	window.open("../postcode/postcode.html", "", "width=400, height=250, resizable=no");
	window.open("../postcode/postcode.html", "", "width=400, height=250, resizable=no");
}

/*=====우편번호 값 전달=====*/
function filladd(zip, addr){
	alert(zip);
	alert(addr);
	opener.document.memberVo.userPostcode.value=zip;
	opener.document.memberVo.userAddress1.value=addr;
	this.close();
}