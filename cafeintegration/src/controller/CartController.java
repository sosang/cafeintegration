package controller;

import javax.servlet.http.HttpSession;

import logic.CartVo;
import logic.ItemVo;
import logic.Shop;
import logic.UseCart;

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

		UseCart cart = (UseCart) session.getAttribute("CART_KEY");
		if (cart == null) {
			cart = this.shopService.getCart();
			session.setAttribute("CART_KEY", cart);
		}
	
		Integer cartSubTotal = price * cartNumOfProduct;
		this.shopService.entryCart(new CartVo(selectedItem, cartNumOfProduct,
				userEmail, cartSubTotal));
		cart.push(new CartVo(selectedItem, cartNumOfProduct));

		ModelAndView modelAndView = new ModelAndView("cart/cart");
		modelAndView.addObject("message", selectedItem.getItemName() + "을(를)"
				+ cartNumOfProduct + "개 카트에 추가했습니다.");
		modelAndView.addObject("cart", cart);
		return modelAndView;
	}

	@RequestMapping(value = "/cart/cartClear")
	public ModelAndView clear(HttpSession session,String userEmail) {

		UseCart cart = (UseCart) session.getAttribute("CART_KEY");
		if (cart == null) {
			cart = this.shopService.getCart();
			session.setAttribute("CART_KEY", cart);
		}
		cart.clearAll();
		this.shopService.clearCart(userEmail);

		ModelAndView modelAndView = new ModelAndView("cart/cart");
		modelAndView.addObject("message", "카트를 비웠습니다");

		return modelAndView;
	}

}
