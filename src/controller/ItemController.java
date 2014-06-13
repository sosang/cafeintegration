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
		
		



		model.put("itemList", itemList);

		
	
		ModelAndView modelAndView = new ModelAndView();
		
	
		modelAndView.addAllObjects(model);
		return modelAndView;
	}

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


	@RequestMapping("/admin/itemRegBefore")
	public ModelAndView itemRegBefore(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("admin/itemReg");
		modelAndView.addObject(new ItemVo());
		modelAndView.addObject(new SaveFilePathTo());
		return modelAndView;
	}

	@RequestMapping("/admin/itemReg")
	public ModelAndView boardReviewsWrite(@Valid ItemVo itemVo, BindingResult bindingResult, 
			HttpServletRequest request, @RequestParam("filePath")MultipartFile filePath) throws Exception{
		String originFileName = filePath.getOriginalFilename();
		String forDb = "";
		String extention = originFileName.substring(originFileName.lastIndexOf(".")+1, originFileName.length());
		if(extention.equals("jpg")||extention.equals("gif")||extention.equals("bmp")||extention.equals("png")||extention.equals("tif")||extention.equals("tiff")||extention.equals("jpeg")||extention.equals("jpe")||extention.equals("jfif")||extention.equals("dib")||extention == ""){

			forDb = "../img/"+originFileName;
		}else{
			ModelAndView modelAndView = new ModelAndView("admin/itemReg");
			return modelAndView;
		}


		String uploadPath = "C:/Tomcat 7/webapps/cafeintegration/img";


		File saveDir = new File(uploadPath);
		if(!saveDir.exists()) saveDir.mkdirs();

		if(bindingResult.hasErrors()){
			ModelAndView modelAndView = new ModelAndView("admin/itemReg");
			modelAndView.getModel().putAll(bindingResult.getModel());

			Map<String, Object> map = bindingResult.getModel();
			Set<String> keys = map.keySet();
			Iterator<String> it = keys.iterator();
			while(it.hasNext()) {
			Object key = it.next();
			Object val = map.get(key);
			System.out.println("���� :: "+val);
			}

			return modelAndView;
		}

		this.shopService.itemReg(itemVo, forDb);

		writeFile(filePath, uploadPath, originFileName);


		int newItemNo = this.shopService.getNewItemNo();
		this.shopService.setFilePath(newItemNo, forDb);

		return new ModelAndView("redirect:itemList.html");	
	}

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
	        }
	    }


	@RequestMapping("admin/itemEdit")
	public ModelAndView itemEdit(Integer itemNo){
		ModelAndView modelAndView = new ModelAndView("admin/itemUpdate");
		ItemVo itemVo = this.shopService.getItemByItemNo(itemNo);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("item", itemVo);
		modelAndView.addAllObjects(model);
		return modelAndView;
	}

	
	@RequestMapping(value="admin/itemUpdate")
	public ModelAndView itemUpdate(@Valid ItemVo itemVo, BindingResult bindingResult, HttpServletRequest request, Integer itemNo, @RequestParam("filePath")MultipartFile filePath){
		if(bindingResult.hasErrors()){
			ModelAndView modelAndView = new ModelAndView("admin/itemUpdate");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}

		if(filePath.isEmpty()){
			this.shopService.itemUpdate(itemVo, itemNo);
			String url="redirect:itemList.html";
			return new ModelAndView(url);	
		}else{
			String originFileName = filePath.getOriginalFilename();
			String extention = originFileName.substring(originFileName.lastIndexOf(".")+1, originFileName.length());
			if(extention.equals("jpg")||extention.equals("gif")||extention.equals("bmp")||extention.equals("png")||extention.equals("tif")||extention.equals("tiff")||extention.equals("jpeg")||extention.equals("jpe")||extention.equals("jfif")||extention.equals("dib")||extention == ""){
			}else{
				ModelAndView modelAndView = new ModelAndView("admin/itemList");
				return modelAndView;
			}


			String uploadPath = "C:/Tomcat 7/webapps/cafeintegration/img";

			String forDb = "../img/"+originFileName;


			File saveDir = new File(uploadPath);
			if(!saveDir.exists()) saveDir.mkdirs();
			
			if(bindingResult.hasErrors()){
				ModelAndView modelAndView = new ModelAndView("admin/itemReg");
				modelAndView.getModel().putAll(bindingResult.getModel());

				Map<String, Object> map = bindingResult.getModel();
				Set<String> keys = map.keySet();
				Iterator<String> it = keys.iterator();
				while(it.hasNext()) {
				Object key = it.next();
				Object val = map.get(key);
				System.out.println("���� :: "+val);
				}
				
				return modelAndView;
			}

			this.shopService.itemUpdate(itemVo, itemNo);

			writeFile(filePath, uploadPath, originFileName);

			this.shopService.updateFilePath(itemVo.getItemNo(), forDb);

			String url="redirect:itemList.html";
			return new ModelAndView(url);	
		}

	}


	@RequestMapping("admin/delete")
	public ModelAndView adminDelete(HttpServletRequest request, Integer itemNo){

		this.shopService.deleteBoth(itemNo);
		String url="redirect:itemList.html";
		return new ModelAndView(url);	
	}

}