package controller;

import javax.servlet.http.HttpSession;

import logic.MemberVo; 
import logic.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import utils.WebConstants;


@Controller
public class LoginController {

	@Autowired
	private Shop shopService;
	
	
	@Autowired
	private Validator loginValidator;

	@ModelAttribute
	public MemberVo setUpForm() { 
		return new MemberVo();

	}

	
	@RequestMapping("login/login")
	public ModelAndView onSubmit(MemberVo member,
			BindingResult bindingResult, HttpSession session) {

		this.loginValidator.validate(member, bindingResult);

		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;

		}

		try {
			//유저 정보 검색
			MemberVo loginMemberVo = this.shopService.getMemberByUserEmailAndUserPasswd(member.getUserEmail(), member.getUserPasswd());
			session.setAttribute(WebConstants.USER_KEY, loginMemberVo);
			//유저 확인시
			modelAndView.setViewName("login/loginSuccess");
			return modelAndView;
		} catch (EmptyResultDataAccessException e) {
			//유저 미 확인시
			bindingResult.reject("error.login.memberVo");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
		
	}
	
	
	@RequestMapping("login/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "login/logout";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}