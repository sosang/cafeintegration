package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import logic.AdminVo;
import logic.AdminVoService;
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
	
	@Autowired
	private Validator findPwdValidator;
	
	
	@Autowired
	private AdminVoService adminVoService;
	private String goBack="";

	@ModelAttribute
	public MemberVo setUpForm() { 
		return new MemberVo();

	}
	@RequestMapping(value="login/login", method=RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login/login");
		this.goBack = request.getHeader("referer");
		return modelAndView;
	}
	
	@RequestMapping(value="login/login", method=RequestMethod.POST)
	public ModelAndView onSubmit(MemberVo member,
			BindingResult bindingResult, HttpServletRequest request, HttpSession session) {

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
		
		
			//유저 확인시 로그인 전 페이지로 돌아간다.
//			String url = "redirect:../"+goBack.substring(33, goBack.length());
			//war용
			String url = "redirect:../"+goBack.substring(36, goBack.length());
			modelAndView.setViewName(url);
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
	
	@RequestMapping("admin/loginSuccess")
	public ModelAndView loginSuccess(HttpServletRequest request, AdminVo adminVo, BindingResult bindingResult){
		String adminEmail = adminVo.getAdminEmail();
		String adminPasswd = adminVo.getAdminPasswd();
		ModelAndView modelAndView = new ModelAndView();
		
		try {
			//유저 정보 검색
			AdminVo loginAdminVo = adminVoService.getAdminInfo(adminEmail, adminPasswd);
			request.getSession().setAttribute("ADMIN_KEY", loginAdminVo);
			//유저 확인시
			modelAndView.setViewName("admin/index");
			return modelAndView;
		} catch (EmptyResultDataAccessException e) {
			//유저 미 확인시
			bindingResult.reject("error.login.memberVo");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
	}
	
	@RequestMapping(value="findPassword", method = RequestMethod.GET)
	public ModelAndView findPassword(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login/findPassword");
		return modelAndView;
	}
	
	@RequestMapping(value="findPassword", method = RequestMethod.POST)
	public ModelAndView findPasswordSubmit(MemberVo memberVo, BindingResult bindingResult, HttpServletRequest request) {
		
		this.findPwdValidator.validate(memberVo, bindingResult);
		
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;

		}

		try {
			//유저 정보 검색
			MemberVo findMemberVo = this.shopService.lookingForPwdByInquiry(memberVo.getUserEmail(), memberVo.getPasswdInquiry(), memberVo.getPasswdAnswer());
			request.getSession().setAttribute(WebConstants.USER_KEY, findMemberVo);
		
		
			//유저 확인시 로그인 전 페이지로 돌아간다.
//			String url = "redirect:../"+goBack.substring(33, goBack.length());
			//war용
			String url = "redirect:../"+goBack.substring(38, goBack.length());
			modelAndView.setViewName(url);
			return modelAndView;
		} catch (EmptyResultDataAccessException e) {
			//유저 미 확인시
			bindingResult.reject("error.login.findMemberVo");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}

	}
}