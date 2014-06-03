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

	@RequestMapping
	public void postcode_test(){

	}

	@RequestMapping("postcode")
	public ModelAndView searchPostcode(HttpServletRequest request, String eupmyeondong) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String eup="";
		if(eupmyeondong == null){
			eup="동을 입력하세요";
		}else{
			try {
				//charset 바꾸기
				//8859_1 -> utf-8
				eup = new String(eupmyeondong.getBytes("8859_1"),"UTF-8"); 
			} catch (UnsupportedEncodingException e) {
				 eup = eupmyeondong;//깨진한글 되돌려주기
			}
			System.out.println(eup);
		}
		//입력한 '읍/면/동' 값으로 Postcode List 취득
		List<Postcode> postcode = this.postcodeCatalog.getPostcodeByEupmyeondong(eup);

		//모델 생성
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("postcode", postcode);

		//반환값인 ModelAndView 인스턴스 생성
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addAllObjects(model);
		return modelAndView;
	}
}