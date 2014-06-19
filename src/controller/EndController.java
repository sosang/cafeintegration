package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
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
	public ModelAndView end(HttpSession session, String receiver,
			String recphone, String recaddr, String recpostcode,
			String remarks, HttpServletResponse response) throws IOException  {
		MemberVo userKey = (MemberVo) session
				.getAttribute(WebConstants.USER_KEY);
		String userEmail = userKey.getUserEmail();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		this.shopService.checkout(userEmail, receiver, recphone, recaddr,
				recpostcode, remarks);
		PrintWriter writer = response.getWriter();
		writer.println("<script type='text/javascript'>");
		writer.println("alert('상품 구매를 완료하셨습니다.');");
		writer.println("</script>");
		writer.flush();
		ModelAndView modelAndView = new ModelAndView("index/index");

		return modelAndView;

	}

}