package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {
	
	
	@RequestMapping("index/index")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
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
