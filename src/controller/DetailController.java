package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;





import logic.ItemVo;
import logic.NumNo;
import logic.Shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DetailController {
	@Autowired
	private Shop shopService;



	@RequestMapping
	public ModelAndView detailItem(Integer itemNo) {
		ItemVo item = this.shopService.getItemByItemNo(itemNo);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("item", item);
		ModelAndView modelAndView = new ModelAndView();

		List<NumNo> numNo = this.shopService.getNum();
		Map<String, Object> model1 = new HashMap<String, Object>();
		model1.put("numNo",numNo);
		modelAndView.addAllObjects(model);
		modelAndView.addAllObjects(model1);
		return modelAndView;
	}

}
