package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;







import logic.CartVo;
import logic.MemberVo;
import logic.Shop;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import utils.WebConstants;

@Controller
public class CartController {
	@Autowired
	private Shop shopService;

	@RequestMapping(value = "/cart/cartAdd")
	public ModelAndView add(Integer itemNo, Integer price,
			Integer cartNumOfProduct, HttpSession session) {

		MemberVo userKey = (MemberVo) session
				.getAttribute(WebConstants.USER_KEY);

		String userEmail = userKey.getUserEmail();
		Integer cartSubTotal = price * cartNumOfProduct;

		CartVo cart = new CartVo();

		cart.setCartNumOfProduct(cartNumOfProduct);
		cart.setItemNo(itemNo);
		cart.setUserEmail(userEmail);
		cart.setCartSubTotal(cartSubTotal);

		this.shopService.entryCart(cart);

		List<CartVo> cartVo = this.shopService.getCartList(userEmail);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("cart", cartVo);

		ModelAndView modelAndView = new ModelAndView("cart/cart");

		modelAndView.addAllObjects(model);
		return modelAndView;

	}

	@RequestMapping(value = "/cart/cartClear")
	public ModelAndView clear(HttpSession session) {
		MemberVo userKey = (MemberVo) session
				.getAttribute(WebConstants.USER_KEY);
		String userEmail = userKey.getUserEmail();
		this.shopService.clearCart(userEmail);

		ModelAndView modelAndView = new ModelAndView("cart/cartClear");

		return modelAndView;
	}

}
