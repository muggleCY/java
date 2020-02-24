package com.web.action;

import com.alibaba.fastjson.JSONObject;
import com.web.entity.Holiday;
import com.web.entity.User;
import com.web.exception.HolidayInsertFailException;
import com.web.exception.HolidayIsSubmitException;
import com.web.service.HolidayService;
import com.web.util.ActionUtils;
import com.web.util.Pager;
import com.web.vo.HolidayVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author cy
 * @Time 19-12-11 下午5:07
 **/
public class HolidayAction {
    private HolidayService holidayService;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public String showHolidays(HttpServletRequest req, HttpServletResponse resp){
        try {
            List<Holiday> holidays = holidayService.selectHolidays();
            PrintWriter out = resp.getWriter();
            out.write(JSONObject.toJSON(holidays).toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
    public String getHolidaysByPage(HttpServletRequest req, HttpServletResponse resp){
        Integer pageNo = Integer.parseInt(req.getParameter("pageNo"));
        String holidayUser = req.getParameter("holidayUser");
        Integer holidayType = Integer.parseInt(req.getParameter("holidayType"));
        Integer holidayStatus = Integer.parseInt(req.getParameter("holidayStatus"));
        User user = (User) req.getSession().getAttribute("user");
        System.out.println(user.getEmpNo());
        Pager<HolidayVo> pager = holidayService.selectHolidaysByPage(user.getEmpNo(),holidayUser,holidayType,holidayStatus,pageNo);
        try {
            ActionUtils.writeJson(resp, JSONObject.toJSONString(pager));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
    public String showOneHoliday(HttpServletRequest req, HttpServletResponse resp){
        resp.setContentType("application/json;charset=utf-8");
        Integer id = Integer.valueOf(req.getParameter("id"));
        try {
            Holiday holiday = holidayService.selectOneHoliday(id);
            System.out.println(holiday);
            PrintWriter out = resp.getWriter();
            out.write(JSONObject.toJSON(holiday).toString());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }
    public String updateHoliday(HttpServletRequest req, HttpServletResponse resp){
        resp.setContentType("application/json;charset=utf-8");
        Integer id = Integer.parseInt(req.getParameter("hid"));
        String holidayNo = req.getParameter("holidayNo");
        Integer holidayUser = Integer.parseInt(req.getParameter("holidayUserId"));
        Integer holidayType = Integer.parseInt(req.getParameter("holidayType"));
        String holidayBz = req.getParameter("holidayBz");
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        Integer holidayStatus = Integer.parseInt(req.getParameter("holidayStatus"));
        JSONObject returnMessage = new JSONObject();
        JSONObject returnCode = new JSONObject();
        try {
            holidayService.updateHoliday(id,holidayNo,holidayUser,holidayType,holidayBz,startTime,endTime,holidayStatus);
            returnCode.put("state","200");
        } catch (HolidayIsSubmitException e) {
            returnCode.put("state",e.getMessage());
        }
        ActionUtils.writeJson(resp,returnCode.toJSONString());;
        return "success";
    }
    public String insertHoliday(HttpServletRequest req, HttpServletResponse resp){
        Integer holidayType = Integer.valueOf(req.getParameter("holidayType"));
        String holidayBz = req.getParameter("holidayBz");
        String startTime = req.getParameter("startTime");
        String endTime = req.getParameter("endTime");
        Integer status = Integer.valueOf(req.getParameter("obj"));
        User user = (User) req.getSession().getAttribute("user");
        Integer empNo = user.getEmpNo();
        JSONObject returnCode = new JSONObject();
        try {
            holidayService.insertHoliday(empNo,holidayType,holidayBz,startTime,endTime,status);
            returnCode.put("state","200");
        }catch (HolidayInsertFailException e) {
            returnCode.put("state",e.getMessage());
        } catch (Exception e) {
            returnCode.put("state",e.getMessage());
        }
        ActionUtils.writeJson(resp,returnCode.toJSONString());
        return "success";
    }
    public String deleteholiday(HttpServletRequest req, HttpServletResponse resp){
        Integer id = Integer.valueOf(req.getParameter("id"));
        PrintWriter out = null;
        JSONObject returnJson = new JSONObject();
        try {
            out= resp.getWriter();
            holidayService.deleteHoliday(id);
//			如果删除成功返回状态200
            returnJson.put("state","200");
        } catch (Exception e) {
//			如果出现异常,将异常信息传递给前端
            returnJson.put("state",e.getMessage());
        }
        out.write(returnJson.toJSONString());
        out.flush();
        out.close();
        return "success";
    }
    public HolidayService getHolidayService() {
        return holidayService;
    }

    public void setHolidayService(HolidayService holidayService) {
        this.holidayService = holidayService;
    }
}
