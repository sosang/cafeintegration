package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import logic.AdminVoService;
import logic.ItemVo;
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
public class ItemloginController {
	@Autowired
	private Shop shopService;


	@Autowired
	private Validator loginValidator;

	@Autowired
	private AdminVoService adminVoService;

	@ModelAttribute
	public MemberVo setUpForm() { 
		return new MemberVo();

	}
	@RequestMapping(value="itemlogin/itemlogin", method=RequestMethod.GET)
	public ModelAndView login(Integer itemNo){
		ItemVo item = this.shopService.getItemByItemNo(itemNo);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("item", item);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(model);
		modelAndView.setViewName("itemlogin/itemlogin");
		return modelAndView;
	}

	@RequestMapping(value="itemlogin/itemlogin", method=RequestMethod.POST)
	public ModelAndView onSubmit(MemberVo member,
			BindingResult bindingResult, HttpSession session,Integer itemNo) {

		this.loginValidator.validate(member, bindingResult);
		ItemVo item = this.shopService.getItemByItemNo(itemNo);

		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;

		}

		try {
			//유저 정보 검색
			MemberVo loginMemberVo = this.shopService.getMemberByUserEmailAndUserPasswd(member.getUserEmail(), member.getUserPasswd());
			session.setAttribute(WebConstants.USER_KEY, loginMemberVo);
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("item", item);
			modelAndView.addAllObjects(model);
			//유저 확인시
			modelAndView.setViewName("detail/detailon");
			return modelAndView;
		} catch (EmptyResultDataAccessException e) {
			//유저 미 확인시
			bindingResult.reject("error.login.memberVo");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}

	}

}