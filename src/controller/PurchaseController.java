package controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import logic.CartVo;
import logic.Shop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PurchaseController {

	@Autowired
	private Shop shopService;

	@RequestMapping
	public ModelAndView purchase(HttpSession session,String userEmail) {

		List<CartVo> cartVo = this.shopService.getCartList(userEmail);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("purchaseLine", cartVo);
		
	
		
		ModelAndView modelAndView = new ModelAndView("purchase/purchase");
		modelAndView.addAllObjects(model);

		return modelAndView;
	}
	
	

	
	
}
