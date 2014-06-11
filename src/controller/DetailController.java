package controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import logic.ItemVo;
import logic.MemberVo;
import logic.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import utils.WebConstants;

@Controller
public class DetailController {
	@Autowired
	private Shop shopService;



	@RequestMapping
	public ModelAndView detailItem(HttpSession session, Integer itemNo) {

		MemberVo userKey = (MemberVo) session
				.getAttribute(WebConstants.USER_KEY);
		String userEmail;
		try {
			userEmail = userKey.getUserEmail();
		} catch (Exception e) {
			userEmail = null;
		}
		ItemVo item = this.shopService.getItemByItemNo(itemNo);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("item", item);

		model.put("userEmail", userEmail);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addAllObjects(model);
		return modelAndView;
	}

	@RequestMapping("admin/detail")
	public ModelAndView adminDetailItem(Integer itemNo) {
		ItemVo item = this.shopService.getItemByItemNo(itemNo);
		String filePathForJsp = this.shopService.getFilePathTo(itemNo);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("item", item);
		model.put("toViewImage",filePathForJsp);
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addAllObjects(model);
		return modelAndView;
	}

}
