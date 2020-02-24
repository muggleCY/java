package com.jc.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jc.service.CardService;
import com.jc.util.Pager;
import com.jc.vo.CardVo;


@Controller
@RequestMapping("/card")
public class CardController {
	@Autowired
	private CardService cardService;
	
	@ResponseBody
	@RequestMapping("/getCardsByPage")
	public void getCardsByPage(Integer pageNo,String cardNum,
			String startTime, String endTime, Integer provId,HttpServletResponse resp){
		PrintWriter pw = null;
		if (provId== 0) {
			provId = null;
		}
//		System.out.println(startTime);
		Pager<CardVo> cards = null;
		try {
			cards = cardService.selectCardsByPage(pageNo, cardNum,startTime, endTime, provId);
			pw = resp.getWriter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String json = JSONObject.fromObject(cards).toString();
		pw.write(json);
		pw.flush();
		pw.close();
	}
	
	
	@RequestMapping("/addCard")
	public ModelAndView addCard(Integer number,Integer[] provId,Integer cardAmount,String startTime,String endTime){
		ModelAndView mv = new ModelAndView();
		
		try {
			cardService.addCards(number,provId,cardAmount,startTime,endTime );
			mv.setViewName("/card/card.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("isError", true);
			mv.addObject("errorMessage", e.getMessage());
			mv.setViewName("/card/addCard.jsp");
		}
		
		return mv;
	}
	
	
	
	
	
	
	
}
