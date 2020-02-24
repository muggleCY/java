package com.jc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jc.pojo.Expense;
import com.jc.pojo.Game;
import com.jc.pojo.GameType;
import com.jc.pojo.User;
import com.jc.service.ExpenseService;
import com.jc.service.GameService;
import com.jc.service.GameTypeService;
import com.jc.util.Pager;
import com.jc.vo.GameVo;


@Controller
@RequestMapping("/game")
public class GameController {
	@Autowired
	private GameService gameService;
	@Autowired
	private GameTypeService gameTypeService;
	@Autowired
	private ExpenseService expenseService;
	
	@ResponseBody
	@RequestMapping("/getGamesByPage")
	public void getGamesByPage(Integer pageNo, String gameName, String gameType,HttpServletResponse resp,HttpSession session){
		System.out.println(gameType);
		if (gameType.equals("0")) {
			gameType = null;
		}
//		Pager<Game> games = gameService.selectGamesByPage(pageNo, gameName, gameType);
		Pager<GameVo> games = gameService.selectGames(pageNo, gameName, gameType);
		PrintWriter pw = null;
		try {
			pw = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String json = JSONObject.fromObject(games).toString();
		pw.write(json);
		pw.flush();
		pw.close();
	}
	@ResponseBody
	@RequestMapping("/getGameForUser")
	public void getGameForUser(Integer pageNo, String gameName, String gameType,HttpServletResponse resp,HttpSession session){
		System.out.println(gameType);
		if (gameType.equals("0")) {
			gameType = null;
		}
//		Pager<Game> games = gameService.selectGamesByPage(pageNo, gameName, gameType);
		Pager<GameVo> games = gameService.selectGamesForUser(pageNo, gameName, gameType);
		PrintWriter pw = null;
		try {
			pw = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String json = JSONObject.fromObject(games).toString();
		pw.write(json);
		pw.flush();
		pw.close();
	}
	@RequestMapping("/gameDetail")
	public ModelAndView gameDetail(Integer id){
		ModelAndView mv = new ModelAndView();
		Game game = null;
		GameType gameType = null;
		try {
			game = gameService.selectGame(id);
			GameType type = new GameType();
			type.setId(game.getGameType());
			gameType = gameTypeService.getGameTypeById(type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("game", game);
		mv.addObject("gameType", gameType);
		mv.setViewName("/game/gameDetails.jsp");
		return mv;
	}
	@RequestMapping("/gameDetailForUser")
	public ModelAndView gameDetailForUser(Integer id,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Game game = null;
		GameType gameType = null;
		Expense expense = null;
		try {
			game = gameService.selectGame(id);
			GameType type = new GameType();
			type.setId(game.getGameType());
			gameType = gameTypeService.getGameTypeById(type);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			expense = expenseService.selectExpenseByUidAndGid(user.getId(), id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("game", game);
		mv.addObject("gameType", gameType);
		mv.addObject("expense", expense);
		mv.setViewName("/view/userDetail.jsp");
		return mv;
	}
	
	@RequestMapping("/addOrModifyGame")
	public ModelAndView addOrModifyGame(Game game,@RequestParam("coverFile") CommonsMultipartFile coverFile,HttpServletRequest request,
			@RequestParam("gameScreenO") CommonsMultipartFile gameScreenO,
			@RequestParam("gameScreenT") CommonsMultipartFile gameScreenT,
			@RequestParam("gameScreenTh") CommonsMultipartFile gameScreenTh) {
//		System.out.println(coverFile.getFileItem().getName());
//		System.out.println(gameScreenT.getFileItem().getName());
		System.out.println(game.getGameStatus());
		System.out.println("-------------------");
		ModelAndView mav = new ModelAndView();
		String coverPath = request.getSession().getServletContext().getRealPath("/img/cover");
		String screenPath = request.getSession().getServletContext().getRealPath("/img/screen");
		String coverName = coverFile.getFileItem().getName().equals("")?null:coverFile.getFileItem().getName();
		String screen1Name = gameScreenO.getFileItem().getName().equals("")?null:gameScreenO.getFileItem().getName();
		String screen2Name = gameScreenT.getFileItem().getName().equals("")?null:gameScreenT.getFileItem().getName();
		String screen3Name = gameScreenTh.getFileItem().getName().equals("")?null:gameScreenTh.getFileItem().getName();
		try {
			InputStream coverInput = coverFile.getFileItem().getName().equals("")?null:coverFile.getInputStream();
			InputStream screen1Input = gameScreenO.getFileItem().getName().equals("")?null:gameScreenO.getInputStream();
			InputStream screen2Input = gameScreenT.getFileItem().getName().equals("")?null:gameScreenT.getInputStream();
			InputStream screen3Input = gameScreenTh.getFileItem().getName().equals("")?null:gameScreenTh.getInputStream();
			System.out.println(screen1Input);
			System.out.println(screen2Input+"+++++++");
			System.out.println(screen3Input);
			gameService.addOrModifyGame(game,coverInput,screen1Input,screen2Input,screen3Input,
					coverPath,screenPath, coverName, screen1Name, screen2Name, screen3Name);
			mav.addObject("isError", true);
			mav.addObject("errorMessage", "成功");
			mav.setViewName("/game/game.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("isError", true);
			mav.addObject("errorMessage", e.getMessage());
			mav.setViewName("/game/game.jsp");
		}
		return mav;
	}
	
	@RequestMapping("/deleteGames")
	@ResponseBody
	public void deleteGames(HttpServletRequest req,HttpServletResponse resp){
		PrintWriter pw = null;
		String[] ids = (String[]) req.getParameterValues("ids");
		for (int i = 0; i < ids.length; i++) {
			System.out.println(ids[i]);
		}
		try {
			pw = resp.getWriter();
			gameService.removeGame(ids);
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
	@RequestMapping("/toModifyGame")
	public ModelAndView toModifyGame(Integer id) {
		ModelAndView mv = new ModelAndView();
		try {
			mv.addObject("game", gameService.selectGame(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("/game/updateGame.jsp");
		return mv;
	}

	
	
	@RequestMapping("/download")
	public void downLoad(HttpServletRequest request,HttpServletResponse response,Integer gameId,Integer userId) throws Exception{
		PrintWriter pw = null;
		Expense expense = expenseService.selectExpenseByUidAndGid(userId, gameId);
		try {
			pw = response.getWriter();
			expenseService.modifyExpense(expense);
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
