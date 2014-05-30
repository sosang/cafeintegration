package controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;




import logic.CartVo;

import logic.PurchaseListVo;
import logic.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MypageController {

	@Autowired
	private Shop shopService;

	@RequestMapping(value = "/mypage/mypage")
	public ModelAndView mypage(HttpSession session, String userEmail) {

		List<PurchaseListVo> myPurchase = this.shopService
				.mypagePurchase(userEmail);



		List<CartVo> myCart = this.shopService.getCartList(userEmail);

		

		Map<String, Object> model = new HashMap<String, Object>();
	
		Map<String, Object> model2 = new HashMap<String, Object>();

		model.put("mycart", myCart);

		model2.put("mypurchase", myPurchase);
		ModelAndView modelAndView = new ModelAndView("mypage/mypage");

		modelAndView.addAllObjects(model);

		modelAndView.addAllObjects(model2);

		return modelAndView;

	}

	@RequestMapping(value = "/mypage/mypageDelete")
	public ModelAndView mypagedelete(HttpSession session, String[] checkCart) {

		for (int i = 0; i < checkCart.length; i++) {
			Integer a = Integer.parseInt(checkCart[i]);
			System.out.println(a);
			this.shopService.mypageCartclear(a);
		}

		ModelAndView modelAndView = new ModelAndView("mypage/mypage");

		return modelAndView;

	}

	// List<ItemVo> item = new ArrayList<ItemVo>();
	// for (int i = 0; i < myCart.size(); i++) {
	// item.add(this.shopService
	// .getItemByItemNo(myCart.get(i).getItemNo()));
	// }

	// List<CartVo> myCart = this.shopSerivce.getCartList(userEmail);
}
