package controller;

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

import utils.WebConstants;


@Controller
public class MemberEntryController {

	@Autowired
	private Shop shopService;

	@Autowired
	private PostcodeCatalog postcodeCatalog;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method=RequestMethod.GET)
	public String toMemberEntryView(){
		return "memberentry/memberEntry";
	}

	@ModelAttribute
	public MemberVo setUpForm(){

		return new MemberVo();
	}
	
	@RequestMapping(value="checkEmail")
	public ModelAndView checkEmail(@RequestParam String userEmail){
		System.out.println(userEmail);
		int res = this.shopService.getCheckedUserEmail(userEmail);
		ModelAndView mav = new ModelAndView();
		if(res != 0){
			mav.addObject("isMember", res);
		}
		return mav;
	}

	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView onSubmit(MemberVo member, BindingResult bindingResult, HttpSession session) throws Exception{

		ModelAndView modelAndView = new ModelAndView();

		if(bindingResult.hasErrors()){
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;			

		}

			try{
			this.shopService.entryMember(member);
			session.setAttribute(WebConstants.USER_KEY, member);
			/*if(this.shopService.getCart()==null){
				session.setAttribute(WebConstants.CART_KEY, this.shopService.getCart());
			}*/
			modelAndView.setViewName("memberentry/memberEntrySuccess");
			modelAndView.addObject("member", member);
			return modelAndView;

		}
		catch(DataIntegrityViolationException e){

			bindingResult.reject("error.duplicate.memberVo");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
	}

}