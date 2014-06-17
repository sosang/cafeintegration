package utils;

import logic.MemberVo;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FindPwdValidator implements Validator {
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return MemberVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object command, Errors errors) {
		// TODO Auto-generated method stub
		if(errors.hasErrors()){
			errors.reject("error.input.memberVo");
		}


	}

}
