package controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.ItemVo;
import logic.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {
	
	@Autowired
	private Shop shopService;
	
	@RequestMapping("index/index")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		List<ItemVo> itemList = this.shopService.getItemList();
		Map<String, Object> model = new HashMap<String,Object>();
		model.put("itemList", itemList);
		modelAndView.addAllObjects(model);
		return modelAndView;
	}

	@RequestMapping("admin/*")
	public String adIndex(){
		return "admin/login";
	}
	@RequestMapping("ckfinder/ckfinder")
	public String ckfinder(){
		return "../../ckfinder/ckfinder";
	}
	
	
}
