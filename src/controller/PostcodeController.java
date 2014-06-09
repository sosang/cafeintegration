package controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import logic.Postcode;
import logic.PostcodeCatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping
public class PostcodeController {

	@Autowired
	private PostcodeCatalog postcodeCatalog;
	
		@RequestMapping(value="postcode")
	public ModelAndView searchPostcode(HttpServletRequest request, String eupmyeondong) throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		//입력한 '읍/면/동' 값으로 Postcode List 취득
		
		List<Postcode> postcode = this.postcodeCatalog.getPostcodeByEupmyeondong(eupmyeondong);
			

		//모델 생성
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("postcode", postcode);

		//반환값인 ModelAndView 인스턴스 생성
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(model);
		return modelAndView;
	}
}