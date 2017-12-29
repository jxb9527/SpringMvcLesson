package cn.et.springmvc.lesson2.crud.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import cn.et.springmvc.lesson2.crud.service.FoodService;
import cn.et.springmvc.lesson2.crud.util.PageTool;

@Controller
public class FoodController {
	@Autowired
	private FoodService foodService;
	
	@RequestMapping(value="/showFood",method=RequestMethod.GET)
	public String queryFood(String foodName,Integer curPage,Model model){
		if(curPage==null){
			curPage=1;
		}
		PageTool pt = foodService.queryByName(foodName,curPage);
		
		model.addAttribute("pt", pt);
		return "/detail/foodList.jsp";
	}
	
	@RequestMapping(value="/food/{foodId}",method=RequestMethod.GET)
	public String queryFoodById(@PathVariable String foodId,Model model){
		Map<String, Object> detailList = foodService.queryById(foodId);
		model.addAttribute("detailList", detailList);
		return "/detail/detailFood.ftl";
	}
	
	@RequestMapping(value="/food/{foodId}",method=RequestMethod.DELETE)
	public String deleteFood(@PathVariable String foodId,Model model){
		foodService.deleteFood(foodId);
		return queryFood(null,1, model);
	}
	
	@RequestMapping(value="/food/{foodId}",method=RequestMethod.POST)
	public String updateFood(@PathVariable String foodId,String foodName,String price,MultipartFile imageUrl,Model model) throws IllegalStateException, IOException{
		
		//获取文件名
		String fileName=imageUrl.getOriginalFilename();
		
		//绝对路径
//		String absPath="D:\\MyWorkSpaces\\.metadata\\.me_tcat\\webapps\\s\\images";
		String absPath="D:\\MyWorkSpaces\\SpringMvcLesson\\src\\main\\webapp\\images";
		
		File file = new File(absPath+"\\"+fileName);
		//保存文件
		if(!file.exists()){
			imageUrl.transferTo(file);
		}
		
		foodService.updateFood(foodId, foodName, price, "/"+fileName);
		return queryFood(null,1, model);
	}
	
	@RequestMapping(value="/food",method=RequestMethod.POST)
	public String addFood(String foodName,String price,MultipartFile imageUrl, Model model) throws IllegalStateException, IOException{
		
		//获取文件名
		String fileName=imageUrl.getOriginalFilename();
				
		//绝对路径
//		String absPath="D:\\MyWorkSpaces\\.metadata\\.me_tcat\\webapps\\s\\images";
		String absPath="D:\\MyWorkSpaces\\SpringMvcLesson\\src\\main\\webapp\\images";
				
		//保存文件
		imageUrl.transferTo(new File(absPath+"\\"+fileName));
		
		foodService.saveFood(foodName, price, "/"+fileName);
		
		return queryFood(null,1, model);
	}
	
	@RequestMapping(value="/download",method=RequestMethod.GET)
	public ResponseEntity<byte[]> download(String imagePath) throws IOException{
		
		String absPath="D:\\MyWorkSpaces\\.metadata\\.me_tcat\\webapps\\s\\images\\"+imagePath;
		
		String fileName=imagePath;
		//需要下载的目标文件
		File file = new File(absPath);
		//设置响应头
		HttpHeaders hh = new HttpHeaders();
		//设置下载的文件名称
		hh.setContentDispositionFormData("attachment", URLEncoder.encode(fileName,"UTF-8"));
		//读取目标文件为二进制数组
		byte[] fileByte = FileCopyUtils.copyToByteArray(file);
		//构建ResponseEntity对象
		ResponseEntity<byte[]> re = new ResponseEntity<byte[]>(fileByte, hh	, HttpStatus.CREATED);
		return re;
	}
}
