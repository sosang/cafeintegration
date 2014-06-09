package controller;

import java.util.HashMap;




import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import logic.ItemVo;
import logic.Shop;







import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import utils.WebConstants;


@Controller
public class ItemController  {
	@Autowired
	private Shop shopService;


	@RequestMapping
	public ModelAndView item(HttpSession session) {
		// TODO Auto-generated method stub
		
		
		List<ItemVo> itemList = this.shopService.getItemList();
		
		Map<String, Object> model = new HashMap<String,Object>();

		
		model.put("itemList", itemList);
		
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addAllObjects(model);
		return modelAndView;
	}

}
