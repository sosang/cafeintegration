package controller;

import javax.servlet.http.HttpSession;



import logic.MemberVo;
import logic.Shop;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import utils.WebConstants;

@Controller
public class EndController {

	@Autowired
	private Shop shopService;

	@RequestMapping
	public ModelAndView end(HttpSession session,
			String receiver, String recphone, String recaddr,
			String recpostcode, String remarks) {
		MemberVo userKey = (MemberVo) session
				.getAttribute(WebConstants.USER_KEY);
		String userEmail = userKey.getUserEmail();

		this.shopService.checkout(userEmail,receiver,recphone,recaddr,recpostcode,remarks);

		ModelAndView modelAndView = new ModelAndView();

		return modelAndView;
	}

}
