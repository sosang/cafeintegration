//비밀번호 확인 이벤트(jQuery사용)
$(document)
		.ready(
				function() {
//					/* 이메일 중복 체크 */
//					$("#emailCheck")
//							.click(
//									function checkEmailDuplication() {
//										var param = "userEmail= "
//												+ $("#userEmail").val();
//										$
//												.ajax({
//													url : "checkEmail",
//													type : "GET",
//													data : param,
//													cache : false,
//													dataType : "text",
//													success : function(response) {
//														if (response == 0) {
//															$("#emailCheckResult")
//																	.html(
//																			"<font color = 'blue'>우리 사이트에 딱 어울리는 반가운 이메일입니다</font>");
//														} else {
//															$("#emailCheckResult")
//																	.html(
//																			"<font color = 'blue'>이미 가입하신 것 같습니다. <a href='../login/login.html'>로그인으로 이동</a></font>");
//														}
//													}
//												});
//									});

					/* 비밀번호 확인 */
					$("#confirmPasswd")
							.keyup(
									function checkPasswordMatch() {
										var password1 = $("#userPasswd").val();
										var password2 = $("#confirmPasswd")
												.val();
										if (password1 == password2)
											$("#validate")
													.html(
															"<font color='blue'>비밀번호 일치</font>");
										else
											$("#validate")
													.html(
															"<font color='red'>비밀번호 불일치</font>");
									});
				});
