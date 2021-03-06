package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import logic.CartVo;
import logic.ItemVo;
import logic.MemberVo;
import logic.PurchaseListVo;
import logic.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import utils.WebConstants;

@Controller
public class PurchaseController {

	@Autowired
	private Shop shopService;

	@RequestMapping(value = "/purchase/purchaseCart")
	public ModelAndView purchase(HttpSession session) {
		
		MemberVo userKey = (MemberVo) session
				.getAttribute(WebConstants.USER_KEY);
		String userEmail = userKey.getUserEmail();
		
		List<CartVo> cartVo = this.shopService.getCartList(userEmail);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("purchaseLine", cartVo);

		ModelAndView modelAndView = new ModelAndView("purchase/purchase");
		modelAndView.addAllObjects(model);

		return modelAndView;
	}

	@RequestMapping(value = "/purchase/purchaseDirect")
	public ModelAndView purchasedirect(Integer itemNo, Integer price,
			Integer cartNumOfProduct,  HttpSession session) {
		ItemVo item = this.shopService.getItemByItemNo(itemNo);

		PurchaseListVo purchaseList = new PurchaseListVo();
		purchaseList.setItemName(item.getItemName());
		purchaseList.setNumOfProduct(cartNumOfProduct);
		purchaseList.setPrice(price);

		ModelAndView modelAndView = new ModelAndView("purchase/purchaseDirect");
		modelAndView.addObject("purchaseLine", purchaseList);

		return modelAndView;
	}

}