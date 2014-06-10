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
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value="login", method=RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login/login");
		return modelAndView;
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public ModelAndView onSubmit(MemberVo member,
			BindingResult bindingResult, HttpSession session) {

		this.loginValidator.validate(member, bindingResult);

		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;

		}

		try {

			MemberVo loginMemberVo = this.shopService.getMemberByUserEmailAndUserPasswd(member.getUserEmail(), member.getUserPasswd());
			session.setAttribute(WebConstants.USER_KEY, loginMemberVo);

			modelAndView.setViewName("index/index");
			return modelAndView;
			
		} catch (EmptyResultDataAccessException e) {

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