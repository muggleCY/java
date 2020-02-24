package com.jc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jc.service.ExpenseService;
import com.jc.vo.ExpenseVo;


@Controller
@RequestMapping("/expense")
public class ExpenseController {
	@Autowired
	private ExpenseService expenseService;
	
	@RequestMapping("/showExpenses")
	@ResponseBody
	public void showExpenses(Integer userId ,HttpServletResponse resp){
		List<ExpenseVo> expenseVos = expenseService.showExpenseVo(userId);
		String json = JSONArray.fromObject(expenseVos).toString();
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
	@RequestMapping("/buyGame")
	@ResponseBody
	public void buyGame(Integer payWay,Integer userId,Integer gameId,HttpSession session, HttpServletResponse resp){
		PrintWriter out = null;
		try {
			expenseService.addExpense(userId,gameId,payWay);
			out = resp.getWriter();
			out.write("success");
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				out = resp.getWriter();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			out.write(e.getMessage());
			out.flush();
			out.close();
		}
	}

}
