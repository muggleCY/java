package com.jc.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jc.pojo.GameType;
import com.jc.service.GameTypeService;


@Controller
@RequestMapping("/gameType")
public class GameTypeController {
	@Autowired
	private GameTypeService gameTypeService;
//	@Autowired
//	private GameService gameService;
	
	
//	public void refreshSession(HttpSession session){
//		session.setAttribute("types",gameTypeService.getTypes(new GameType()));
//	}
	@RequestMapping("/getTypes")
	@ResponseBody
	public String getTypes(String typeName,Integer typeStatus , HttpServletResponse resp){
		GameType gameType = new GameType();
		gameType.setTypeName(typeName);
		gameType.setTypeStatus(typeStatus);
		List<GameType> gameTypes =  gameTypeService.getGameTypes(gameType);
		String json = JSONArray.fromObject(gameTypes).toString();
		return json;
	}
	@RequestMapping("/getTypesForUser")
	@ResponseBody
	public String getTypesForUser(HttpServletResponse resp){
		GameType gameType = new GameType();
		gameType.setTypeStatus(1);
		List<GameType> gameTypes =  gameTypeService.getGameTypes(gameType);
		String json = JSONArray.fromObject(gameTypes).toString();
		return json;
	}
	@RequestMapping("/insertGameType")
	public ModelAndView insertGameType(GameType gameType,@RequestParam("imgFile") CommonsMultipartFile imgFile,HttpServletRequest request,HttpServletResponse resp,HttpSession session){
		ModelAndView mav = new ModelAndView();
		String imgPath = request.getSession().getServletContext().getRealPath(
				"/img/cover");
		String fileName = imgFile.getFileItem().getName();
		System.out.println(imgPath);
		System.out.println(fileName);
		try {
			gameTypeService.addGameType(gameType, imgFile.getInputStream(), imgPath, fileName);
			mav.addObject("isError", true);
			mav.addObject("errorMessage", "添加成功");
			mav.setViewName("/gameType/gameType.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("isError", true);
			mav.addObject("errorMessage", "添加失败"+e.getMessage());
			mav.setViewName("/gameType/gameType.jsp");
		}
		return mav;
	}
	@RequestMapping("/selectGameType")
	public ModelAndView selectGameType(HttpServletRequest req){
		Integer id = Integer.valueOf(String.valueOf(req.getParameter("id")));
		System.out.println(id);
		ModelAndView mv = new ModelAndView();
		GameType gameType = new GameType();
		gameType.setId(id);
		gameType = gameTypeService.getGameTypeById(gameType);
		mv.addObject("gameType", gameType);
		mv.setViewName("/gameType/updateGameType.jsp");
		return mv;
	}
	@RequestMapping("/updateGameType")
	@ResponseBody
	public ModelAndView updateGameType(GameType gameType,HttpSession session){
		ModelAndView mv = new ModelAndView();
		try {
			gameTypeService.modifyGameType(gameType);
			mv.setViewName("/gameType/gameType.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("gameType", gameType);
			mv.addObject("isError", true);
			mv.addObject("errorMessage", e.getMessage());
			mv.setViewName("/gameType/updateGameType.jsp");
		}
		return mv;
	}
	@RequestMapping("/deleteGameTypes")
	@ResponseBody
	public void deleteGameTypes(HttpServletRequest req,HttpServletResponse resp){
		PrintWriter pw = null;
		String[] ids = (String[]) req.getParameterValues("ids");
		System.out.println(ids);
		try {
			pw = resp.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			gameTypeService.removeGameType(ids);
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
