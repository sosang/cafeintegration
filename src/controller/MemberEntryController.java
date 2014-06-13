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
public class MemberEntryController {

	@Autowired
	private Shop shopService;

	@Autowired
	private PostcodeCatalog postcodeCatalog;
	
	@Autowired
  	private MemberEntryValidator memberEntryValidator;
	
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
	
	@RequestMapping(value="emailCheck")
	public ModelAndView emailCheck(ModelAndView modelAndView, @RequestParam String userEmail ){
			
		int result = this.shopService.getCheckedUserEmail(userEmail);
	
		modelAndView.setViewName("memberentry/emailCheck");
		modelAndView.addObject("result", result);
		 		  		
		return modelAndView;
	}
	

	@RequestMapping(value="aliasCheck")
	public ModelAndView aliasCheck(ModelAndView modelAndView, @RequestParam String userAlias ){
 		
 		int result = this.shopService.getCheckedUserAlias(userAlias);
 	
 		modelAndView.setViewName("memberentry/aliasCheck");
 		modelAndView.addObject("result", result);
 		
 		return modelAndView;
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
			//유저 Email 중복 시, 폼을 송신한 곳으로 이동
			bindingResult.reject("error.duplicate.memberVo");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
	} 

	// 어드민에서 가입자 목록 보기
	// 가입자 목록 보기
	@RequestMapping("admin/member")
	public ModelAndView member(HttpServletRequest request, Integer pageNo) throws Throwable{
		// 가입자 목록 취득
		List<MemberVo> memberList = null;
		memberList = this.shopService.getMemberList(request, pageNo);
		// 모델 생성
		Map<String, Object> model = new HashMap<String,Object>();
		model.put("memberList", memberList);

		// 반환값인 ModelAndView 인스턴스 생성
		ModelAndView modelAndView = new ModelAndView("admin/member");
		modelAndView.addAllObjects(model);

		return modelAndView;
	}
	
	
}