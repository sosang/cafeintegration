package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import logic.CartVo;
import logic.MemberVo;
import logic.PurchaseListVo;
import logic.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import utils.WebConstants;

@Controller
public class MypageController {

	@Autowired
	private Shop shopService;

	@RequestMapping(value = "/mypage/mypage")
	public ModelAndView mypage(HttpSession session) {
		MemberVo userKey = (MemberVo) session
				.getAttribute(WebConstants.USER_KEY);
		String userEmail = userKey.getUserEmail();
		String userPasswd = userKey.getUserPasswd();

		List<PurchaseListVo> myPurchase = this.shopService
				.mypagePurchase(userEmail);

		MemberVo user = this.shopService.getMemberByUserEmailAndUserPasswd(
				userEmail, userPasswd);

		List<CartVo> myCart = this.shopService.getCartList(userEmail);

		Map<String, Object> model = new HashMap<String, Object>();

		Map<String, Object> model2 = new HashMap<String, Object>();

		Map<String, Object> model3 = new HashMap<String, Object>();

		model3.put("user", user);

		model.put("mycart", myCart);

		model2.put("mypurchase", myPurchase);

		ModelAndView modelAndView = new ModelAndView("mypage/mypage");

		modelAndView.addAllObjects(model);

		modelAndView.addAllObjects(model2);

		modelAndView.addAllObjects(model3);
		return modelAndView;

	}

	@RequestMapping(value = "/mypage/mypageDelete")
	public ModelAndView mypagedelete(HttpSession session, String[] checkCart) {
		try {
			for (int i = 0; i < checkCart.length; i++) {
				Integer a = Integer.parseInt(checkCart[i]);

				this.shopService.mypageCartclear(a);
			}
		} catch (Exception e) {

		}
		ModelAndView modelAndView = new ModelAndView("mypage/mypage");

		return modelAndView;

	}

}
