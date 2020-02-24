package com.jc.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jc.pojo.Manager;
import com.jc.service.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerController {
	@Autowired
	private ManagerService managerService; 
	/**
	 * 登录管理员
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping("/login")
		public ModelAndView login(String username,String password,HttpSession session){
		
		ModelAndView mv = new ModelAndView();
		Manager manager = new Manager();
		manager.setUsername(username);
		manager.setPassword(password);
		try {
			manager = managerService.getManager(manager);
			session.setAttribute("manager", manager);
			mv.setViewName("redirect:/index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("isError", true);
			session.setAttribute("errorMessage",e.getMessage());
			mv.setViewName("redirect:/login.jsp");
		}
		return mv;
	}
	/**
	 * 清除缓存
	 * @param session
	 */
	@RequestMapping("/clearManager")
	@ResponseBody
	public void clearManagerSession(HttpSession session){
		session.removeAttribute("manager");
	}
	
	
}
