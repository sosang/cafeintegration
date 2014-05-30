package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import logic.CartVo;
import logic.Shop;
import logic.UseCart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PurchaseController {

	@Autowired
	private Shop shopService;

	@RequestMapping
	public ModelAndView purchase(HttpSession session) {

		UseCart cart = (UseCart) session.getAttribute("CART_KEY");

		List<CartVo> purchaseLine = cart.getCartList();
		
	
		
		ModelAndView modelAndView = new ModelAndView("purchase/purchase");
		modelAndView.addObject("purchaseLine", purchaseLine);

		return modelAndView;
	}
	
	

	
	
}
