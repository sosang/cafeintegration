package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AboutUsController {

	
	@RequestMapping("aboutUs/aboutUs")
	public String aboutUs(){
	
	return "aboutUs/aboutUs";
	}
	
}
