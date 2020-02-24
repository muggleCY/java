package com.jc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jc.service.PrepaidService;
import com.jc.vo.PrepaidVo;


@Controller
@RequestMapping("/prepaid")
public class PrepaidController {
	@Autowired
	private PrepaidService prepaidService;
	
	
	
	@RequestMapping("/selectPrepaidVo")
	@ResponseBody
	public void selectPrepaidVo(Integer id ,  HttpServletResponse resp){
		List<PrepaidVo> prepaidVos = prepaidService.selectPrepaidVo(id);
		String json = JSONArray.fromObject(prepaidVos).toString();
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			out.write(json);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@RequestMapping("/addPrepaid")
	@ResponseBody
	public void addPrepaid(String cardNum,Integer userId,String cardPwd,HttpServletResponse resp){
		System.out.println("userId :" + userId);
		PrintWriter pw = null;
		try {
			pw = resp.getWriter();
			prepaidService.addPrepaid(userId, cardNum,cardPwd);
			pw.write("success");
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
			pw.write(e.getMessage());
			pw.flush();
			pw.close();
		}
	}
}
