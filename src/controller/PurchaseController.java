package controller;

import java.util.ArrayList;
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

		MemberVo user = this.shopService.memberInfo(userEmail);

		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> model2 = new HashMap<String, Object>();

		model.put("purchaseLine", cartVo);
		model2.put("myinfo", user);

		ModelAndView modelAndView = new ModelAndView("purchase/purchase");
		modelAndView.addAllObjects(model);
		modelAndView.addAllObjects(model2);

		return modelAndView;

	}

	@RequestMapping(value = "/purchase/purchaseDirect")
	public ModelAndView purchasedirect(Integer itemNo, Integer price,
			Integer cartNumOfProduct, HttpSession session) {

		MemberVo userKey = (MemberVo) session
				.getAttribute(WebConstants.USER_KEY);
		String userEmail = userKey.getUserEmail();
		MemberVo user = this.shopService.memberInfo(userEmail);
		Map<String, Object> model2 = new HashMap<String, Object>();
		model2.put("myinfo", user);
		ItemVo item = this.shopService.getItemByItemNo(itemNo);

		PurchaseListVo purchaseList = new PurchaseListVo();
		purchaseList.setItemName(item.getItemName());
		purchaseList.setNumOfProduct(cartNumOfProduct);
		purchaseList.setPrice(price);
		purchaseList.setPhoto(item.getPhoto());

		ModelAndView modelAndView = new ModelAndView("purchase/purchaseDirect");
		modelAndView.addObject("purchaseLine", purchaseList);
		modelAndView.addAllObjects(model2);
		return modelAndView;
	}

	@RequestMapping(value = "/purchase/purchaseDirect2")
	public ModelAndView purchasedirect2( String[] checkCart, HttpSession session) {

		MemberVo userKey = (MemberVo) session
				.getAttribute(WebConstants.USER_KEY);
		String userEmail = userKey.getUserEmail();
		
		MemberVo user = this.shopService.memberInfo(userEmail);
		List<CartVo> cartList = new ArrayList<CartVo>();
		try {
			for (int i = 0; i < checkCart.length; i++) {
				Integer a = Integer.parseInt(checkCart[i]);

				CartVo cartNo=this.shopService.myCartItem(userEmail, a);
				cartList.add(cartNo);
			}
		} catch (Exception e) {

		}

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("mycartitem", cartList);
		
		Map<String, Object> model2 = new HashMap<String, Object>();
		model2.put("myinfo", user);

		ModelAndView modelAndView = new ModelAndView("purchase/purchaseDirect2");
		modelAndView.addAllObjects(model);
		modelAndView.addAllObjects(model2);
		return modelAndView;
	}

}