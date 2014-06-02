package utils;

import logic.MemberVo;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MemberEntryValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MemberVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		// TODO Auto-generated method stub

		MemberVo member = (MemberVo) command;

		if(!StringUtils.hasLength(member.getUserEmail())){
			errors.rejectValue("userEmail", "error.required");
		}
		if(!StringUtils.hasLength(member.getUserPasswd())){
			errors.rejectValue("userPasswd", "error.required");
		}
		if(!StringUtils.hasLength(member.getUserAlias())){
			errors.rejectValue("userAlias", "error.required");
		}
		if(!StringUtils.hasText(member.getUserAddress2())){
			errors.rejectValue("userAddress2", "error.required");
		}
		if(errors.hasErrors()){
			errors.reject("error.input.member");
		}
	}

}
