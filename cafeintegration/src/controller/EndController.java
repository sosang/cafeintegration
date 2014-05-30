package controller;

import javax.servlet.http.HttpSession;

import logic.Shop;
import logic.UseCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EndController {

	@Autowired
	private Shop shopService;

	@RequestMapping
	public ModelAndView end(HttpSession session, String userEmail,
			String receiver, String recphone, String recaddr,
			String recpostcode, String remarks) {

		UseCart cart = (UseCart) session.getAttribute("CART_KEY");

		this.shopService.checkout(cart, userEmail,receiver,recphone,recaddr,recpostcode,remarks);

		ModelAndView modelAndView = new ModelAndView();

		return modelAndView;
	}

}
