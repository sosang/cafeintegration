package controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;





import logic.CartVo;
import logic.ItemVo;
import logic.Shop;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartController {
	@Autowired
	private Shop shopService;

	@RequestMapping(value = "/cart/cartAdd")
	public ModelAndView add(Integer itemNo, Integer price,
			Integer cartNumOfProduct, String userEmail, HttpSession session) {
		ItemVo selectedItem = this.shopService.getItemByItemNo(itemNo);
		
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
		modelAndView.addObject("message", selectedItem.getItemName() + "을(를)"
				+ cartNumOfProduct + "개 카트에 추가했습니다.");
		modelAndView.addAllObjects(model);
		return modelAndView;
	}

	@RequestMapping(value = "/cart/cartClear")
	public ModelAndView clear(HttpSession session, String userEmail) {

		this.shopService.clearCart(userEmail);

		ModelAndView modelAndView = new ModelAndView("cart/cart");
		modelAndView.addObject("message", "카트를 비웠습니다");

		return modelAndView;
	}
	// this.shopService.entryCart(new CartItem(selectedItem,
	// cartNumOfProduct,
	// userEmail, cartSubTotal));

}
