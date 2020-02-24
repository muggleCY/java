package com.web.action;

import com.alibaba.fastjson.JSONObject;
import com.web.entity.User;
import com.web.exception.ExpDeleteException;
import com.web.service.ExpenseService;
import com.web.util.ActionUtils;
import com.web.util.Pager;
import com.web.vo.ExpenseVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author zyb
 * @TIME 19-12-12
 **/
@lombok.Setter
public class ExpenseAction {
    private ExpenseService expenseService;
    public String showAllExpense(HttpServletRequest req, HttpServletResponse resp){
        Integer stateNo = Integer.valueOf(req.getParameter("stateNo"));
        Integer typeNo = Integer.valueOf(req.getParameter("typeNo"));
        Integer pageNo = Integer.valueOf(req.getParameter("pageNo"));
        Pager<ExpenseVO> pager = null;
        if (stateNo==-1&&typeNo==-1){
            pager = expenseService.showAllExpenseByPager(pageNo);
        }else {
            pager = expenseService.showAllExpenseByPagerByCondi(pageNo, typeNo, stateNo);
        }
        ActionUtils.writeJson(resp,JSONObject.toJSONString(pager));
        return "success";
    }
    public String updateExpense(HttpServletRequest req, HttpServletResponse resp){

        Integer expId = Integer.valueOf(req.getParameter("expenseTypeId"));
        String expSummary = req.getParameter("expSummary");
        Double expMoney = Double.valueOf(req.getParameter("expMoney"));
        JSONObject retJson = new JSONObject();
        retJson.put("state","200");
        ActionUtils.writeJson(resp,retJson.toJSONString());
        return "success";
    }
    public String showOneRecById(HttpServletRequest req, HttpServletResponse resp){
        Integer id = Integer.valueOf(req.getParameter("id"));
        ActionUtils.writeJson(resp,JSONObject.toJSONString(expenseService.getOneExpenseVO(id)));
        return "success";
    }
    public String deleteOneRecById(HttpServletRequest req, HttpServletResponse resp){
        Integer id = Integer.valueOf(req.getParameter("id"));
        JSONObject jsonObject = new JSONObject();
        try {
            expenseService.removeExpense(id);
            jsonObject.put("state","200");
        } catch (ExpDeleteException e) {
            jsonObject.put("state",e.getMessage());
        }
        ActionUtils.writeJson(resp,jsonObject.toJSONString());
        return "success";
    }
    public String addOneExpense(HttpServletRequest req, HttpServletResponse resp){
        Integer expType = Integer.valueOf(req.getParameter("expType"));
        String expCont = req.getParameter("expCont");
        Double expMoney = Double.valueOf(req.getParameter("expMoney"));
       Integer state= Integer.valueOf(req.getParameter("state"));
        User user = (User) req.getSession().getAttribute("user");
        JSONObject retJson = new JSONObject();
        try {
            expenseService.addOneExpense(expType,expCont,expMoney,user,state);
            retJson.put("state","200");
        } catch (Exception e) {
            retJson.put("state",e.getMessage());
        }
        ActionUtils.writeJson(resp,retJson.toJSONString());
        return "success";
    }
}
