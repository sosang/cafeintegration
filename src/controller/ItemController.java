package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;


import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import logic.ItemVo;
import logic.SaveFilePathTo;
import logic.Shop;











import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ItemController  {
	@Autowired
	private Shop shopService;

	private FileOutputStream fos;



	@RequestMapping
	public ModelAndView item(HttpSession session) {
		// TODO Auto-generated method stub

		List<ItemVo> itemList = this.shopService.getItemList();

		Map<String, Object> model = new HashMap<String,Object>();
		
		
		
		List<SaveFilePathTo> photo = this.shopService.getitemAll_photo();
		
		Map<String , Object>model2 = new HashMap<String,Object>();

		model.put("itemList", itemList);
		model2.put("toViewImage", photo);
		
		System.out.println(photo.get(0).getFilePath());
		System.out.println(photo.get(0).getSaveFilePathNo());
		
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addAllObjects(model2);
		modelAndView.addAllObjects(model);
		return modelAndView;
	}

	// 아이템 목록 불러오기
	@RequestMapping("admin/itemList")
	public ModelAndView itemList(HttpSession session) {
		// TODO Auto-generated method stub
		List<ItemVo> itemList = this.shopService.getItemList();

		Map<String, Object> model = new HashMap<String,Object>();


		model.put("itemList", itemList);

		ModelAndView modelAndView = new ModelAndView("admin/itemList");

		modelAndView.addAllObjects(model);
		return modelAndView;
	}

	// 아이템 추가 객체 생성(제한 : 로그인 한 자)
	@RequestMapping("/admin/itemRegBefore")
	public ModelAndView itemRegBefore(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("admin/itemReg");
		modelAndView.addObject(new ItemVo());
		modelAndView.addObject(new SaveFilePathTo());
		return modelAndView;
	}

//	// 후기게시판 파일 저장을 위한 페이지 생성
	@RequestMapping("/admin/itemReg")
	public ModelAndView boardReviewsWrite(@Valid ItemVo itemVo, BindingResult bindingResult, 
			HttpServletRequest request, @RequestParam("filePath")MultipartFile filePath) throws Exception{
		String originFileName = filePath.getOriginalFilename();
		String forDb = "";
		String extention = originFileName.substring(originFileName.lastIndexOf(".")+1, originFileName.length());
		if(extention.equals("jpg")||extention.equals("gif")||extention.equals("bmp")||extention.equals("png")||extention.equals("tif")||extention.equals("tiff")||extention.equals("jpeg")||extention.equals("jpe")||extention.equals("jfif")||extention.equals("dib")||extention == ""){
			//디비 저장용
			forDb = "../img/"+originFileName;
		}else{
			ModelAndView modelAndView = new ModelAndView("admin/itemReg");
			return modelAndView;
		}

		// WAR파일로 뿌렷을 때
		String uploadPath = "C:/Tomcat 7/webapps/cafeintegration/img";

		// 시작
		File saveDir = new File(uploadPath);
		if(!saveDir.exists()) saveDir.mkdirs();
		// @valid 때문인데. 글쓰는 곳에서 설정해줬으면 되는거였다.
		if(bindingResult.hasErrors()){
			ModelAndView modelAndView = new ModelAndView("admin/itemReg");
			modelAndView.getModel().putAll(bindingResult.getModel());
			// 여기부터 바인딩 에러 내용보기!
			Map<String, Object> map = bindingResult.getModel();
			Set<String> keys = map.keySet();
			Iterator<String> it = keys.iterator();
			while(it.hasNext()) {
			Object key = it.next();
			Object val = map.get(key);
			System.out.println("에러내용 :: "+val);
			}
			// 여기까지 바인딩 에러 내용보기!
			return modelAndView;
		}
		// 내용을 사빕한다.
		this.shopService.itemReg(itemVo, forDb);
		// 업로드 파일 저장
		writeFile(filePath, uploadPath, originFileName);
		// 파일경로 쓰기
//		int newBdNoRev = this.boardReviewsService.getRecentNo();
//		this.boardReviewsService.setFilePath(newBdNoRev,forDb);
		int newItemNo = this.shopService.getNewItemNo();
		this.shopService.setFilePath(newItemNo, forDb);

		return new ModelAndView("redirect:itemList.html");	// 상품 등록 후 리스트로 돌아감
	}
	// 파일을 쓰기위한 메소드
	 public void writeFile(MultipartFile filePath, String path, String fileName){
         
	        try{
	            byte fileData[] = filePath.getBytes();
	            fos = new FileOutputStream(path + "\\" + fileName);
	            fos.write(fileData);
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            if(fos != null){
	                try{
	                    fos.close();
	                }catch(Exception e){}
	                }
	        }// try end;
	    }// wirteFile() end;

	// 상품 내용 수정을 위해 상품번호로 조회 후 객체 저장
	@RequestMapping("admin/itemEdit")
	public ModelAndView itemEdit(Integer itemNo){
		ModelAndView modelAndView = new ModelAndView("admin/itemUpdate");
		ItemVo itemVo = this.shopService.getItemByItemNo(itemNo);
		// 모델 생성
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("item", itemVo);
		modelAndView.addAllObjects(model);
		return modelAndView;
	}

	// 읽어온 상품의 내용을 수정한다.
	@RequestMapping(value="admin/itemUpdate")
	public ModelAndView itemUpdate(@Valid ItemVo itemVo, BindingResult bindingResult, HttpServletRequest request, Integer itemNo, @RequestParam("filePath")MultipartFile filePath){
		if(bindingResult.hasErrors()){
			ModelAndView modelAndView = new ModelAndView("admin/itemUpdate");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
		// save_file_path를 itemVo.getItemNo를 이용해 불러옴
		// 새로 파일을 업로드 한경우 경로를 갱신
		// 그렇지 않을 경우 기존 경로를 사용
		if(filePath.isEmpty()){
			this.shopService.itemUpdate(itemVo, itemNo);
			String url="redirect:itemList.html";
			return new ModelAndView(url);	// 글을 쓰고 목록 첫 페이지로 돌아감
		}else{
			String originFileName = filePath.getOriginalFilename();
			String extention = originFileName.substring(originFileName.lastIndexOf(".")+1, originFileName.length());
			if(extention.equals("jpg")||extention.equals("gif")||extention.equals("bmp")||extention.equals("png")||extention.equals("tif")||extention.equals("tiff")||extention.equals("jpeg")||extention.equals("jpe")||extention.equals("jfif")||extention.equals("dib")||extention == ""){
			}else{
				ModelAndView modelAndView = new ModelAndView("admin/itemList");
				return modelAndView;
			}

			// WAR파일로 뿌렷을 때
			String uploadPath = "C:/Tomcat 7/webapps/cafeintegration/img";
			// DB저장하기
			String forDb = "../img/"+originFileName;

			// 시작
			File saveDir = new File(uploadPath);
			if(!saveDir.exists()) saveDir.mkdirs();
			// @valid 때문인데. 글쓰는 곳에서 설정해줬으면 되는거였다.
			if(bindingResult.hasErrors()){
				ModelAndView modelAndView = new ModelAndView("admin/itemReg");
				modelAndView.getModel().putAll(bindingResult.getModel());
				// 여기부터 바인딩 에러 내용보기!
				Map<String, Object> map = bindingResult.getModel();
				Set<String> keys = map.keySet();
				Iterator<String> it = keys.iterator();
				while(it.hasNext()) {
				Object key = it.next();
				Object val = map.get(key);
				System.out.println("에러내용 :: "+val);
				}
				// 여기까지 바인딩 에러 내용보기!
				return modelAndView;
			}
			// 내용을 사빕한다.
			this.shopService.itemUpdate(itemVo, itemNo);
			// 업로드 파일 저장
			writeFile(filePath, uploadPath, originFileName);
			// 파일경로 쓰기
//			int newBdNoRev = this.boardReviewsService.getRecentNo();
//			this.boardReviewsService.setFilePath(newBdNoRev,forDb);
			this.shopService.updateFilePath(itemVo.getItemNo(), forDb);

			String url="redirect:itemList.html";
			return new ModelAndView(url);	// 글을 쓰고 목록 첫 페이지로 돌아감
		}

	}

	// 삭제하기 (제한 : 로긴한자 + 게시자)
	@RequestMapping("admin/delete")
	public ModelAndView adminDelete(HttpServletRequest request, Integer itemNo){
//		request.setAttribute("pageNo", pageNo);
//		request.setAttribute("bdNoRev", bdNoRev);
		this.shopService.deleteBoth(itemNo);
		String url="redirect:itemList.html";
		return new ModelAndView(url);	// 글을 쓰고 목록 해당 페이지로 돌아감
	}

}