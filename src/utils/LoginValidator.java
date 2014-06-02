package utils;

import logic.MemberVo;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MemberVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		// TODO Auto-generated method stub
		MemberVo memberVo = (MemberVo) command;
		if(!StringUtils.hasLength(memberVo.getUserEmail())){
			errors.rejectValue("userEmail","error.required");
		}
		if(!StringUtils.hasLength(memberVo.getUserPasswd())){
			errors.rejectValue("userPasswd","error.required");
		}
		if(errors.hasErrors()){
			errors.reject("error.input.memberVo");
		}


	}

}
