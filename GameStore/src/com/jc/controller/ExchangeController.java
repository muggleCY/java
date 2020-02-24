package com.jc.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jc.pojo.Exchange;
import com.jc.service.ExchangeService;
import com.jc.util.Pager;
import com.jc.vo.ExchangeVo;


@Controller
@RequestMapping("/exchange")
public class ExchangeController {
	@Autowired
	private ExchangeService exchangeService;
	
	@ResponseBody
	@RequestMapping("/getExchangesByPage")
	public void getExchangesByPage(Integer pageNo, Integer prov,HttpServletResponse resp){
		System.out.println("prov : "+ prov);
		if (prov == 0) {
			prov = null;
		}
		Pager<ExchangeVo> games = exchangeService.selectExchanges(pageNo, prov);
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
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/toModifyExchange")
	public ModelAndView toModifyExchange(Integer id){
		ModelAndView mv = new ModelAndView();
		mv.addObject("exchange", exchangeService.selectExchangeById(id));
		mv.setViewName("/exchange/updateExchange.jsp");
		return mv;
	}
	
	@RequestMapping("/addOrModifyExchange")
	public ModelAndView addOrModifyExchange(Exchange exchange,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			exchangeService.addOrModifyExchange(exchange);
			mv.addObject("isError", true);
			mv.addObject("errorMessage", "成功");
			mv.setViewName("/exchange/exchange.jsp");
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("isError", true);
			mv.addObject("errorMessage", e.getMessage());
			mv.setViewName("/exchange/updateExchange.jsp");
			return mv;
		}
	}
	
	
	@RequestMapping("/deleteExchanges")
	@ResponseBody
	public void deleteExchanges(HttpServletRequest req,HttpServletResponse resp){
		PrintWriter pw = null;
		String[] ids = (String[]) req.getParameterValues("ids");
		System.out.println(ids);
		try {
			pw = resp.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			exchangeService.removeExchanges(ids);
			pw.write("success");
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
			pw.write("fail");
			pw.flush();
			pw.close();
		}
	}
}
