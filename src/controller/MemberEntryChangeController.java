package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import logic.MemberVo;
import logic.PostcodeCatalog;
import logic.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import utils.MemberEntryValidator;
import utils.WebConstants;

@Controller
public class MemberEntryChangeController {

	@Autowired
	private Shop shopService;

	@Autowired
	private PostcodeCatalog postcodeCatalog;

	@Autowired
	private MemberEntryValidator memberEntryValidator;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView toMemberEntryView(HttpSession session) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("memberentrychange/memberEntryChange");
		MemberVo userKey = (MemberVo) session
				.getAttribute(WebConstants.USER_KEY);
		String userEmail = userKey.getUserEmail();
		MemberVo user = this.shopService.memberInfo(userEmail);
		Map<String, Object> model2 = new HashMap<String, Object>();
		model2.put("myinfo", user);
		modelAndView.addAllObjects(model2);
		return modelAndView;
	}

	@ModelAttribute
	public MemberVo setUpForm() {

		return new MemberVo();
	}

	@RequestMapping(value = "emailCheckChange")
	public ModelAndView emailCheck(ModelAndView modelAndView,
			@RequestParam String userEmail) {

		int result = this.shopService.getCheckedUserEmail(userEmail);

		modelAndView.setViewName("memberentrychange/emailCheckChange");
		modelAndView.addObject("result", result);

		return modelAndView;
	}

	@RequestMapping(value = "aliasCheckChange")
	public ModelAndView aliasCheck(ModelAndView modelAndView,
			@RequestParam String userAlias) {

		int result = this.shopService.getCheckedUserAlias(userAlias);

		modelAndView.setViewName("memberentrychange/aliasCheckChange");
		modelAndView.addObject("result", result);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(MemberVo member, BindingResult bindingResult,
			HttpSession session) throws Exception {

		ModelAndView modelAndView = new ModelAndView();

		

		if (bindingResult.hasErrors()) {
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;

		}

		try {
			MemberVo userKey = (MemberVo) session
					.getAttribute(WebConstants.USER_KEY);
			String userEmail = userKey.getUserEmail();
			String userAlias = userKey.getUserAlias();
			member.setUserEmail(userEmail);
			member.setUserAlias(userAlias);
			this.shopService.updateMember(member, userEmail);
			session.setAttribute(WebConstants.USER_KEY, member);
			/*
			 * if(this.shopService.getCart()==null){
			 * session.setAttribute(WebConstants.CART_KEY,
			 * this.shopService.getCart()); }
			 */
			modelAndView.setViewName("memberentrychange/memberEntryChangeSuccess");
			modelAndView.addObject("member", member);
			return modelAndView;

		} catch (DataIntegrityViolationException e) {
			// 유저 Email 중복 시, 폼을 송신한 곳으로 이동
			bindingResult.reject("error.duplicate.memberVo");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
	}

}