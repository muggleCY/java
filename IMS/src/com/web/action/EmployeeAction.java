package com.web.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web.entity.Employee;
import com.web.entity.User;
import com.web.service.EmployeeService;
import com.web.util.ActionUtils;
import com.web.util.Pager;
import com.web.vo.EmployeeVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author zyb
 * @TIME 19-12-11
 **/
@lombok.Setter
public class EmployeeAction {
    private EmployeeService employeeService;
    private EmployeeService employeeServiceProxy;
    public String showAllEmp(HttpServletRequest req, HttpServletResponse resp){
        //需要分页的页码
        Integer pageNumber = Integer.valueOf(req.getParameter("pageNumber"));
        //获取需要查询的员工的姓名
        String empName = req.getParameter("empName");

        //获取当前登录的用户给service
        User user = (User) req.getSession().getAttribute("user");
//        获取员工信息
//        Pager<EmployeeVO> empVOList = employeeService.getEmpVOListByPage(pageNumber);
        Pager<EmployeeVO> empVOList = employeeService.getEmpVOListByPageAndCodi(pageNumber, empName, user);
        ActionUtils.writeJson(resp,JSONObject.toJSONString(empVOList));
        return "success";
    }
    public String addEmp(HttpServletRequest req, HttpServletResponse resp){
        String empName = req.getParameter("empName");
        String sex = req.getParameter("empSex");
        String empMaster = req.getParameter("empMaster");
        String empEntryTime = req.getParameter("empEntryTime");
        JSONObject retJson = new JSONObject();
        try {
            employeeServiceProxy.addEmployee(empName,sex,empMaster,empEntryTime);
            retJson.put("state","200");
        } catch (Exception e) {
            retJson.put("state",e.getMessage());
        }
        ActionUtils.writeJson(resp,retJson.toJSONString());
        return "success";
    }
    public String deleteEmp(HttpServletRequest req, HttpServletResponse resp){
        Integer id = Integer.valueOf(req.getParameter("id"));
        JSONObject retJson = new JSONObject();
        try {
            employeeServiceProxy.removeEmployee(id);
            retJson.put("state","200");
        } catch (Exception e) {
            retJson.put("state",e.getMessage());
        }
        ActionUtils.writeJson(resp,retJson.toJSONString());
        return "success";
    }
    public String getOneEmp(HttpServletRequest req, HttpServletResponse resp){
        Integer id = Integer.valueOf(req.getParameter("id"));
        EmployeeVO employee = employeeService.selectOneEmp(id);
        ActionUtils.writeJson(resp,JSONObject.toJSONString(employee));
        return "success";
    }
    public String updateEmp(HttpServletRequest req, HttpServletResponse resp){
        JSONObject retJson = new JSONObject();
        String empName = req.getParameter("empName");
        String empMaster = req.getParameter("empMaster");
        String entryTime = req.getParameter("entryTime");
        String empNo = req.getParameter("empNo");
        try {
            employeeService.updateEmp(empNo,empName,empMaster,entryTime);
            retJson.put("state","200");
        } catch (Exception e) {
            retJson.put("state",e.getMessage());
        }
        ActionUtils.writeJson(resp,retJson.toJSONString());
        return "success";
    }
}
