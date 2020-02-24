package com.jc.controller;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jc.pojo.Province;
import com.jc.service.ProvinceService;

@Controller
@RequestMapping("/prov")
public class ProvinceController {
	@Autowired
	private ProvinceService provinceService;
	/**
	 * @ResponseBody是
	 * @return
	 */
	@RequestMapping("/getProvs")
	@ResponseBody
	public String getProvs(){
//		ModelAndView mav = new ModelAndView();
		List<Province> provinces = provinceService.getProvinces();
//		mav.addObject("provinces",provinces);
//		return mav;
		String json = JSONArray.fromObject(provinces).toString();
		return json;
	}
	@RequestMapping("/getProvId")
	@ResponseBody
	public String getProvId(){
//		ModelAndView mav = new ModelAndView();
		List<Province> provinces = provinceService.getProvinceById();
//		mav.addObject("provinces",provinces);
//		return mav;
		String json = JSONArray.fromObject(provinces).toString();
		return json;
	}
	
}
